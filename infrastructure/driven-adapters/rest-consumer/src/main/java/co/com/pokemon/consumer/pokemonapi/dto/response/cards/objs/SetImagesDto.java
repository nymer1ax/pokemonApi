package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SetImagesDto {
    private String symbol;
    private String logo;
}
