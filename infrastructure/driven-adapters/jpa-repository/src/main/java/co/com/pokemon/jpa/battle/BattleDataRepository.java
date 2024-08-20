package co.com.pokemon.jpa.battle;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface BattleDataRepository extends CrudRepository<BattleDataEntity, Long>, QueryByExampleExecutor<BattleDataEntity> {
    @Query(value = "SELECT * FROM battles WHERE finished = false ORDER BY start_time DESC LIMIT 1", nativeQuery = true)
    BattleDataEntity findLatestUnfinishedBattle();
}
