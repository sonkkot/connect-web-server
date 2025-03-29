package site.copi.security.oauth2.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static site.copi.security.oauth2.CopiOAuth2Provider.GITHUB;

@Service
@RequiredArgsConstructor
public class CopiDelegatingOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final Map<String, OAuth2UserService<OAuth2UserRequest, OAuth2User>> delegateList = new HashMap<>();
    private final DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
    private final CopiGithubOAuth2UserService githubOauth2UserService;

    @PostConstruct
    public void init() {
        this.delegateList.put(GITHUB.name(), githubOauth2UserService);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final var provider = userRequest.getClientRegistration().getClientName().toUpperCase();
        final var oauth2UserService = delegateList.getOrDefault(provider, defaultOAuth2UserService);
        return oauth2UserService.loadUser(userRequest);
    }
}