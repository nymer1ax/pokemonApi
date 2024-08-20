package co.com.pokemon.usecase.battlereport;

import co.com.pokemon.model.battle.gateways.BattleRepository;
import co.com.pokemon.model.battle.report.BattleReport;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BattleReportUseCase {
    private final BattleRepository battleRepository;

    public List<BattleReport> getBattleReport(){
        return battleRepository.getReport();
    }

    public List<BattleReport> getBattleReportByBattleId(String battleId){
        return battleRepository.getReportByBattleId(battleId);
    }
}
