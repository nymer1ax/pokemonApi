package co.com.pokemon.api.dto;

import co.com.pokemon.model.battle.action.BattleAction;
import co.com.pokemon.model.player.action.PlayerAction;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerActionRequest {
    private  BattleAction action;
    private  PokemonCard newActivePokemon;
    private  String item;

    public PlayerAction toDto() {
        return PlayerAction.builder()
                .action(action)
                .newActivePokemon(newActivePokemon)
                .item(item)
                .build();
    }
}
