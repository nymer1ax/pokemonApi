package co.com.pokemon.model.battle;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Battle {
    private Player player1;
    private Player player2;
    private Player winner;
    private PokemonCard activePokemonPlayer1;
    private PokemonCard activePokemonPlayer2;
    private Player currentTurn;
    private boolean isFinished;

    public Battle(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.activePokemonPlayer1 = player1.getSelectedCards().isEmpty() ?  null: player1.getSelectedCards().get(0) ;
        this.activePokemonPlayer2 = player2.getSelectedCards().isEmpty() ?  null: player2.getSelectedCards().get(0);
        this.isFinished = false;
    }

    public void switchTurn() {
        currentTurn = (currentTurn == player1) ? player2 : player1;
    }
}
