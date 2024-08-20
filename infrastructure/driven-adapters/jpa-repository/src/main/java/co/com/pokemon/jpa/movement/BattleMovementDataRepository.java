package co.com.pokemon.jpa.movement;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BattleMovementDataRepository extends CrudRepository<BattleMovementDataEntity, Long>, QueryByExampleExecutor<BattleMovementDataEntity> {
}
