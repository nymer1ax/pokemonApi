package co.com.pokemon.jpa.pokemoncard;

import co.com.pokemon.jpa.battle.BattleDataEntity;
import co.com.pokemon.jpa.battle.BattleDataRepository;
import co.com.pokemon.jpa.player.PlayerDataRepository;
import co.com.pokemon.jpa.playercard.PlayerCardDataEntity;
import co.com.pokemon.jpa.playercard.PlayerCardDataRepository;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.model.pokemoncard.gateways.PokemonCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class PokemonCardDataRepositoryAdapter implements PokemonCardRepository {

    private final PlayerCardDataRepository playerCardDataRepository;
    private final PlayerDataRepository playerDataRepository;
    private final PokemonCardDataRepository pokemonCardDataRepository;
    private final BattleDataRepository battleDataRepository;

    @Override
    public void saveAll(String name, List<PokemonCard> pokemonCardList) {

        String battleId = battleDataRepository.findLatestUnfinishedBattle().getBattleId();

        List<PokemonCardDataEntity> pokemonEntities = pokemonCardList.stream()
                .map(obj -> PokemonCardDataEntity.builder()
                        .cardId(obj.getId())
                        .name(obj.getName())
                        .type(obj.getType())
                        .hp(obj.getHp())
                        .maxHp(obj.getMaxHp())
                        .attackDamage(obj.getAttackDamage())
                        .weaknessType(obj.getWeaknessType())
                        .resistanceType(obj.getResistanceType())
                        .retreatCost(obj.getRetreatCost())
                        .build())
                .collect(Collectors.toList());


        Iterable<PokemonCardDataEntity> savedEntities = pokemonCardDataRepository.saveAll(pokemonEntities);

        List<PlayerCardDataEntity> savedPokemonEntities = StreamSupport.stream(savedEntities.spliterator(), false)
                .map(obj -> PlayerCardDataEntity.builder()
                        .cardId(obj.getCardId())
                        .playerId(name)
                        .battleId(battleId)
                        .build())
                .collect(Collectors.toList());

        playerCardDataRepository.saveAll(savedPokemonEntities);


    }

}
