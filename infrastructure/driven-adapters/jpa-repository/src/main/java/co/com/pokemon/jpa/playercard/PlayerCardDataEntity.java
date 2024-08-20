package co.com.pokemon.jpa.playercard;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerCardDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id", nullable = false)
    private String playerId;

    @Column(name = "card_id", nullable = false)
    private String cardId;

    @Column(name = "battle_id", nullable = false)
    private String battleId;

}
