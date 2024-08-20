package co.com.pokemon.aop.aspect.battle;

import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.gateways.BattleRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class EndBattleAspect {
    private final BattleRepository battleRepository;
    @Pointcut("execution(* co.com.pokemon.usecase.battle.manager.BattleManagerUseCase.finishBattle(..))")
    public void finishBattlePointcut() {
    }

    @AfterReturning(pointcut = "finishBattlePointcut()", returning = "finishedBattle")
    public void afterEndBattle(Battle finishedBattle) {
        if (finishedBattle != null) {
            battleRepository.finishBattle(finishedBattle);
        }
    }

}
