package co.com.pokemon.usecase.battle.manager;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.usecase.exceptions.BattleInProgressException;
import co.com.pokemon.usecase.exceptions.BattleNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class BattleManagerUseCase {

    private Battle currentBattle;

    public void createBattle(Player player1, Player player2) {
        if (currentBattle != null && !currentBattle.isFinished()) {
            throw new BattleInProgressException("Ya hay una batalla en curso.");
        }
        currentBattle = new Battle(player1, player2);
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
