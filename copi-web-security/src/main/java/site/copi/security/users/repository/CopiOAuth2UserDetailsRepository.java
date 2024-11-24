package site.copi.security.users.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.copi.security.users.CopiUserDetails;
import site.copi.security.users.dto.CopiOAuth2ProviderDTO;
import site.copi.users.infrastructure.UserRepositoryPort;
import site.copi.users.infrastructure.model.LanguagePoint;
import site.copi.users.infrastructure.model.LanguagePointList;
import site.copi.users.infrastructure.model.UserModel;

@Service
@RequiredArgsConstructor
class CopiOAuth2UserDetailsRepository implements CopiOAuth2UserDetailsPort {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public CopiUserDetails load(String oAuth2Id) {
        final var userModel = userRepositoryPort.loadOrDefault(oAuth2Id);
        return CopiUserDetails.of(userModel);
    }

    @Override
    public void register(CopiOAuth2ProviderDTO dto) {
        userRepositoryPort.save(UserModel.builder()
            .oAuth2Id(dto.oAuth2Id())
            .oAuth2LoginId(dto.oAuth2LoginId())
            .email(dto.email())
            .nickname(dto.nickname())
            .name(dto.name())
            .profileImage(dto.profileImage())
            .languagePointList(convert(dto))
            .build());
    }

    @Override
    public boolean isNewMember(String oAuth2Id) {
        return !userRepositoryPort.exists(oAuth2Id);
    }

    private static LanguagePointList convert(final CopiOAuth2ProviderDTO dto) {
        return new LanguagePointList(
            dto.languagePointList()
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(e -> LanguagePoint.of(e.getKey(), e.getValue()))
                .toList()
        );
    }
}