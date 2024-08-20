package co.com.pokemon.usecase.battle.manager;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.usecase.battle.prepare.PrepareBattleUseCase;
import co.com.pokemon.usecase.exceptions.BattleInProgressException;
import co.com.pokemon.usecase.exceptions.BattleNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class BattleManagerUseCase {
    public static final int NUMBER_OF_CARDS = 3;
    private Battle currentBattle;
    private final PrepareBattleUseCase prepareBattleUseCase;

    public Battle createBattle(Player player1, Player player2) {
        if (currentBattle != null && !currentBattle.isFinished()) {
            throw new BattleInProgressException("Ya hay una batalla en curso.");
        }
        currentBattle = new Battle(player1, player2);
        prepareBattleUseCase.execute(currentBattle, player1, player2, NUMBER_OF_CARDS);
        currentBattle = new Battle(player1, player2);
        return currentBattle;
    }

    public Battle getCurrentBattle() {
        if (currentBattle == null) {
            throw new BattleNotFoundException("No hay ninguna batalla en curso.");
        }
        return currentBattle;
    }

    public void endBattle() {
        if (currentBattle != null) {
            currentBattle.setFinished(true);
            currentBattle = null;
        }
    }

    public boolean isBattleInProgress() {
        return currentBattle != null && !currentBattle.isFinished();
    }
}
