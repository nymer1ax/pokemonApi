package co.com.pokemon.usecase.cards.asign;

import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.usecase.cards.validate.ValidateCardUseCase;
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
