package site.copi.security.oauth2.provider.github;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import site.copi.security.users.repository.CopiOAuth2UserDetailsRepository;

@Service
@RequiredArgsConstructor
public class CopiGithubOAuth2UserService extends DefaultOAuth2UserService {
    private final CopiOAuth2UserDetailsRepository copiOAuth2UserDetailsRepository;
    private final CopiGithubUserInformationService copiGithubUserInformationService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final var oAuth2User = super.loadUser(userRequest);
        final var providerDTO = copiGithubUserInformationService.createProviderDTO(oAuth2User, userRequest);

        copiOAuth2UserDetailsRepository.upsert(providerDTO);

        return oAuth2User;
    }
}