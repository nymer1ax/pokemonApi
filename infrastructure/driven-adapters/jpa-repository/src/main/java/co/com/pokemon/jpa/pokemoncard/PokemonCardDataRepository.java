package co.com.pokemon.jpa.pokemoncard;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

public interface PokemonCardDataRepository extends CrudRepository<PokemonCardDataEntity, Long>, QueryByExampleExecutor<PokemonCardDataEntity> {
    Optional<PokemonCardDataEntity> findByName(String name);

    Boolean existsByCardId(String cardId);
}
