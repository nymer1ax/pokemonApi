package co.com.pokemon.model.pokemoncard;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PokemonCard {
    private String id;
    private String name;
    private String type;
    private int hp;
    private String attackName;
    private int attackDamage;
    private String weaknessType;
    private String resistanceType;
    private String retreatCost;
}
