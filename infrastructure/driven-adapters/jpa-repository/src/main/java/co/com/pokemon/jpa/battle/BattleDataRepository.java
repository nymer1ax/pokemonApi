package co.com.pokemon.jpa.battle;



import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BattleDataRepository extends CrudRepository<BattleDataEntity, Long>, QueryByExampleExecutor<BattleDataEntity> {
}
