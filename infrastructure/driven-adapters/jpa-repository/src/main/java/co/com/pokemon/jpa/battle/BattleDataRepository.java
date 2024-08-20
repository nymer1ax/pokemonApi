package co.com.pokemon.jpa.battle;



import co.com.pokemon.model.battle.report.BattleReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface BattleDataRepository extends CrudRepository<BattleDataEntity, Long>, QueryByExampleExecutor<BattleDataEntity> {
    @Query(value = "SELECT * FROM battles WHERE finished = false ORDER BY start_time DESC LIMIT 1", nativeQuery = true)
    BattleDataEntity findLatestUnfinishedBattle();

    @Query("SELECT new co.com.pokemon.model.battle.report.BattleReport(b.battleId, p1.name, p2.name, b.startTime, b.endTime, " +
            "CASE WHEN b.winner = p1.name THEN p1.name ELSE p2.name END, " +
            "m.movement, m.player1Score, m.player2Score, " +
            "pc1.cardId, pc1.name, pc1.type, m.attackerCardHp, pc1.maxHp, " +
            "pc2.cardId, pc2.name, pc2.type, m.targetCardHp, pc2.maxHp) " +
            "FROM BattleDataEntity b " +
            "JOIN PlayerDataEntity p1 ON b.player1Id = p1.name " +
            "JOIN PlayerDataEntity p2 ON b.player2Id = p2.name " +
            "JOIN BattleMovementDataEntity m ON b.battleId = m.battleId " +
            "JOIN PokemonCardDataEntity pc1 ON m.attackerCard = pc1.cardId " +
            "JOIN PokemonCardDataEntity pc2 ON m.targetCard = pc2.cardId " +
            "ORDER BY b.startTime DESC")
    List<BattleReport> findBattleReports();

    @Query("SELECT new co.com.pokemon.model.battle.report.BattleReport(b.battleId, p1.name, p2.name, b.startTime, b.endTime, " +
            "CASE WHEN b.winner = p1.name THEN p1.name ELSE p2.name END, " +
            "m.movement, m.player1Score, m.player2Score, " +
            "pc1.cardId, pc1.name, pc1.type, m.attackerCardHp, pc1.maxHp, " +
            "pc2.cardId, pc2.name, pc2.type, m.targetCardHp, pc2.maxHp) " +
            "FROM BattleDataEntity b " +
            "JOIN PlayerDataEntity p1 ON b.player1Id = p1.name " +
            "JOIN PlayerDataEntity p2 ON b.player2Id = p2.name " +
            "JOIN BattleMovementDataEntity m ON b.battleId = m.battleId " +
            "JOIN PokemonCardDataEntity pc1 ON m.attackerCard = pc1.cardId " +
            "JOIN PokemonCardDataEntity pc2 ON m.targetCard = pc2.cardId " +
            "WHERE b.battleId = :battleId " +
            "ORDER BY b.startTime DESC")
    List<BattleReport> findBattleReportsByBattleId(@Param("battleId") String battleId);






}
