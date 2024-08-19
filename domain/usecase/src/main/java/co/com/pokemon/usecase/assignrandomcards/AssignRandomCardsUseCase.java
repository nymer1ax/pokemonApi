package co.com.pokemon.usecase.assignrandomcards;

import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.model.pokemoncard.gateways.PokemonCardRepository;
import co.com.pokemon.usecase.validatecard.ValidateCardUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AssignRandomCardsUseCase {

    private final ValidateCardUseCase validateCardUseCase;
    public List<PokemonCard> assignCards(List<PokemonCard> availableCards, int numberOfCards) {
        return availableCards.stream()
                .filter(validateCardUseCase::isValidCard)
                .limit(numberOfCards)
                .collect(Collectors.toList());
    }
}
