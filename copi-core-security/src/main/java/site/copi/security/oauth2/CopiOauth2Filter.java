package site.copi.security.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import site.copi.security.filter.base.CopiBaseSecurity;
import site.copi.security.oauth2.service.CopiOAuth2UserSuccessHandler;
import site.copi.security.oauth2.service.CopiOauth2UserService;

@Component
@RequiredArgsConstructor
public class CopiOauth2Filter {
    private static final String[] TARGET_API = {
        "/oauth2/**", "/login/**", "/oauth2/login",
    };

    private final CopiBaseSecurity copiBaseSecurity;
    private final CopiOauth2UserService copiOauth2UserService;
    private final CopiOAuth2UserSuccessHandler copiOAuth2UserSuccessHandler;

    public SecurityFilterChain doFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(TARGET_API)
            .with(copiBaseSecurity, CopiBaseSecurity::active)
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(config -> config.userService(copiOauth2UserService))
                .successHandler(copiOAuth2UserSuccessHandler))

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(TARGET_API).permitAll()
                .anyRequest().denyAll())

            .build();
    }
}