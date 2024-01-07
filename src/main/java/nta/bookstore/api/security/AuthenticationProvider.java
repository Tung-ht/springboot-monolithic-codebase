package nta.bookstore.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider {
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;

    public Authentication getAuthentication(String token) {
        return Optional.ofNullable(token)
                .map(jwtUtils::getSub)
                .map(userDetailsService::loadUserByUsername)
                .map(userDetails ->
                        new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()))
                .orElse(null);
    }
}
