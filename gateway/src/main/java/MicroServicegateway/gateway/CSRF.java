package MicroServicegateway.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class CSRF {


        @Bean
        public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
            http.csrf(csrf -> csrf.disable())
                    .authorizeExchange(authorize -> authorize
                            .pathMatchers(HttpMethod.POST, "/**").permitAll()
                            .anyExchange().permitAll()
                    );
            return http.build();
        }
    }

