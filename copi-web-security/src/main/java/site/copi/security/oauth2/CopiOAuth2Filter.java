package site.copi.security.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import site.copi.security.filter.base.CopiBaseSecurity;
import site.copi.security.oauth2.service.CopiDelegatingOAuth2UserService;
import site.copi.security.oauth2.service.CopiOAuth2UserSuccessHandler;

@Component
@RequiredArgsConstructor
public class CopiOAuth2Filter {
    private static final String[] TARGET_API = {
        "/oauth2/**", "/login/oauth2/**"
    };

    private final CopiBaseSecurity copiBaseSecurity;
    private final CopiOAuth2UserSuccessHandler copiOAuth2UserSuccessHandler;
    private final CopiDelegatingOAuth2UserService copiDelegatingOauth2UserService;

    public SecurityFilterChain doFilterChain(HttpSecurity http, OAuth2AuthorizationRequestResolver oAuth2AuthorizationRequestResolver, AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository) throws Exception {

        return http
            .securityMatcher(TARGET_API)
            .with(copiBaseSecurity, CopiBaseSecurity::active)
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(config -> config.userService(copiDelegatingOauth2UserService))
                .successHandler(copiOAuth2UserSuccessHandler)
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(TARGET_API).permitAll()
                .anyRequest().permitAll())
            .build();
    }
}