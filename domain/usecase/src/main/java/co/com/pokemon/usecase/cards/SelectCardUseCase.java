package co.com.pokemon.usecase.cards;

import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.model.pokemoncard.gateways.PokemonCardGateway;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class SelectCardUseCase {
    private final PokemonCardGateway pokemonCardRepository;

    public List<PokemonCard> execute() {
        List<PokemonCard> allCards = pokemonCardRepository.getAll();
        Collections.shuffle(allCards); // Barajar las cartas para hacer la selección aleatoria
        return allCards;
    }
}
