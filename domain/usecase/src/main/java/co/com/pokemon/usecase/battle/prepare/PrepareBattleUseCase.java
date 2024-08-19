package co.com.pokemon.usecase.battle.prepare;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.usecase.cards.asign.AssignRandomCardsUseCase;
import co.com.pokemon.usecase.cards.SelectCardUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PrepareBattleUseCase {
    private final SelectCardUseCase selectedCardsUseCase;

    private final AssignRandomCardsUseCase assignRandomCardsUseCase;

    public void execute(Battle battle, Player player1, Player player2, int numberOfCards) {
        List<PokemonCard> allCards = selectedCardsUseCase.execute();

        List<PokemonCard> player1Cards = assignRandomCardsUseCase.assignCards(allCards, numberOfCards);
        player1.setSelectedCards(player1Cards);

        allCards.removeAll(player1Cards);

        List<PokemonCard> player2Cards = assignRandomCardsUseCase.assignCards(allCards, numberOfCards);
        player2.setSelectedCards(player2Cards);

        battle.setPlayer1(player1);
        battle.setPlayer2(player2);
    }

}
