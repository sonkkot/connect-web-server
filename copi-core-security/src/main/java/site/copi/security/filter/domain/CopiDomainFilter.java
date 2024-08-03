package site.copi.security.filter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import site.copi.security.filter.base.CopiBaseSecurity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CopiDomainFilter {
    private final List<CopiApiFilterProvider> copiApiFilterProviderList;
    private final CopiBaseSecurity copiBaseSecurity;

    public SecurityFilterChain doFilterChain(HttpSecurity http) throws Exception {
        copiApiFilterProviderList.forEach(c -> {
            try {
                http
                    .securityMatcher(c.targetApi())
                    .with(copiBaseSecurity, CopiBaseSecurity::active)

                    .authorizeHttpRequests(auth -> auth
                        .requestMatchers(c.allowList()).permitAll()
                        .requestMatchers(c.authenticatedEndPoints()).authenticated()
                        .anyRequest().denyAll()
                    )

                    .build();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return http.build();
    }
}