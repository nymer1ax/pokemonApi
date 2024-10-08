package co.com.pokemon.model.pokemoncard;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id", "name", "type"})
public class PokemonCard {
    private String id;
    private String name;
    private String type;
    private int hp;
    private int maxHp;
    private String attackName;
    private int attackDamage;
    private String weaknessType;
    private String resistanceType;
    private String retreatCost;
    @Builder.Default
    private double defenseModifier = 1.0;

    @Builder.Default
    private double attackModifier = 1.0;
}

