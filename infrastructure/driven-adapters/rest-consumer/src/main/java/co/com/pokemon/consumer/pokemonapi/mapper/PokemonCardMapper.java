package co.com.pokemon.consumer.pokemonapi.mapper;

import co.com.pokemon.consumer.pokemonapi.dto.response.cards.PokemonCardApiDto;
import co.com.pokemon.model.pokemoncard.PokemonCard;

public class PokemonCardMapper {
    public static PokemonCard toPokemonCard(PokemonCardApiDto apiDto) {
        PokemonCard card = new PokemonCard();
        card.setId(apiDto.getId());
        card.setName(apiDto.getName());
        card.setType(apiDto.getTypes() != null && !apiDto.getTypes().isEmpty() ? apiDto.getTypes().get(0) : "Unknown");
        card.setHp(apiDto.getHp() != null ? Integer.parseInt(apiDto.getHp()) : 0);

        if (apiDto.getAttacks() != null && !apiDto.getAttacks().isEmpty()) {
            String damageString = apiDto.getAttacks().get(0).getDamage();
            int damage = (damageString != null && !damageString.isEmpty())
                    ? Integer.parseInt(damageString.replaceAll("[^\\d]", ""))
                    : 0;
            card.setAttackName(apiDto.getAttacks().get(0).getName());
            card.setAttackDamage(damage);
        }

        card.setWeaknessType(apiDto.getWeaknesses() != null && !apiDto.getWeaknesses().isEmpty() ? apiDto.getWeaknesses().get(0).getType() : "None");
        card.setResistanceType(apiDto.getResistances() != null && !apiDto.getResistances().isEmpty() ? apiDto.getResistances().get(0).getType() : "None");
        card.setRetreatCost(apiDto.getRetreatCost() != null ? String.join(", ", apiDto.getRetreatCost()) : "None");

        return card;
    }
}
