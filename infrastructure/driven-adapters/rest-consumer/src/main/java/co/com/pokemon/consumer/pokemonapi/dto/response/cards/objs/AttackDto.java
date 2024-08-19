package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttackDto {
    private String name;
    private List<String> cost;
    private int convertedEnergyCost;
    private String damage;
    private String text;
}
