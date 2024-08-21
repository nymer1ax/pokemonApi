package co.com.pokemon.api.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@RequiredArgsConstructor
public class BasicAuthenticationManager implements AuthenticationManager {

    private final String authUsername;
    private final String authPassword;
    private final String ROLE_USER = "USER";
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication != null && authentication.getCredentials() != null) {
            String userName = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();
            if (userName.equals(authUsername) && password.equals(authPassword)) {
                return UsernamePasswordAuthenticationToken
                        .authenticated(userName, password, List.of(new SimpleGrantedAuthority((ROLE_USER))));
            }
        }
        return authentication;
    }
}