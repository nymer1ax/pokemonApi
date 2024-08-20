package co.com.pokemon.jpa.player;

import co.com.pokemon.model.player.Player;

import java.util.function.Function;

public class PlayerMapper {

    private PlayerMapper(){

    }
    public static final Function<Player, PlayerDataEntity> toEntity = player ->
            PlayerDataEntity.builder()
                    .name(player.getName())
                    .build();

    public static final Function<PlayerDataEntity, Player> toModel = entity ->
            Player.builder()
                    .name(entity.getName())
                    .build();
}
