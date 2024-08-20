package co.com.pokemon.model.battle.gateways;

import co.com.pokemon.model.battle.status.BattleStatus;

public interface BattleStatusRepository {
    void saveBattleStatus(BattleStatus battleStatus);
}
