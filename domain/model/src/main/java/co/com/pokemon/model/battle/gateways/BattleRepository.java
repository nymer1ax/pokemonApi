package co.com.pokemon.model.battle.gateways;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.report.BattleReport;

import java.util.List;
import java.util.Optional;

public interface BattleRepository {
    void initialize(Battle battle);
    void finishBattle(Battle battle);

    Optional<BattleReport> getReportByBattleId(String battleId);
}
