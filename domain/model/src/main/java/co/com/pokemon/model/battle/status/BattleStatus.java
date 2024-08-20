package co.com.pokemon.model.battle.status;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BattleStatus {
    private String battleId;
    private String currentPlayer;
    private String action;
    private String targetPokemon;
    private String attackerPokemon;
    private int targetPokemonHp;
    private int attackerPokemonHp;
    private boolean isBattleFinished;
    private int player1Score;
    private int player2Score;
    private String winner;
}
