package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class AttackDto {
    private String name;
    private List<String> cost;
    private int convertedEnergyCost;
    private String damage;
    private String text;
}
