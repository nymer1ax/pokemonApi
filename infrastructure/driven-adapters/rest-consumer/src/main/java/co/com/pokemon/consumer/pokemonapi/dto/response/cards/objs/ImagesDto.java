package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagesDto {
    private String small;
    private String large;
}
