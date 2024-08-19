package co.com.pokemon.usecase.battle.manager;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.usecase.battle.prepare.PrepareBattleUseCase;
import co.com.pokemon.usecase.exceptions.BattleInProgressException;
import co.com.pokemon.usecase.exceptions.BattleNotFoundException;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class BattleManagerUseCase {
    private Battle currentBattle;
    private final PrepareBattleUseCase prepareBattleUseCase;

    public void createBattle(Player player1, Player player2) {
        if (currentBattle != null && !currentBattle.isFinished()) {
            throw new BattleInProgressException("Ya hay una batalla en curso.");
        }
        currentBattle = Battle.builder().player1(player1).player2(player2).build();
        prepareBattleUseCase.execute(currentBattle, player1, player2, 10);
        currentBattle = currentBattle.toBuilder().player1(player1).player2(player2).build();
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
