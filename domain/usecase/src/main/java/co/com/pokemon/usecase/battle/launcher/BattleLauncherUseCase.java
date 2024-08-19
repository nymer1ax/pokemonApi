package co.com.pokemon.usecase.battle.launcher;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.action.BattleAction;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.action.PlayerAction;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.usecase.battle.BattleUseCase;
import co.com.pokemon.usecase.battle.manager.BattleManagerUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BattleLauncherUseCase {
    private final BattleManagerUseCase battleManager;
    private final BattleUseCase battleUseCase;

    public void startBattle(Player player1, Player player2) {
        battleManager.createBattle(player1, player2);
        Battle battle = battleManager.getCurrentBattle();

        System.out.println("La batalla entre " + player1.getName() + " y " + player2.getName() + " ha comenzado!");

        while (!battle.isFinished()) {
            // Turno de Player 1
            PlayerAction player1Input = getPlayerActionInput(player1);
            battleUseCase.executeTurn(battle, player1, player1Input);

            if (battle.isFinished()) break;

            // Turno de Player 2
            PlayerAction player2Input = getPlayerActionInput(player2);
            battleUseCase.executeTurn(battle, player2, player2Input);
        }

        declareWinner(battle);
        battleManager.endBattle();
    }

    private PlayerAction getPlayerActionInput(Player player) {

        BattleAction action = BattleAction.ATTACK;
        PokemonCard target = player.getSelectedCards().get(0);
        return new PlayerAction(action, target, null, null);
    }

    private void declareWinner(Battle battle) {
        if (battle.getPlayer1().getSelectedCards().stream().allMatch(card -> card.getHp() <= 0)) {
            System.out.println(battle.getPlayer2().getName() + " es el ganador!");
        } else if (battle.getPlayer2().getSelectedCards().stream().allMatch(card -> card.getHp() <= 0)) {
            System.out.println(battle.getPlayer1().getName() + " es el ganador!");
        }
    }

}
