package site.copi.security.oauth2.provider.github;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import site.copi.common.rest.CopiRestClient;
import site.copi.security.users.dto.CopiOAuth2ProviderDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
final class GithubUserLanguageService {
    // TODO : refactoring..!
    private static final String AUTHORIZATION = "Authorization";
    private static final String ORGANIZATION_URL = "organization_url";
    private static final String REPOSITORY_URL = "repos_url";
    private static final String REPOSITORY_LANGUAGES_URL = "languages_url";

    private static final ParameterizedTypeReference<List<Map<String, Object>>> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };
    private static final ParameterizedTypeReference<Map<String, Integer>> REPOSITORY_LANGUAGE_RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };

    public void setUp(final OAuth2User oAuth2User, final OAuth2UserRequest userRequest, final CopiOAuth2ProviderDTO.CopiOAuth2ProviderDTOBuilder builder) {
        final var accessToken = "Bearer " + userRequest.getAccessToken().getTokenValue();

        organizationBuilder(oAuth2User, accessToken, builder);
        languageBuilder(oAuth2User, accessToken, builder);
    }

    private static void languageBuilder(final OAuth2User oAuth2User, final String accessToken, final CopiOAuth2ProviderDTO.CopiOAuth2ProviderDTOBuilder builder) {
        final var repositoryUrl = parse(oAuth2User.getAttribute(REPOSITORY_URL));

        final var repositoryList = CopiRestClient
            .perform(GET, repositoryUrl)
            .addHeader(AUTHORIZATION, accessToken)
            .retrieve(RESPONSE_TYPE);

        final var repositoryPoint = new HashMap<String, Long>();

        repositoryList.forEach(result -> {
            final var url = parse(result.get(REPOSITORY_LANGUAGES_URL));

            final var languagePoint = CopiRestClient
                .perform(GET, url)
                .addHeader(AUTHORIZATION, accessToken)
                .retrieve(REPOSITORY_LANGUAGE_RESPONSE_TYPE);

            languagePoint.forEach((key, value) -> {
                final var point = repositoryPoint.getOrDefault(key, 0L) + value;
                repositoryPoint.put(key, point);
            });
        });

        builder.languagePointList(repositoryPoint);
    }

    private static void organizationBuilder(final OAuth2User oAuth2User, final String accessToken, final CopiOAuth2ProviderDTO.CopiOAuth2ProviderDTOBuilder builder) {
//        final var organizationUrl = parse(oAuth2User.getAttribute(ORGANIZATION_URL));
//
//        final var organizationList = CopiRestClient.perform(GET, organizationUrl)
//            .addHeader(AUTHORIZATION, accessToken)
//            .retrieve(RESPONSE_TYPE);
        // TODO : repository.. 추가
    }

    private static String parse(final Object value) {
        System.out.println(value.toString());
        return Objects.requireNonNull(value).toString();
    }
}