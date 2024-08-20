package co.com.pokemon.jpa.movement;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "battle_movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattleMovementDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "battle_id", nullable = false)
    private String battleId;
    @Column(name = "movement", nullable = false)
    private String movement;
    @Column(name = "target_card", nullable = false)
    private String targetCard;
    @Column(name = "attacker_card", nullable = false)
    private String attackerCard;
    @Column(name = "target_card_hp", nullable = false)
    private int targetCardHp;
    @Column(name = "attacker_card_hp", nullable = false)
    private int attackerCardHp;
    @Column(name = "player", nullable = false)
    private String player;
    @Column(name = "player1_score", nullable = false)
    private int player1Score;
    @Column(name = "player2_score", nullable = false)
    private int player2Score;
    @Column(name = "winner", nullable = false)
    private String winner;
}
