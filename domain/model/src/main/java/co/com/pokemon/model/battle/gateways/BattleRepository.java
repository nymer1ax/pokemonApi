package co.com.pokemon.model.battle.gateways;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.report.BattleReport;

import java.util.List;

public interface BattleRepository {
    void initialize(Battle battle);
    List<BattleReport> getReport();

    List<BattleReport> getReportByBattleId(String battleId);
}
