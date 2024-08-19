package co.com.pokemon.model.pokemoncard.gateways;

import co.com.pokemon.model.pokemoncard.PokemonCard;

import java.util.List;

public interface PokemonCardRepository {
    List<PokemonCard> getAll();
}
