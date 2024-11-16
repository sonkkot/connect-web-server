package site.copi.security.oauth2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CopiGithubOAuth2UserService extends DefaultOAuth2UserService {
    private static final RestClient REST_CLIENT = RestClient.create();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final var user = super.loadUser(userRequest);

        return super.loadUser(userRequest);
    }

    private void getUserInfo(final OAuth2UserRequest userRequest) {
        final var body = REST_CLIENT
            .get()
            .uri("https://api.github.com/user/emails")
            .header("Authorization", "Bearer " + userRequest.getAccessToken().getTokenValue())
            .retrieve()
            .body(Object.class);
    }
}