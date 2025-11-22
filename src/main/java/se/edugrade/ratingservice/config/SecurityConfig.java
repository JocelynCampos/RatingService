package se.edugrade.ratingservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import se.edugrade.ratingservice.converters.JwtAuthConverter;


@Configuration
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Autowired
    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/edufy/v1/ratings/test",
                                "/h2-console/**",
                                "/edufy/v1/ratings/all").permitAll()

                        .requestMatchers(HttpMethod.POST, "/edufy/v1/ratings/add").hasRole("admin")
                        .requestMatchers(HttpMethod.PUT, "/edufy/v1/ratings/update/**").hasRole("admin")
                        //.requestMatchers(HttpMethod.GET,  "/edufy/v1/ratings/**").hasRole("admin")
                        //.requestMatchers(HttpMethod.GET,"/edufy/v1/ratings/**").hasRole("admin")
                        .requestMatchers(HttpMethod.DELETE, "/edufy/v1/ratings/delete/**").hasRole("admin")

                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(auth2 -> auth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));
        return http.build();
    }

}

