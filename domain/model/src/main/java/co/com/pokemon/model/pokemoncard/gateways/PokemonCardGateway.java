package co.com.pokemon.model.pokemoncard.gateways;

import co.com.pokemon.model.pokemoncard.PokemonCard;

import java.util.List;

public interface PokemonCardGateway {
    List<PokemonCard> getAll();
}
