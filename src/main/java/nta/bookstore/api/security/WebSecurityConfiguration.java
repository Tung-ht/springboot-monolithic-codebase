package nta.bookstore.api.security;

import lombok.RequiredArgsConstructor;
import nta.bookstore.api.common.enumtype.ERole;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final JWTAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    final String[] generalEndpoints = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/images/**",
            "/users/**",
            "/books/**",
            "/categories/**",
            "/orders/**",
            "/shopping-cart-items/**"
    };

    final String[] adminEndpoints = {
            "/articles/approve/**",
            "/articles/pin/**",
            "/articles/unpin/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .authorizeRequests()
                .antMatchers(generalEndpoints).permitAll()
                .antMatchers(adminEndpoints).hasAuthority(ERole.ROLE_ADMIN.name())
                .antMatchers("/articles/unapproved").hasAnyAuthority(ERole.ROLE_ADMIN.name(), ERole.ROLE_USER.name())
                .antMatchers(HttpMethod.GET, "/books/**").permitAll()
                .anyRequest().hasAnyAuthority(ERole.ROLE_ADMIN.name(), ERole.ROLE_USER.name())
                .and()
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
