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
    public static final String START_LOG = "La batalla entre %s y %s ha comenzado!";
    public static final String NOT_TURN_LOG = "No es el turno de %s. Es el turno de %s";
    public static final String WINNER_LOG = "%s es el ganador!";
    public static final String NEXT_TURN_LOG = "Ahora es el turno de ";

    private final BattleManagerUseCase battleManager;
    private final BattleUseCase battleUseCase;
    private final PrepareBattleUseCase prepareBattleUseCase;
    private final LoggerGateway log;

    public void startBattle(Player player1, Player player2) {
        battleManager.createBattle(player1, player2);
        Battle battle = battleManager.getCurrentBattle();
        battle.setCurrentTurn(player1);
        log.info(String.format(START_LOG, player1.getName(), player2.getName()));
    }

    public BattleStatus nextTurn(Player player, PlayerAction playerActionInput) {
        Battle battle = battleManager.getCurrentBattle();
        BattleStatus status = battleUseCase.executeTurn(battle, player, playerActionInput);

        if (!battle.getCurrentTurn().equals(player)) {
            log.warn(String.format(NOT_TURN_LOG, player.getName(), battle.getCurrentTurn().getName()));
            throw new NotYourTurnException("No es tu turno.");
        }

        if (status.isBattleFinished()) {
            declareWinner(battle);
            battleManager.endBattle();
        }

        battle.switchTurn();
        log.info(NEXT_TURN_LOG + battle.getCurrentTurn().getName());

        return status;
    }

    private void declareWinner(Battle battle) {
        boolean player1Lost =  battle.hasPlayerLost(battle.getPlayer1());
        boolean player2Lost = battle.hasPlayerLost(battle.getPlayer2());

        if (player1Lost) {
            log.info(String.format(WINNER_LOG, battle.getPlayer2().getName()));
            battle.setWinner(battle.getPlayer2());
            return;
        }

        if (player2Lost) {
            log.info(String.format(WINNER_LOG, battle.getPlayer1().getName()));
            battle.setWinner(battle.getPlayer1());
        }
    }


}
