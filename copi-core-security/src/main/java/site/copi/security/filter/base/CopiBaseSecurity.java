package site.copi.security.filter.base;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.stereotype.Component;
import site.copi.security.handler.CopiBaseAccessDeniedHandler;
import site.copi.security.handler.CopiBaseAuthenticationEntryPoint;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Component
@RequiredArgsConstructor
public class CopiBaseSecurity extends AbstractHttpConfigurer<CopiBaseSecurity, HttpSecurity> {
    private final CopiCorsSecurity copiCorsSecurity;

    private final CopiBaseAccessDeniedHandler copiBaseAccessDeniedHandler;
    private final CopiBaseAuthenticationEntryPoint copiBaseAuthenticationEntryPoint;
    private boolean flag;

    @Override
    public void init(HttpSecurity http) throws Exception {
        if (flag) {
            http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(STATELESS))
                .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .cors(cors -> cors.configurationSource(copiCorsSecurity.corsConfigurationSource()))
                .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
                    .accessDeniedHandler(copiBaseAccessDeniedHandler)
                    .authenticationEntryPoint(copiBaseAuthenticationEntryPoint));
        }
    }

    public void active() {
        this.flag = true;
    }
}