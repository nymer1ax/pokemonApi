package co.com.pokemon.jpa.battle;


import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.gateways.BattleRepository;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.gateways.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BattleDataRepositoryAdapter
implements BattleRepository
{
    private final BattleDataRepository battleRepository;


    @Override
    public void initialize(Battle battle) {
        this.battleRepository.save(BattleDataEntity
                .builder()
                        .player1Id(battle.getPlayer1().getName())
                        .player2Id(battle.getPlayer2().getName())
                        .startTime(LocalDateTime.now())
                        .battleId(battle.getId())
                        .finished(battle.isFinished())
                .build());
    }
}
