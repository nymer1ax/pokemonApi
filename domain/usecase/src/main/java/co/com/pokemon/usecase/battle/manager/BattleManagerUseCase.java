package co.com.pokemon.usecase.battle.manager;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.usecase.battle.prepare.PrepareBattleUseCase;
import co.com.pokemon.usecase.exceptions.BattleInProgressException;
import co.com.pokemon.usecase.exceptions.BattleNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class BattleManagerUseCase {
    public static final int NUMBER_OF_CARDS = 3;
    private final PrepareBattleUseCase prepareBattleUseCase;
    private final BattleHolder battleHolder = new BattleHolder();

    public Battle createBattle(Player player1, Player player2) {
        battleHolder.getBattle().ifPresent(battle -> {
            if (!battle.isFinished()) {
                throw new BattleInProgressException("Ya hay una batalla en curso.");
            }
        });

        Battle battle = new Battle(player1, player2);
        prepareBattleUseCase.execute(battle, player1, player2, NUMBER_OF_CARDS);
        battleHolder.setBattle(battle);
        return battle;
    }

    public Battle getCurrentBattle() {
        return battleHolder.getBattle()
                .orElseThrow(() -> new BattleNotFoundException("No hay ninguna batalla en curso."));
    }

    public Battle finishBattle() {
        Battle battle = battleHolder.getBattle().orElse(null);
        if (battle != null) {
            battle.setFinished(true);
        }
        return battle;
    }

    public void endBattle() {
        battleHolder.clear();
    }

    public boolean isBattleInProgress() {
        return battleHolder.getBattle().map(battle -> !battle.isFinished()).orElse(false);
    }

    // Inner static class to hold the current battle
    public static class BattleHolder {
        private Battle battle;

        public Optional<Battle> getBattle() {
            return Optional.ofNullable(battle);
        }

        public void setBattle(Battle battle) {
            this.battle = battle;
        }

        public void clear() {
            this.battle = null;
        }
    }
}

