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

        log.info("La batalla entre " + player1.getName() + " y " + player2.getName() + " ha comenzado!");
    }

    public BattleStatus nextTurn(Player player, PlayerAction playerActionInput) {
        Battle battle = battleManager.getCurrentBattle();
        BattleStatus status = battleUseCase.executeTurn(battle, player, playerActionInput);

        if (status.isBattleFinished()) {
            declareWinner(battle);
            battleManager.endBattle();
        }

        return status;
    }

    private void declareWinner(Battle battle) {
        if (battle.getPlayer1().getSelectedCards().stream().allMatch(card -> card.getHp() <= 0)) {
            log.info(battle.getPlayer2().getName() + " es el ganador!");
        } else if (battle.getPlayer2().getSelectedCards().stream().allMatch(card -> card.getHp() <= 0)) {
            log.info(battle.getPlayer1().getName() + " es el ganador!");
        }
    }

}
