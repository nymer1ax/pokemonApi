package co.com.pokemon.usecase.battle.report;

import co.com.pokemon.model.battle.gateways.BattleRepository;
import co.com.pokemon.model.battle.report.BattleReport;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class BattleReportUseCase {
    private final BattleRepository battleRepository;
    public BattleReport getReportByBattleId(String battleId) {
        return battleRepository.getReportByBattleId(battleId).orElseThrow();
    }
}
