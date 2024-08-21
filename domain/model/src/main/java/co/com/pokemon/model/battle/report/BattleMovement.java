package co.com.pokemon.model.battle.report;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BattleMovement {
    private String movement;
    private String attackerCard;
    private String targetCard;
    private int attackerCardHp;
    private int targetCardHp;
    private String player;
}
