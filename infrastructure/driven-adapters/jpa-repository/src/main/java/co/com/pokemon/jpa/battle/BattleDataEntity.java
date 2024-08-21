package co.com.pokemon.jpa.battle;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;



@Entity
@Table(name = "battles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattleDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "battle_id", nullable = false, unique = true)
    private String battleId;

    @Column(name = "player1_id", nullable = false)
    private String player1Id;

    @Column(name = "player2_id", nullable = false)
    private String player2Id;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "winner")
    private String winner;

    @Column(name = "finished", nullable = false)
    private boolean finished;
}
