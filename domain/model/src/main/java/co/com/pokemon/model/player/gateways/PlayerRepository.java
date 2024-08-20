package co.com.pokemon.model.player.gateways;

import co.com.pokemon.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Player savePlayer(Player player);
    Optional<Player> findPlayerByName(String name);

    List<Player> findAllPlayers();
}
