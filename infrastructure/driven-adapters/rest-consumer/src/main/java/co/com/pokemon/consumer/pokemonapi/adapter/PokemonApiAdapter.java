package co.com.pokemon.consumer.pokemonapi.adapter;

import co.com.pokemon.consumer.pokemonapi.service.PokemonApiFacade;
import co.com.pokemon.model.pokemoncard.PokemonCard;
import co.com.pokemon.model.pokemoncard.gateways.PokemonCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonApiAdapter implements PokemonCardRepository {

    private final PokemonApiFacade pokemonApiFacade;

    @Override
    public List<PokemonCard> getAll() {
        return pokemonApiFacade.getPokemonCards();
    }
}
