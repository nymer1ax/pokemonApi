package co.com.pokemon.model.battle.status;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BattleStatus {
    private String currentPlayer;
    private String action;
    private String targetPokemon;
    private int targetPokemonHp;
    private int attackerPokemonHp;
    private boolean isBattleFinished;
    private String winner;
}
