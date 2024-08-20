package co.com.pokemon.jpa.player;

import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class PlayerDataRepositoryAdapter implements PlayerRepository {

    private final PlayerDataRepository playerDataRepository;
    @Override
    public Player savePlayer(Player player) {
        playerDataRepository.save(PlayerDataEntity
                .builder()
                        .name(player.getName())
                .build());
        return player;
    }

    @Override
    public Optional<Player> findPlayerByName(String name) {
        Optional<PlayerDataEntity> exist = playerDataRepository.findByName(name);
        if(exist.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(Player.builder().name(name).build());
    }

    @Override
    public List<Player> findAllPlayers() {
        Iterable<PlayerDataEntity> playerEntities = playerDataRepository.findAll();
        List<Player> players = new ArrayList<>();
        playerEntities.forEach(entity -> players.add(Player.builder().name(entity.getName()).build()));
        return players;
    }
}
