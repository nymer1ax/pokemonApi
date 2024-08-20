package co.com.pokemon.jpa.player;

import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class PlayerDataRepositoryAdapter implements PlayerRepository {
    private final PlayerDataRepository playerDataRepository;

    @Override
    public Player savePlayer(Player player) {
        playerDataRepository.save(PlayerMapper.toEntity.apply(player));
        return player;
    }

    @Override
    public Optional<Player> findPlayerByName(String name) {
        return playerDataRepository.findByName(name)
                .map(PlayerMapper.toModel);
    }

    @Override
    public List<Player> findAllPlayers() {
        return StreamSupport.stream(playerDataRepository.findAll().spliterator(), false)
                .map(PlayerMapper.toModel)
                .collect(Collectors.toList());
    }

}
