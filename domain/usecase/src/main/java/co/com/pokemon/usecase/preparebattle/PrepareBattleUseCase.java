package co.com.pokemon.usecase.preparebattle;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.usecase.assignrandomcards.AssignRandomCardsUseCase;
import co.com.pokemon.usecase.selectcard.SelectCardUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PrepareBattleUseCase {
    private final SelectCardUseCase selectedCardsUseCase;

    private final AssignRandomCardsUseCase assignRandomCardsUseCase;

    public void execute(Battle battle, Player player1, Player player2, int numberOfCards) {
        List<PokemonCard> allCards = selectedCardsUseCase.execute();

        // Asignar cartas a Player 1
        List<PokemonCard> player1Cards = assignRandomCardsUseCase.assignCards(allCards, numberOfCards);
        player1.setSelectedCards(player1Cards);

        // Remover las cartas asignadas a Player 1 de la lista de cartas disponibles
        allCards.removeAll(player1Cards);

        // Asignar cartas a Player 2
        List<PokemonCard> player2Cards = assignRandomCardsUseCase.assignCards(allCards, numberOfCards);
        player2.setSelectedCards(player2Cards);

        // Configurar jugadores en la batalla
        battle.setPlayer1(player1);
        battle.setPlayer2(player2);
    }

}
