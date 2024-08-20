package co.com.pokemon.aop.aspect.battle;

import co.com.pokemon.model.battle.gateways.BattleStatusRepository;
import co.com.pokemon.model.battle.status.BattleStatus;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
@RequiredArgsConstructor
public class NextTurnAspect {

    private final BattleStatusRepository battleStatusRepository;
    @Pointcut("execution(* co.com.pokemon.usecase.battle.battle.BattleUseCase.executeTurn(..))")
    public void executeTurnPointcut() {
    }

    @AfterReturning(pointcut = "executeTurnPointcut()", returning = "result")
    public void logAfterReturningExecuteTurn(BattleStatus result) {
        battleStatusRepository.saveBattleStatus(result);
    }
}
