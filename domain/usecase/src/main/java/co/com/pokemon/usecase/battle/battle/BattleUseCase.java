package co.com.pokemon.usecase.battle.battle;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.status.BattleStatus;
import co.com.pokemon.model.logger.LoggerGateway;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.action.PlayerAction;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BattleUseCase {

    private final LoggerGateway log;

    public BattleStatus executeTurn(Battle battle, Player currentPlayer, PlayerAction playerActionInput) {
        Player opponent = (currentPlayer == battle.getPlayer1()) ? battle.getPlayer2() : battle.getPlayer1();
        PokemonCard currentPokemon = (currentPlayer == battle.getPlayer1()) ? battle.getActivePokemonPlayer1() : battle.getActivePokemonPlayer2();
        PokemonCard opponentPokemon = playerActionInput.getTargetPokemon();

        log.info("Turno de " + currentPlayer.getName() + ": Acción -> " + playerActionInput.getAction().toString());

        String actionPerformed = playerActionInput.getAction().toString();

        switch (playerActionInput.getAction()) {
            case ATTACK:
                performAttack(currentPokemon, opponentPokemon);
                break;
            case DEFEND:
                performDefend(currentPokemon);
                break;
            case USE_ITEM:
                useItem(currentPokemon, playerActionInput.getItem());
                break;
            case SWITCH_POKEMON:
                switchPokemon(currentPlayer, playerActionInput.getNewActivePokemon(), battle);
                break;
        }

        checkForKnockOut(battle, opponent);

        return BattleStatus.builder()
                .currentPlayer(currentPlayer.getName())
                .action(actionPerformed)
                .targetPokemon(opponentPokemon.getName())
                .targetPokemonHp(opponentPokemon.getHp())
                .attackerPokemonHp(currentPokemon.getHp())
                .isBattleFinished(battle.isFinished())
                .winner(battle.isFinished() ? currentPlayer.getName() : null)
                .build();
    }

    private void performAttack(PokemonCard attacker, PokemonCard defender) {
        int damage = calculateDamage(attacker, defender);
        defender.setHp(defender.getHp() - damage);

        log.info(attacker.getName() + " atacó a " + defender.getName() + " con " + attacker.getAttackName() + " causando " + damage + " de daño.");

        if (defender.getHp() <= 0) {
            defender.setHp(0);
            log.info(defender.getName() + " ha sido derrotado!");
        }

        defender.setDefenseModifier(1.0);
    }

    private int calculateDamage(PokemonCard attacker, PokemonCard defender) {
        int baseDamage = attacker.getAttackDamage();

        if (attacker.getType().equals(defender.getWeaknessType())) {
            baseDamage *= 2;
            log.debug("Ventaja de tipo aplicada: El daño de " + attacker.getType() + " se duplica contra " + defender.getName());
        } else if (attacker.getType().equals(defender.getResistanceType())) {
            baseDamage /= 2;
            log.debug("Resistencia de tipo aplicada: El daño de " + attacker.getType() + " se reduce a la mitad contra " + defender.getName());
        }

        baseDamage *= attacker.getAttackModifier();
        baseDamage *= defender.getDefenseModifier();

        return baseDamage;
    }

    private void performDefend(PokemonCard pokemon) {
        log.info(pokemon.getName() + " se está defendiendo!");
        pokemon.setDefenseModifier(0.5);
    }

    private void useItem(PokemonCard pokemon, String item) {
        log.info("Usando " + item + " en " + pokemon.getName());

        switch (item.toLowerCase()) {
            case "potion":
                int healedHp = Math.min(pokemon.getHp() + 20, pokemon.getMaxHp());
                pokemon.setHp(healedHp);
                log.info(pokemon.getName() + " ha recuperado 20 puntos de vida!");
                break;
            case "attack_boost":
                pokemon.setAttackModifier(1.5);
                log.info("El ataque de " + pokemon.getName() + " ha aumentado temporalmente!");
                break;
            default:
                log.warn("Ítem desconocido: " + item);
                break;
        }
    }

    private void switchPokemon(Player player, PokemonCard newPokemon, Battle battle) {
        if (player == battle.getPlayer1()) {
            battle.setActivePokemonPlayer1(newPokemon);
        } else {
            battle.setActivePokemonPlayer2(newPokemon);
        }
        log.info(player.getName() + " ha cambiado a " + newPokemon.getName() + "!");
    }

    private void checkForKnockOut(Battle battle, Player opponent) {
        if (opponent.getSelectedCards().stream().allMatch(card -> card.getHp() <= 0)) {
            battle.setFinished(true);

            log.info(battle.getPlayer1().getName() + " ha ganado la batalla!");
            log.info(opponent.getName() + " ha perdido la batalla!");
        }
    }
}
