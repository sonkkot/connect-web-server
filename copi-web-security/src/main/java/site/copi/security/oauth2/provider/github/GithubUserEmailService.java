package site.copi.security.oauth2.provider.github;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
import site.copi.common.rest.CopiRestClient;
import site.copi.security.users.dto.CopiOAuth2ProviderDTO;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
final class GithubUserEmailService {
    // TODO : refactoring..!
    private static final String EMAIL_URL = "https://api.github.com/user/emails";

    private static final String GITHUB_PRIMARY_EMAIL = "primary";
    private static final String GITHUB_EMAIL = "email";

    private static final String AUTHORIZATION = "Authorization";

    private static final ParameterizedTypeReference<List<Map<String, Object>>> RESPONSE_TYPE = new ParameterizedTypeReference<>() {
    };

    public void setUp(final OAuth2UserRequest userRequest, final CopiOAuth2ProviderDTO.CopiOAuth2ProviderDTOBuilder builder) {
        final var accessToken = "Bearer " + userRequest.getAccessToken().getTokenValue();

        emailBuilder(accessToken, builder);
    }

    private static void emailBuilder(final String accessToken, final CopiOAuth2ProviderDTO.CopiOAuth2ProviderDTOBuilder dtoBuilder) {
        final var emailResponse = CopiRestClient
            .perform(GET, EMAIL_URL)
            .addHeader(AUTHORIZATION, accessToken)
            .retrieve(RESPONSE_TYPE);

        emailResponse.stream()
            .filter(e -> Boolean.TRUE.equals(e.get(GITHUB_PRIMARY_EMAIL)))
            .findFirst()
            .ifPresent(e -> dtoBuilder.email(String.valueOf(e.get(GITHUB_EMAIL))));
    }
}