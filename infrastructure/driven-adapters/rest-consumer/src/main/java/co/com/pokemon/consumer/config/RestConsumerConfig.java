package co.com.pokemon.consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.okhttp3.OkHttpMetricsEventListener;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestConsumerConfig {

    @Bean
    public OkHttpClient getHttpClient(OkHttpMetricsEventListener listener) {
        return new OkHttpClient.Builder()
                .eventListener(listener)
                .build();
    }

    @Bean
    public OkHttpMetricsEventListener okHttpMetricsListener(MeterRegistry registry) {
        return OkHttpMetricsEventListener.builder(registry, "http-outgoing")
                .uriMapper(req -> req.url().encodedPath()).build();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

}
