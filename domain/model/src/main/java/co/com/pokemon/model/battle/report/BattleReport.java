package co.com.pokemon.model.battle.report;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BattleReport {
    private String battleId;
    private String player1;
    private String player2;
    private Timestamp startTime;
    private Timestamp endTime;
    private String winner;
    private List<BattleMovement> movements;
    private List<Card> pokemonCards;

    public BattleReport(String battleId, String player1, String player2, Timestamp startTime, Timestamp endTime, String winner) {
        this.battleId = battleId;
        this.player1 = player1;
        this.player2 = player2;
        this.startTime = startTime;
        this.endTime = endTime;
        this.winner = winner;
    }

}
