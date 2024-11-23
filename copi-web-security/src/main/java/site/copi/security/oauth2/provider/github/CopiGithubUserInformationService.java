package site.copi.security.oauth2.provider.github;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import site.copi.security.users.dto.CopiOAuth2ProviderDTO;

import java.util.Objects;

@Service
@RequiredArgsConstructor
class CopiGithubUserInformationService {
    // TODO : refactoring..!
    private static final String GITHUB_IDX = "id";
    private static final String GITHUB_NODE_ID = "node_id";
    private static final String GITHUB_LOGIN_ID = "login";
    private static final String GITHUB_AVATAR_URL = "avatar_url";
    private static final String GITHUB_NAME = "name";

    private final GithubUserEmailService githubUserEmailService;
    private final GithubUserLanguageService githubUserLanguageService;

    public CopiOAuth2ProviderDTO createProviderDTO(final OAuth2User oAuth2User, final OAuth2UserRequest userRequest) {
        final var providerDTOBuilder = CopiOAuth2ProviderDTO.builder();
        informationBuilder(oAuth2User, providerDTOBuilder);
        githubUserEmailService.setUp(userRequest, providerDTOBuilder);
        githubUserLanguageService.setUp(oAuth2User, userRequest, providerDTOBuilder);

        return providerDTOBuilder.build();
    }

    private static void informationBuilder(final OAuth2User oAuth2User, final CopiOAuth2ProviderDTO.CopiOAuth2ProviderDTOBuilder builder) {
        final var githubIdx = parse(oAuth2User.getAttribute(GITHUB_IDX));
        final var githubNodeId = parse(oAuth2User.getAttribute(GITHUB_NODE_ID));

        final var githubLoginId = parse(oAuth2User.getAttribute(GITHUB_LOGIN_ID));
        final var githubAvatarUrl = parse(oAuth2User.getAttribute(GITHUB_AVATAR_URL));
        final var githubName = parse(oAuth2User.getAttribute(GITHUB_NAME));

        builder
            .oAuth2Id(githubIdx + githubNodeId)
            .oAuth2LoginId(githubLoginId)
            .profileImage(githubAvatarUrl)
            .name(githubName)
            .nickname(githubName);
    }

    private static String parse(final Object value) {
        return Objects.requireNonNull(value).toString();
    }
}