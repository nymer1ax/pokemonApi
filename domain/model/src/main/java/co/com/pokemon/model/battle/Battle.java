package co.com.pokemon.model.battle;

import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Battle {
    private String id;
    private Player player1;
    private Player player2;
    private Player currentTurn;
    private PokemonCard activePokemonPlayer1;
    private PokemonCard activePokemonPlayer2;
    private boolean isFinished;
    private Player winner;
    private int player1Score;
    private int player2Score;

    public Battle(Player player1, Player player2) {
        this.id = UUID.randomUUID().toString();
        this.player1 = player1;
        this.player2 = player2;
        this.currentTurn = player1;

        this.activePokemonPlayer1 = getFirstPokemon(player1);
        this.activePokemonPlayer2 = getFirstPokemon(player2);

        this.player1Score = getSelectedCardsCount(player1);
        this.player2Score = getSelectedCardsCount(player2);

        this.isFinished = false;
    }

    private PokemonCard getFirstPokemon(Player player) {
        return Optional.ofNullable(player.getSelectedCards())
                .filter(cards -> !cards.isEmpty())
                .map(cards -> cards.get(0))
                .orElse(null);
    }

    private int getSelectedCardsCount(Player player) {
        return Optional.ofNullable(player.getSelectedCards())
                .map(List::size)
                .orElse(0);
    }


    public void setActivePokemonForPlayer(Player player, PokemonCard newPokemon) {
        if (player.getSelectedCards().contains(newPokemon)) {
            if (player.equals(player1)) {
                this.activePokemonPlayer1 = newPokemon;
            } else if (player.equals(player2)) {
                this.activePokemonPlayer2 = newPokemon;
            }
        }
    }

    public void switchTurn() {
        currentTurn = (currentTurn == player1) ? player2 : player1;
    }

    public PokemonCard getActivePokemonForPlayer(Player player) {
        return player.equals(player1) ? activePokemonPlayer1 : activePokemonPlayer2;
    }

    public void removeDefeatedPokemon(Player player, PokemonCard defeatedPokemon) {
        player.getSelectedCards().remove(defeatedPokemon);
        updateScore(player);
    }

    public boolean hasPlayerLost(Player player) {
        return player.getSelectedCards().isEmpty();
    }

    public void updateScore(Player player) {
        if (player.equals(player1)) {
            player1Score = getSelectedCardsCount(player1);
        } else if (player.equals(player2)) {
            player2Score = getSelectedCardsCount(player2);
        }
    }

    public int getScore(Player player) {
        return player.equals(player1) ? player1Score : player2Score;
    }

    public PokemonCard getNextAvailablePokemon(Player player) {
        return player.getSelectedCards().stream()
                .filter(card -> card.getHp() > 0)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("El jugador %s no tiene más Pokémon disponibles.", player.getName())));
    }
}
