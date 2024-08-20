package co.com.pokemon.model.battle.report;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder(toBuilder = true)
public class BattleReport {
    private String battleId;
    private String player1;
    private String player2;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String winner;
    private String movement;
    private int player1Score;
    private int player2Score;
    private String attackerCardId;
    private String attackerName;
    private String attackerType;
    private int attackerHp;
    private int attackerMaxHp;
    private String targetCardId;
    private String targetName;
    private String targetType;
    private int targetHp;
    private int targetMaxHp;


    public BattleReport(String battleId, String player1, String player2, LocalDateTime startTime, LocalDateTime endTime,
                        String winner, String movement, int player1Score, int player2Score,
                        String attackerCardId, String attackerName, String attackerType, int attackerHp, int attackerMaxHp,
                        String targetCardId, String targetName, String targetType, int targetHp, int targetMaxHp) {
        this.battleId = battleId;
        this.player1 = player1;
        this.player2 = player2;
        this.startTime = startTime;
        this.endTime = endTime;
        this.winner = winner;
        this.movement = movement;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        this.attackerCardId = attackerCardId;
        this.attackerName = attackerName;
        this.attackerType = attackerType;
        this.attackerHp = attackerHp;
        this.attackerMaxHp = attackerMaxHp;
        this.targetCardId = targetCardId;
        this.targetName = targetName;
        this.targetType = targetType;
        this.targetHp = targetHp;
        this.targetMaxHp = targetMaxHp;
    }
}

