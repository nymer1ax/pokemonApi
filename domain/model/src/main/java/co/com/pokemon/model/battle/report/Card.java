package co.com.pokemon.model.battle.report;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Card {
    private String cardId;
    private String name;
    private String type;
    private int hp;
    private int maxHp;
}
