package site.copi.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;
import site.copi.security.filter.CopiFianlFilter;
import site.copi.security.oauth2.CopiOAuth2Filter;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class CopiFilterConfig {
    //    private final CopiDomainFilter copiDomainFilter;
    private final CopiOAuth2Filter copiOauth2Filter;
    private final CopiFianlFilter copiFianlFilter;

    private final ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public OAuth2AuthorizationRequestResolver authorizationRequestResolver() {
        StringKeyGenerator stringKeyGenerator = () -> UUID.randomUUID().toString();
        DefaultOAuth2AuthorizationRequestResolver defaultOAuth2AuthorizationRequestResolver = new DefaultOAuth2AuthorizationRequestResolver(clientRegistrationRepository, "/oauth2/authorization");
        defaultOAuth2AuthorizationRequestResolver.setAuthorizationRequestCustomizer(r -> r.state(stringKeyGenerator.generateKey()));

        return defaultOAuth2AuthorizationRequestResolver;
    }

    @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }
//    @Bean
//    @Order(HIGHEST_PRECEDENCE)
//    public SecurityFilterChain copiDomainFilterChain(HttpSecurity http) throws Exception {
//        return copiDomainFilter.doFilterChain(http);
//    }

    @Bean
    @Order(1)
    public SecurityFilterChain copiOAuth2FilterChain(HttpSecurity http) throws Exception {
        return copiOauth2Filter.doFilterChain(http, authorizationRequestResolver(),authorizationRequestRepository());
    }

    @Bean
    @Order
    public SecurityFilterChain copiFinalFilterChain(HttpSecurity http) throws Exception {
        return copiFianlFilter.doFilterChain(http);
    }
}