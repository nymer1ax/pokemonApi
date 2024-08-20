package co.com.pokemon.jpa.movement;

import co.com.pokemon.model.battle.gateways.BattleStatusRepository;
import co.com.pokemon.model.battle.status.BattleStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BattleMovementDataAdapter implements BattleStatusRepository  {
    private final BattleMovementDataRepository battleMovementDataRepository;
    @Override
    public void saveBattleStatus(BattleStatus battleStatus) {
        battleMovementDataRepository.save(BattleMovementMapper.toEntity.apply(battleStatus));
    }
}
