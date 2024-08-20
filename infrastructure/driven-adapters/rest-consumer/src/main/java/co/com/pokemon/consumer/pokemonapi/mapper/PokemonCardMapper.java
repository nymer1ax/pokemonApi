package co.com.pokemon.consumer.pokemonapi.mapper;

import co.com.pokemon.consumer.pokemonapi.dto.response.cards.PokemonCardApiDto;
import co.com.pokemon.model.pokemoncard.PokemonCard;

public class PokemonCardMapper {
    public static PokemonCard toPokemonCard(PokemonCardApiDto apiDto) {
        return PokemonCard.builder()
                .id(apiDto.getId())
                .name(apiDto.getName())
                .type(determineType(apiDto))
                .hp(determineHp(apiDto))
                .maxHp(determineHp(apiDto))
                .attackName(determineAttackName(apiDto))
                .attackDamage(determineAttackDamage(apiDto))
                .weaknessType(determineWeaknessType(apiDto))
                .resistanceType(determineResistanceType(apiDto))
                .retreatCost(determineRetreatCost(apiDto))
                .build();
    }

    private static String determineType(PokemonCardApiDto apiDto) {
        return apiDto.getTypes() != null && !apiDto.getTypes().isEmpty() ? apiDto.getTypes().get(0) : "Unknown";
    }

    private static int determineHp(PokemonCardApiDto apiDto) {
        return apiDto.getHp() != null ? Integer.parseInt(apiDto.getHp()) : 0;
    }

    private static String determineAttackName(PokemonCardApiDto apiDto) {
        return apiDto.getAttacks() != null && !apiDto.getAttacks().isEmpty() ? apiDto.getAttacks().get(0).getName() : null;
    }

    private static int determineAttackDamage(PokemonCardApiDto apiDto) {
        if (apiDto.getAttacks() == null || apiDto.getAttacks().isEmpty()) {
            return 10;
        }
        String damageString = apiDto.getAttacks().get(0).getDamage();
        int damage = (damageString != null && !damageString.isEmpty())
                ? Integer.parseInt(damageString.replaceAll("[^\\d]", ""))
                : 0;
        return damage == 0 ? 10 : damage;
    }

    private static String determineWeaknessType(PokemonCardApiDto apiDto) {
        return apiDto.getWeaknesses() != null && !apiDto.getWeaknesses().isEmpty() ? apiDto.getWeaknesses().get(0).getType() : "None";
    }

    private static String determineResistanceType(PokemonCardApiDto apiDto) {
        return apiDto.getResistances() != null && !apiDto.getResistances().isEmpty() ? apiDto.getResistances().get(0).getType() : "None";
    }

    private static String determineRetreatCost(PokemonCardApiDto apiDto) {
        return apiDto.getRetreatCost() != null ? String.join(", ", apiDto.getRetreatCost()) : "None";
    }


}
