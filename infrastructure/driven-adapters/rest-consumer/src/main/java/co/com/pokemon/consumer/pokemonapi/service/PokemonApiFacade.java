package co.com.pokemon.consumer.pokemonapi.service;

import co.com.pokemon.consumer.pokemonapi.consumer.PokemonApiRestConsumer;
import co.com.pokemon.consumer.pokemonapi.dto.response.cards.PokemonCardApiDto;
import co.com.pokemon.consumer.pokemonapi.mapper.PokemonCardMapper;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokemonApiFacade {
    private final PokemonApiRestConsumer pokemonApiRestConsumer;

    public List<PokemonCard> getPokemonCards() {
        PokemonCardApiDto[] cardDtos = pokemonApiRestConsumer.get("/cards", PokemonCardApiDto[].class)
                .block();

        if (cardDtos == null || cardDtos.length == 0) {
            return List.of();
        }

        return Arrays.stream(cardDtos)
                .map(PokemonCardMapper::toPokemonCard)
                .collect(Collectors.toList());
    }
}
