package site.copi.security.users.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record CopiOAuth2ProviderDTO(
    String oAuth2Id,
    String oAuth2LoginId,
    String email,
    String name,
    String nickname,
    String profileImage,
    Map<String, Long> languagePointList
) {
}