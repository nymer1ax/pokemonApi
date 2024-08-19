package co.com.pokemon.consumer.pokemonapi.consumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
@Service
public class PokemonApiRestConsumer {
    private final String baseUrl;
    private final WebClient webClient;
    private final ObjectMapper mapper;

    public PokemonApiRestConsumer(@Value("${adapter.pokemonapi.url}") String baseUrl, WebClient.Builder webClientBuilder, ObjectMapper mapper) {
        this.baseUrl = baseUrl;
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
        this.mapper = mapper;
    }

    @CircuitBreaker(name = "pokemonApiGetCircuitBreaker", fallbackMethod = "fallbackGet")
    @Retry(name = "pokemonApiGetRetry")
    public <T> Mono<T> get(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(responseType)
                .onErrorMap(IOException.class, ex -> new IOException("Failed to call " + baseUrl + uri, ex));
    }

    @CircuitBreaker(name = "pokemonApiPostCircuitBreaker", fallbackMethod = "fallbackPost")
    @Retry(name = "pokemonApiPostRetry")
    public <T, R> Mono<T> post(String uri, R requestBody, Class<T> responseType) {
        return webClient.post()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestBody), requestBody.getClass())
                .retrieve()
                .bodyToMono(responseType)
                .onErrorMap(IOException.class, ex -> new IOException("Failed to call " + baseUrl + uri, ex));
    }



}
