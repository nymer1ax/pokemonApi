package co.com.pokemon.model.battle;

import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Battle {

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
        this.player1 = player1;
        this.player2 = player2;
        this.activePokemonPlayer1 = player1.getSelectedCards().isEmpty() ? null : player1.getSelectedCards().get(0);
        this.activePokemonPlayer2 = player2.getSelectedCards().isEmpty() ? null : player2.getSelectedCards().get(0);
        this.isFinished = false;
        this.player1Score = player1.getSelectedCards().isEmpty() ? 0 : player1.getSelectedCards().size();
        this.player2Score = player2.getSelectedCards().isEmpty() ? 0 : player2.getSelectedCards().size();
    }

    /*
     * Método para cambiar el Pokémon activo de un jugador, validando que el nuevo Pokémon esté en la lista de seleccionados.
     */
    public void setActivePokemonForPlayer(Player player, PokemonCard newPokemon) {
        if (!player.getSelectedCards().contains(newPokemon)) {
            return;
        }

        if (player.equals(player1)) {
            this.activePokemonPlayer1 = newPokemon;
        } else if (player.equals(player2)) {
            this.activePokemonPlayer2 = newPokemon;
        }

    }

    public void switchTurn() {
        currentTurn = (currentTurn == player1) ? player2 : player1;
    }

    /*
     Método para obtener el Pokémon activo de un jugador
     */
    public PokemonCard getActivePokemonForPlayer(Player player) {
        return player.equals(player1) ? activePokemonPlayer1 : activePokemonPlayer2;
    }

    /*
     Método para actualizar la lista de Pokémon activos del jugador cuando uno es eliminado
     */
    public void removeDefeatedPokemon(Player player, PokemonCard defeatedPokemon) {
        player.getSelectedCards().remove(defeatedPokemon);
        updateScore(player);
    }

    /*
     Método para verificar si un jugador ha perdido todos sus Pokémon
     */
    public boolean hasPlayerLost(Player player) {
        return player.getSelectedCards().isEmpty();
    }

    /*
     Método para actualizar el score cuando un Pokémon es derrotado
     */
    public void updateScore(Player player) {
        if (player.equals(player1)) {
            player1Score = player.getSelectedCards().size();
        } else if (player.equals(player2)) {
            player2Score = player.getSelectedCards().size();
        }
    }

    /*
     Método para obtener el score de un jugador
     */
    public int getScore(Player player) {
        return player.equals(player1) ? player1Score : player2Score;
    }


    /**
     * Obtiene el siguiente Pokémon disponible que tenga HP > 0.
     *
     * @param player El jugador que está buscando el próximo Pokémon activo.
     * @return El próximo Pokémon disponible que aún tiene HP > 0.
     * @throws IllegalStateException si no hay Pokémon disponibles.
     */
    public PokemonCard getNextAvailablePokemon(Player player) {
        return player.getSelectedCards().stream()
                .filter(card -> card.getHp() > 0)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("El jugador %s no tiene más Pokémon disponibles.", player.getName())));
    }

}
