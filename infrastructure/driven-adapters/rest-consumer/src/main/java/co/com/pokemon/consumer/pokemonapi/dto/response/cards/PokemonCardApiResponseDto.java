package co.com.pokemon.consumer.pokemonapi.dto.response.cards;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonCardApiResponseDto {
    private List<PokemonCardApiDto> data;

}
