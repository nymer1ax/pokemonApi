package co.com.pokemon.usecase.battle;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.action.BattleAction;
import co.com.pokemon.model.battle.status.BattleStatus;
import co.com.pokemon.model.logger.LoggerGateway;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.action.PlayerAction;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.usecase.battle.battle.BattleUseCase;
import co.com.pokemon.usecase.battle.manager.BattleManagerUseCase;
import co.com.pokemon.usecase.battle.prepare.PrepareBattleUseCase;
import co.com.pokemon.usecase.exceptions.NotYourTurnException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BattleLauncherUseCase {
    public static final int numberOfCards = 10;
    private final BattleManagerUseCase battleManager;
    private final BattleUseCase battleUseCase;
    private final PrepareBattleUseCase prepareBattleUseCase;
    private final LoggerGateway log;

    public void startBattle(Player player1, Player player2) {
        battleManager.createBattle(player1, player2);
        Battle battle = battleManager.getCurrentBattle();

        prepareBattleUseCase.execute(battle, player1, player2, numberOfCards);
        battle.setCurrentTurn(player1);

        log.info(String.format("La batalla entre %s y %s ha comenzado!", player1.getName(), player2.getName()));
    }

    public BattleStatus nextTurn(Player player, PlayerAction playerActionInput) {
        Battle battle = battleManager.getCurrentBattle();
        BattleStatus status = battleUseCase.executeTurn(battle, player, playerActionInput);

        if (!battle.getCurrentTurn().equals(player)) {
            log.warn(String.format("No es el turno de %s. Es el turno de %s", player.getName(), battle.getCurrentTurn().getName()));
            throw new NotYourTurnException("No es tu turno.");
        }

        if (status.isBattleFinished()) {
            declareWinner(battle);
            battleManager.endBattle();
        }

        battle.switchTurn();
        log.info("Ahora es el turno de " + battle.getCurrentTurn().getName());

        return status;
    }

    private void declareWinner(Battle battle) {
        boolean player1Lost = battle.getPlayer1().getSelectedCards().stream().allMatch(card -> card.getHp() <= 0);
        boolean player2Lost = battle.getPlayer2().getSelectedCards().stream().allMatch(card -> card.getHp() <= 0);

        if (player1Lost) {
            log.info(String.format("%s es el ganador!", battle.getPlayer2().getName()));
            battle.setWinner(battle.getPlayer2());
            return;
        }

        if (player2Lost) {
            log.info(String.format("%s es el ganador!", battle.getPlayer1().getName()));
            battle.setWinner(battle.getPlayer1());
        }
    }


}
