package site.copi.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import site.copi.security.filter.CopiDomainFilter;
import site.copi.security.filter.CopiFianlFilter;
import site.copi.security.oauth2.CopiOauth2Filter;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
@RequiredArgsConstructor
public class CopiFilterConfig {
    private final CopiDomainFilter copiDomainFilter;
    private final CopiOauth2Filter copiOauth2Filter;
    private final CopiFianlFilter copiFianlFilter;

    @Bean
    @Order(HIGHEST_PRECEDENCE)
    public SecurityFilterChain copiDomainFilterChain(HttpSecurity http) throws Exception {
        return copiDomainFilter.doFilterChain(http);
    }

    @Bean
    @Order(1)
    public SecurityFilterChain copiOAuth2FilterChain(HttpSecurity http) throws Exception {
        return copiOauth2Filter.doFilterChain(http);
    }

    @Bean
    @Order
    public SecurityFilterChain copiFinalFilterChain(HttpSecurity http) throws Exception {
        return copiFianlFilter.doFilterChain(http);
    }
}