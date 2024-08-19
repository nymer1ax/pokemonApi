package co.com.pokemon.usecase.cards.validate;

import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class ValidateCardUseCase {
    public  boolean isValidCard(PokemonCard card) {
        return card.getHp() > 0 && card.getAttackName() != null && !card.getAttackName().isEmpty();
    }
}
