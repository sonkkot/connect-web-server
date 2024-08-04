package site.copi.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import site.copi.security.filter.base.CopiBaseSecurity;

@Component
@RequiredArgsConstructor
public class CopiFianlFilter {
    private static final String TARGET_API = "/**";
    private static final String[] ALLOW_LIST = {
    };

    private static final String[] AUTHENTICATED = {
    };

    private final CopiBaseSecurity copiBaseSecurity;

    public SecurityFilterChain doFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(TARGET_API)
            .with(copiBaseSecurity, CopiBaseSecurity::active)

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(ALLOW_LIST).permitAll()
                .requestMatchers(AUTHENTICATED).authenticated()
                .anyRequest().denyAll()
            )

            .build();
    }
}