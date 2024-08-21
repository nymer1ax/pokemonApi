package co.com.pokemon.jpa.battle;


import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.gateways.BattleRepository;
import co.com.pokemon.model.battle.report.BattleMovement;
import co.com.pokemon.model.battle.report.BattleReport;
import co.com.pokemon.model.battle.report.Card;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BattleDataRepositoryAdapter
implements BattleRepository
{
    private final BattleDataRepository battleDataRepository;

    @Override
    public void initialize(Battle battle) {
        this.battleDataRepository.save(BattleMapper.toEntity.apply(battle));
    }

    @Override
    public void finishBattle(Battle battle) {
        Optional<BattleDataEntity> battleEntity = this.battleDataRepository.findByBattleId(battle.getId());
        if (battleEntity.isPresent()) {
            battleEntity.get().setFinished(true);
            battleEntity.get().setEndTime(Timestamp.from(Instant.now()));
            battleEntity.get().setWinner(battle.getWinner().getName());
            this.battleDataRepository.save(battleEntity.get());
        }
    }

    @Override
    public Optional<BattleReport> getReportByBattleId(String battleId) {
        Optional<BattleReport> basicReport = battleDataRepository.findBasicReportByBattleId(battleId);
        List<BattleMovement> movements = battleDataRepository.findMovementsByBattleId(battleId);
        List<Card> cards = battleDataRepository.findCardsByBattleId(battleId);

        if (basicReport.isEmpty()) {
            return Optional.empty();
        }

        BattleReport report = basicReport.get();
        report.setMovements(movements);
        report.setPokemonCards(cards);

        return Optional.of(report);
    }


}
