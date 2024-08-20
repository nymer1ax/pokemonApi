package co.com.pokemon.jpa.battle;


import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.gateways.BattleRepository;
import co.com.pokemon.model.battle.report.BattleReport;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BattleDataRepositoryAdapter
implements BattleRepository
{
    private final BattleDataRepository battleRepository;

    @Override
    public void initialize(Battle battle) {
        this.battleRepository.save(BattleMapper.toEntity.apply(battle));
    }

    @Override
    public void finishBattle(Battle battle) {
        Optional<BattleDataEntity> battleEntity = this.battleRepository.findByBattleId(battle.getId());
        if (battleEntity.isPresent()) {
            battleEntity.get().setFinished(true);
            battleEntity.get().setEndTime(LocalDateTime.now());
            battleEntity.get().setWinner(battle.getWinner().getName());
            this.battleRepository.save(battleEntity.get());
        }
    }
}
