package co.com.pokemon.logger;

import co.com.pokemon.model.logger.LoggerGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class LoggerAdapter implements LoggerGateway {

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void warn(String message) {
        log.warn(message);
    }

    @Override
    public void debug(String message) {
        log.debug(message);
    }
}
