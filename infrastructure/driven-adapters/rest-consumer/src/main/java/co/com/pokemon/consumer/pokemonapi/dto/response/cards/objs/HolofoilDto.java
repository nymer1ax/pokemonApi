package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class HolofoilDto {
    private double low;
    private double mid;
    private double high;
    private double market;
    private Double directLow;
}
