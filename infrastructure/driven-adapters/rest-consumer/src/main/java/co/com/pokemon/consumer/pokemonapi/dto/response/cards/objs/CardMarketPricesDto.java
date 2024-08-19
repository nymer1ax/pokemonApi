package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CardMarketPricesDto {
    private double averageSellPrice;
    private double lowPrice;
    private double trendPrice;
    private double reverseHoloSell;
    private double reverseHoloLow;
    private double reverseHoloTrend;
    private double lowPriceExPlus;
    private double avg1;
    private double avg7;
    private double avg30;
    private double reverseHoloAvg1;
    private double reverseHoloAvg7;
    private double reverseHoloAvg30;
}
