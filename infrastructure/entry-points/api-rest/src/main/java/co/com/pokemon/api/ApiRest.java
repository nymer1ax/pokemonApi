package co.com.pokemon.api;
import co.com.pokemon.api.dto.NextTurnRequest;
import co.com.pokemon.api.dto.PlayerActionRequest;
import co.com.pokemon.api.dto.PlayerRequest;
import co.com.pokemon.model.battle.status.BattleStatus;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.model.player.action.PlayerAction;
import co.com.pokemon.usecase.battle.BattleLauncherUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pokemon/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {


    private final BattleLauncherUseCase battleLauncherUseCase;

    @PostMapping(path = "battle/start")
    public void startBatle() {
        battleLauncherUseCase.startBattle(Player.builder().name("user").build(), Player.builder().name("system").build());
    }

    @PostMapping(path = "battle/next-turn")
    public BattleStatus commandName(@RequestBody NextTurnRequest request) {
         return battleLauncherUseCase.nextTurn(request.getPlayer().toDto(), request.getAction().toDto());
    }
}
