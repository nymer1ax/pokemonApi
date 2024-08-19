package co.com.pokemon.usecase.exceptions;

public class BattleNotFoundException extends RuntimeException {
    public BattleNotFoundException(String message) {
        super(message);
    }
}
