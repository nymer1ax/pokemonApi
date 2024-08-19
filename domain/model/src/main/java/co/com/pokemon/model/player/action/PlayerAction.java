package co.com.pokemon.model.player.action;
import co.com.pokemon.model.battle.action.BattleAction;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class PlayerAction {
    private final BattleAction action;
    private final PokemonCard targetPokemon;  // Usado en ataques
    private final PokemonCard newActivePokemon; // Usado en cambio de Pokémon
    private final String item; 
}
