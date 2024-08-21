package co.com.pokemon.jpa.battle;

import co.com.pokemon.model.battle.Battle;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Function;

public class BattleMapper {

    private BattleMapper() {
    }
    public static final Function<Battle, BattleDataEntity> toEntity = battle ->
            BattleDataEntity.builder()
                    .player1Id(battle.getPlayer1().getName())
                    .player2Id(battle.getPlayer2().getName())
                    .startTime(Timestamp.from(Instant.now()))
                    .battleId(battle.getId())
                    .finished(battle.isFinished())
                    .build();
}
