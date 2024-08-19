package co.com.pokemon.api.dto;

import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerRequest {
    private String name;
    private List<PokemonCard> selectedCards;

    public Player toDto() {
        return Player.builder()
                .name(name)
                .selectedCards(selectedCards)
                .build();
    }
}
