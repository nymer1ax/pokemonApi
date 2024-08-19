package co.com.pokemon.model.player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"name"})
public class Player {
    private String name;
    private List<PokemonCard> selectedCards;
}
