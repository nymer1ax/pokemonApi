package co.com.pokemon.jpa.battle;

import org.springframework.data.repository.query.Param;


import co.com.pokemon.model.battle.report.BattleMovement;
import co.com.pokemon.model.battle.report.BattleReport;
import co.com.pokemon.model.battle.report.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface BattleDataRepository extends CrudRepository<BattleDataEntity, Long>, QueryByExampleExecutor<BattleDataEntity> {
    @Query(value = "SELECT * FROM battles WHERE finished = false ORDER BY start_time DESC LIMIT 1", nativeQuery = true)
    BattleDataEntity findLatestUnfinishedBattle();

    Optional<BattleDataEntity> findByBattleId(String battleId);

    @Query("SELECT new co.com.pokemon.model.battle.report.BattleReport(b.battleId, p1.name, p2.name, b.startTime, b.endTime, b.winner) " +
            "FROM BattleDataEntity b " +
            "JOIN PlayerDataEntity p1 ON b.player1Id = p1.name " +
            "JOIN PlayerDataEntity p2 ON b.player2Id = p2.name " +
            "WHERE b.battleId = :battleId")
    Optional<BattleReport> findBasicReportByBattleId(@Param("battleId") String battleId);


    @Query("SELECT new co.com.pokemon.model.battle.report.BattleMovement(m.movement, m.attackerCard, m.targetCard, m.attackerCardHp, m.targetCardHp, m.player) " +
            "FROM BattleMovementDataEntity m " +
            "WHERE m.battleId = :battleId")
    List<BattleMovement> findMovementsByBattleId(@Param("battleId") String battleId);

    @Query("SELECT new co.com.pokemon.model.battle.report.Card(pcd.cardId, pcd.name, pcd.type, pcd.hp, pcd.maxHp) " +
            "FROM PlayerCardDataEntity pc " +
            "JOIN PokemonCardDataEntity pcd ON pc.cardId = pcd.cardId " +
            "WHERE pc.battleId = :battleId")
    List<Card> findCardsByBattleId(@Param("battleId") String battleId);











}
