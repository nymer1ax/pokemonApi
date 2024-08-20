package co.com.pokemon.jpa.pokemoncard;

import co.com.pokemon.jpa.playercard.PlayerCardDataEntity;
import co.com.pokemon.model.pokemoncard.PokemonCard;

import java.util.List;
import java.util.stream.Collectors;

public class PokemonCardMapper {

    private PokemonCardMapper() {
    }
        public static PokemonCardDataEntity toPokemonCardDataEntity(PokemonCard card) {
            return PokemonCardDataEntity.builder()
                    .cardId(card.getId())
                    .name(card.getName())
                    .type(card.getType())
                    .hp(card.getHp())
                    .maxHp(card.getMaxHp())
                    .attackName(card.getAttackName())
                    .attackDamage(card.getAttackDamage())
                    .weaknessType(card.getWeaknessType())
                    .resistanceType(card.getResistanceType())
                    .retreatCost(card.getRetreatCost())
                    .build();
        }

        public static PlayerCardDataEntity toPlayerCardDataEntity(String playerId, PokemonCardDataEntity cardEntity, String battleId) {
            return PlayerCardDataEntity.builder()
                    .cardId(cardEntity.getCardId())
                    .playerId(playerId)
                    .battleId(battleId)
                    .build();
        }

    public static List<PokemonCardDataEntity> mapToPokemonCardEntities(List<PokemonCard> pokemonCardList) {
        return pokemonCardList.stream()
                .map(PokemonCardMapper::toPokemonCardDataEntity)
                .collect(Collectors.toList());
    }

}

