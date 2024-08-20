package co.com.pokemon.jpa.player;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface PlayerDataRepository extends CrudRepository<PlayerDataEntity, Long>, QueryByExampleExecutor<PlayerDataEntity> {
    Optional<PlayerDataEntity> findByName(String name);
}
