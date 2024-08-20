package co.com.pokemon.jpa.movement;

import co.com.pokemon.model.battle.status.BattleStatus;

import java.util.function.Function;

public class BattleMovementMapper {
    private BattleMovementMapper() {
    }
    public static final Function<BattleStatus, BattleMovementDataEntity> toEntity = battleStatus ->
            BattleMovementDataEntity.builder()
                    .battleId(battleStatus.getBattleId())
                    .player(battleStatus.getCurrentPlayer())
                    .movement(battleStatus.getAction())
                    .targetCard(battleStatus.getTargetPokemon())
                    .attackerCard(battleStatus.getAttackerPokemon())
                    .targetCardHp(battleStatus.getTargetPokemonHp())
                    .attackerCardHp(battleStatus.getAttackerPokemonHp())
                    .player1Score(battleStatus.getPlayer1Score())
                    .player2Score(battleStatus.getPlayer2Score())
                    .build();
}
