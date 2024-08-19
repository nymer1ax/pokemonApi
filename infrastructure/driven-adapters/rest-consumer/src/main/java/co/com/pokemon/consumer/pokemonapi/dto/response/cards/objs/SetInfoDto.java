package co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SetInfoDto {
    private String id;
    private String name;
    private String series;
    private int printedTotal;
    private int total;
    private LegalitiesDto legalities;
    private String ptcgoCode;
    private String releaseDate;
    private String updatedAt;
    private SetImagesDto images;
}
