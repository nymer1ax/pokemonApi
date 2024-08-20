package co.com.pokemon.consumer.pokemonapi.service;

import co.com.pokemon.consumer.pokemonapi.consumer.PokemonApiRestConsumer;
import co.com.pokemon.consumer.pokemonapi.mapper.PokemonCardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;


import co.com.pokemon.consumer.pokemonapi.dto.response.cards.PokemonCardApiResponseDto;

import co.com.pokemon.model.pokemoncard.PokemonCard;

@Service
@RequiredArgsConstructor
public class PokemonApiFacade {
    private final PokemonApiRestConsumer pokemonApiRestConsumer;

    public List<PokemonCard> getPokemonCards() {
        return getPokemonCardsAsync();
    }
    public List<PokemonCard> getPokemonCardsAsync() {
        return pokemonApiRestConsumer.get("/cards", PokemonCardApiResponseDto.class)
                .flatMapMany(responseDto -> Flux.fromIterable(responseDto.getData())
                        .map(PokemonCardMapper::toPokemonCard)
                        .subscribeOn(Schedulers.parallel()))
                .collectList()
                .block();

    }



}

