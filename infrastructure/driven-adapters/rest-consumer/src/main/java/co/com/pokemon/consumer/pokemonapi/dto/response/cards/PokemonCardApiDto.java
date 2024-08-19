package co.com.pokemon.consumer.pokemonapi.dto.response.cards;

import co.com.pokemon.consumer.pokemonapi.dto.response.cards.objs.*;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PokemonCardApiDto {
    private String id;
    private String name;
    private String supertype;
    private List<String> subtypes;
    private String hp;
    private List<String> types;
    private String evolvesFrom;
    private List<AttackDto> attacks;
    private List<WeaknessDto> weaknesses;
    private List<ResistanceDto> resistances;
    private List<String> retreatCost;
    private SetInfoDto set;
    private String number;
    private String artist;
    private String rarity;
    private String flavorText;
    private List<Integer> nationalPokedexNumbers;
    private ImagesDto images;
    private TcgPlayerDto tcgplayer;
    private CardMarketDto cardmarket;
}
