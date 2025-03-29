package site.copi.security.filter.base;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Component
public class CopiCorsSecurity {
    private final List<String> allowedOrigin = List.of("*");
    private final List<String> allowedHeader = List.of("*");
    private final List<String> allowedMethods = List.of(
        GET.name(), POST.name(),
        PUT.name(), PATCH.name(), DELETE.name(),
        OPTIONS.name(), HEAD.name(), "UPGRADE"
    );
    private final List<String> exposedHeader = List.of("Set-Cookie", "Authorization");
    private final Long corsCacheTime = 3_600L;
    private final String targetApi = "/**";

    public CorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(corsCacheTime);

        allowedHeader.forEach(configuration::addAllowedHeader);
        allowedMethods.forEach(configuration::addAllowedMethod);
        exposedHeader.forEach(configuration::addExposedHeader);

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(targetApi, configuration);

        return source;
    }
}