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

        BattleMovementDataEntity data = BattleMovementDataEntity
                .builder()
                .battleId(battleStatus.getBattleId())
                .player(battleStatus.getCurrentPlayer())
                .movement(battleStatus.getAction())
                .targetCard(battleStatus.getTargetPokemon())
                .attackerCard(battleStatus.getAttackerPokemon())
                .targetCardHp(battleStatus.getTargetPokemonHp())
                .attackerCardHp(battleStatus.getAttackerPokemonHp())
                .player1Score(battleStatus.getPlayer1Score())
                .player2Score(battleStatus.getPlayer2Score())
                .build();

        battleMovementDataRepository.save(data);
    }
}
