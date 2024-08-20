package co.com.pokemon.api;

import co.com.pokemon.api.dto.NextTurnRequest;
import co.com.pokemon.api.dto.PlayerRequest;
import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.status.BattleStatus;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.usecase.battle.BattleLauncherUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pokemon/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {


    private final BattleLauncherUseCase battleLauncherUseCase;

    @PostMapping(path = "battle/start/{name}")
    public void startBatle(@PathVariable String name) {
        battleLauncherUseCase.startBattle(Player.builder()
                        .name(name)
                        .build(),
                Player.builder()
                        .name("system").
                        build());
    }

    @PostMapping(path = "battle/next-turn")
    public BattleStatus battleMovement(@RequestBody NextTurnRequest request) {
         return battleLauncherUseCase.nextTurn(request.getPlayer().toDto(), request.getAction().toDto());
    }

    @GetMapping("battle/player/{name}")
    public Player getPlayer(@PathVariable String name) {
        return battleLauncherUseCase.getPlayerInfo(name);
    }

    @GetMapping("battle")
    public Battle getBattle() {
        return battleLauncherUseCase.getActiveBattle();
    }


}
