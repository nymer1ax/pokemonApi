package co.com.pokemon.model.battle;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Battle {
    private Player player1;
    private Player player2;
    private Set<PokemonCard> selectedCards;
}
