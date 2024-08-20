package co.com.pokemon.jpa.pokemoncard;

import co.com.pokemon.jpa.battle.BattleDataRepository;
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

    private final BattleDataRepository battleDataRepository;
    private final PlayerCardDataRepository playerCardDataRepository;
    private final PokemonCardDataRepository pokemonCardDataRepository;

    @Override
    public void saveAll(String playerId, List<PokemonCard> pokemonCardList, String battleId) {
        List<PokemonCardDataEntity> cards = PokemonCardMapper.mapToPokemonCardEntities(pokemonCardList);
        Iterable<PokemonCardDataEntity> savedCards = pokemonCardDataRepository.saveAll(cards);
        savePlayerCard(savedCards, playerId, battleId);
    }

    private void savePlayerCard(Iterable<PokemonCardDataEntity> savedEntities, String playerId, String battleId) {
        List<PlayerCardDataEntity> playerCardEntities = StreamSupport.stream(savedEntities.spliterator(), false)
                .map(entity -> PokemonCardMapper.toPlayerCardDataEntity(playerId, entity, battleId))
                .collect(Collectors.toList());
        playerCardDataRepository.saveAll(playerCardEntities);
    }

}
