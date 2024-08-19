package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PricesDto {
    private HolofoilDto holofoil;
    private HolofoilDto reverseHolofoil;
    private HolofoilDto normal;
}
