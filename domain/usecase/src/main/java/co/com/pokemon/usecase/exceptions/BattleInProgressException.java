package co.com.pokemon.usecase.exceptions;

public class BattleInProgressException extends RuntimeException {
    public BattleInProgressException(String message) {
        super(message);
    }
}

