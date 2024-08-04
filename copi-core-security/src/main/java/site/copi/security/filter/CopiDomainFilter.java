package site.copi.security.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import site.copi.security.filter.base.CopiBaseSecurity;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

@Component
@RequiredArgsConstructor
public class CopiDomainFilter {
    private static final IntFunction<String[]> TO_ARRAY = String[]::new;

    private final List<CopiApiFilterProvider> copiApiFilterProviderList;
    private final CopiBaseSecurity copiBaseSecurity;

    public SecurityFilterChain doFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(getTagetApi())
            .with(copiBaseSecurity, CopiBaseSecurity::active)

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(getAllowList()).permitAll()
                .requestMatchers(getAuthenticatedEndPoints()).authenticated()
                .anyRequest().denyAll()
            )
            .build();
    }

    private String[] getTagetApi() {
        return copiApiFilterProviderList.stream()
            .flatMap(e -> Arrays.stream(e.targetApi()))
            .toArray(TO_ARRAY);
    }

    private String[] getAllowList() {
        return copiApiFilterProviderList.stream()
            .flatMap(e -> Arrays.stream(e.allowList()))
            .toArray(TO_ARRAY);
    }

    private String[] getAuthenticatedEndPoints() {
        return copiApiFilterProviderList.stream()
            .flatMap(e -> Arrays.stream(e.authenticatedEndPoints()))
            .toArray(TO_ARRAY);
    }
}