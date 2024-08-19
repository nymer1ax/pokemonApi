package co.com.pokemon.jpa.battle;


import co.com.pokemon.jpa.helper.AdapterOperations;
import co.com.pokemon.model.battle.Battle;
import co.com.pokemon.model.battle.gateways.BattleRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class BattleDataRepositoryAdapter extends AdapterOperations<Battle, BattleDataEntity , Long, BattleDataRepository>
implements BattleRepository
{
    protected BattleDataRepositoryAdapter(BattleDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Battle.class));
    }
}
