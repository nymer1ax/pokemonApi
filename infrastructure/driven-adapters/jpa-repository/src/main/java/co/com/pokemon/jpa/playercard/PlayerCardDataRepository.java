package co.com.pokemon.jpa.playercard;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface PlayerCardDataRepository extends CrudRepository<PlayerCardDataEntity, Long>, QueryByExampleExecutor<PlayerCardDataEntity> {
}
