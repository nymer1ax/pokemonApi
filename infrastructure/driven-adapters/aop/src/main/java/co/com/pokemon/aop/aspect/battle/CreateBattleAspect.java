package co.com.pokemon.aop.aspect.battle;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.gateways.BattleRepository;
import co.com.pokemon.model.player.gateways.PlayerRepository;
import co.com.pokemon.model.pokemoncard.gateways.PokemonCardRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CreateBattleAspect {

    private final BattleRepository battleRepository;
    private final PlayerRepository playerRepository;
    private final PokemonCardRepository pokemonCardRepository;

    @Pointcut("execution(* co.com.pokemon.usecase.battle.manager.BattleManagerUseCase.createBattle(..))")
    public void createBattlePointcut() {
    }

    @AfterReturning(pointcut = "createBattlePointcut()", returning = "battle")
    public void afterReturningCreateBattle(JoinPoint joinPoint, Battle battle) {
        savePlayers(battle);
        savePokemonCards(battle);
        initializeBattle(battle);
    }

    private void savePlayers(Battle battle) {
        playerRepository.savePlayer(battle.getPlayer1());
        playerRepository.savePlayer(battle.getPlayer2());
    }

    private void savePokemonCards(Battle battle) {
        pokemonCardRepository.saveAll(battle.getPlayer1().getName(), battle.getPlayer1().getSelectedCards(), battle.getId());
        pokemonCardRepository.saveAll(battle.getPlayer2().getName(), battle.getPlayer2().getSelectedCards(), battle.getId());
    }

    private void initializeBattle(Battle battle) {
        battleRepository.initialize(battle);
    }
}
