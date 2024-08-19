package co.com.pokemon.model.logger;

public interface LoggerGateway {
    void info(String message);
    void warn(String message);

    void debug(String message);
}
