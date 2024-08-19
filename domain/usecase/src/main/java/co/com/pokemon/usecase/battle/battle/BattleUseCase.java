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

    private static final String TURN_ACTION_LOG = "Turno de %s: Acción -> %s";
    private static final String ATTACK_LOG = "%s atacó a %s con %s causando %d de daño.";
    private static final String DEFEATED_LOG = "%s ha sido derrotado!";
    private static final String TYPE_ADVANTAGE_LOG = "Ventaja de tipo aplicada: El daño de %s se duplica contra %s";
    private static final String TYPE_RESISTANCE_LOG = "Resistencia de tipo aplicada: El daño de %s se reduce a la mitad contra %s";
    private static final String DEFENDING_LOG = "%s se está defendiendo!";
    private static final String USE_ITEM_LOG = "Usando %s en %s";
    private static final String HP_RECOVERED_LOG = "%s ha recuperado 20 puntos de vida!";
    private static final String ATTACK_BOOST_LOG = "El ataque de %s ha aumentado temporalmente!";
    private static final String UNKNOWN_ITEM_LOG = "Ítem desconocido: %s";
    private static final String SWITCH_POKEMON_LOG = "%s ha cambiado a %s!";
    private static final String BATTLE_WON_LOG = "%s ha ganado la batalla!";
    private static final String BATTLE_LOST_LOG = "%s ha perdido la batalla!";

    public BattleStatus executeTurn(Battle battle, Player currentPlayer, PlayerAction playerActionInput) {
        Player opponent = (currentPlayer.equals(battle.getPlayer1())) ? battle.getPlayer2() : battle.getPlayer1();
        PokemonCard currentPokemon = (currentPlayer.equals(battle.getPlayer1())) ? battle.getActivePokemonPlayer1() : battle.getActivePokemonPlayer2();
        PokemonCard opponentPokemon = (currentPlayer.equals(battle.getPlayer1())) ? battle.getActivePokemonPlayer2() : battle.getActivePokemonPlayer1();

        log.info(String.format(TURN_ACTION_LOG, currentPlayer.getName(), playerActionInput.getAction().toString()));

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

        log.info(String.format(ATTACK_LOG, attacker.getName(), defender.getName(), attacker.getAttackName(), damage));

        if (defender.getHp() <= 0) {
            defender.setHp(0);
            log.info(String.format(DEFEATED_LOG, defender.getName()));
        }

        defender.setDefenseModifier(1.0);
    }

    private int calculateDamage(PokemonCard attacker, PokemonCard defender) {
        int baseDamage = attacker.getAttackDamage();

        if (attacker.getType().equals(defender.getWeaknessType())) {
            baseDamage *= 2;
            log.debug(String.format(TYPE_ADVANTAGE_LOG, attacker.getType(), defender.getName()));
        } else if (attacker.getType().equals(defender.getResistanceType())) {
            baseDamage /= 2;
            log.debug(String.format(TYPE_RESISTANCE_LOG, attacker.getType(), defender.getName()));
        }

        baseDamage *= (int) attacker.getAttackModifier();
        baseDamage *= (int) defender.getDefenseModifier();

        return baseDamage;
    }

    private void performDefend(PokemonCard pokemon) {
        log.info(String.format(DEFENDING_LOG, pokemon.getName()));
        pokemon.setDefenseModifier(0.5);
    }

    private void useItem(PokemonCard pokemon, String item) {
        log.info(String.format(USE_ITEM_LOG, item, pokemon.getName()));

        switch (item.toLowerCase()) {
            case "potion":
                int healedHp = Math.min(pokemon.getHp() + 20, pokemon.getMaxHp());
                pokemon.setHp(healedHp);
                log.info(String.format(HP_RECOVERED_LOG, pokemon.getName()));
                break;
            case "attack_boost":
                pokemon.setAttackModifier(1.5);
                log.info(String.format(ATTACK_BOOST_LOG, pokemon.getName()));
                break;
            default:
                log.warn(String.format(UNKNOWN_ITEM_LOG, item));
                break;
        }
    }

    private void switchPokemon(Player player, PokemonCard newPokemon, Battle battle) {
        if (player == battle.getPlayer1()) {
            battle.setActivePokemonPlayer1(newPokemon);
        } else {
            battle.setActivePokemonPlayer2(newPokemon);
        }
        log.info(String.format(SWITCH_POKEMON_LOG, player.getName(), newPokemon.getName()));
    }

    private void checkForKnockOut(Battle battle, Player opponent) {
        if (opponent.getSelectedCards().stream().allMatch(card -> card.getHp() <= 0)) {
            battle.setFinished(true);

            log.info(String.format(BATTLE_WON_LOG, battle.getPlayer1().getName()));
            log.info(String.format(BATTLE_LOST_LOG, opponent.getName()));
        }
    }

}