package co.com.pokemon.api;

import co.com.pokemon.api.dto.NextTurnRequest;
import co.com.pokemon.api.dto.PlayerRequest;
import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.report.BattleReport;
import co.com.pokemon.model.battle.status.BattleStatus;
import co.com.pokemon.model.player.Player;
import co.com.pokemon.usecase.battle.BattleLauncherUseCase;
import co.com.pokemon.usecase.battlereport.BattleReportUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pokemon/", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {


    private final BattleLauncherUseCase battleLauncherUseCase;
    private final BattleReportUseCase battleReportUseCase;

    @PostMapping(path = "battle/start/{name}")
    public ResponseEntity<Void> startBattle(@PathVariable(name = "name") String name) {
        battleLauncherUseCase.startBattle(
                Player.builder().name(name).build(),
                Player.builder().name("system").build()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(path = "battle/next-turn")
    public ResponseEntity<BattleStatus> battleMovement(@RequestBody NextTurnRequest request) {
        BattleStatus battleStatus = battleLauncherUseCase.nextTurn(request.getPlayer().toDto(), request.getAction().toDto());
        return ResponseEntity.ok(battleStatus);
    }

    @GetMapping("battle/player/{name}")
    public ResponseEntity<Player> getPlayer(@PathVariable(name = "name") String name) {
        Player player = battleLauncherUseCase.getPlayerInfo(name);
        return ResponseEntity.ok(player);
    }

    @GetMapping("battle")
    public ResponseEntity<Battle> getBattle() {
        Battle battle = battleLauncherUseCase.getActiveBattle();
        return ResponseEntity.ok(battle);
    }

    @GetMapping(path = "battle/report")
    public ResponseEntity<List<BattleReport>> battleReports() {
        List<BattleReport> reports = battleReportUseCase.getBattleReport();
        return ResponseEntity.ok(reports);
    }

    @GetMapping(path = "battle/report/{battleId}")
    public ResponseEntity<List<BattleReport>> battleReportsByBattleId(@PathVariable(name = "battleId") String battleId) {
        List<BattleReport> reports = battleReportUseCase.getBattleReportByBattleId(battleId);
        return ResponseEntity.ok(reports);
    }


}
