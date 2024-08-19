package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TcgPlayerDto {
    private String url;
    private PricesDto prices;
}
