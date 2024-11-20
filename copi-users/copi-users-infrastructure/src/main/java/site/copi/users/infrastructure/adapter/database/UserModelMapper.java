package site.copi.users.infrastructure.adapter.database;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.copi.users.infrastructure.model.LanguagePoint;
import site.copi.users.infrastructure.model.UserModel;

import java.util.function.Function;
import java.util.stream.Collectors;

import static site.copi.users.infrastructure.adapter.database.UserJpaEntity.UserRole.valueOf;

@Service
@RequiredArgsConstructor
class UserModelMapper {

    UserModel convert(final UserJpaEntity vo) {
        return UserModel.builder()
            .id(vo.getId())

            .oAuth2Id(vo.getOAuth2Id())
            .oAuth2LoginId(vo.getOAuth2LoginId())

            .email(vo.getEmail())
            .nickname(vo.getNickname())
            .profileImage(vo.getProfileImage())
            .name(vo.getName())

            .languagePointList(vo.getLanguagePointList())
            .role(vo.getRole().name())
            .build();
    }

    UserJpaEntity convert(final UserModel vo) {
        return UserJpaEntity.builder()
            .id((vo.getId() != null) ? vo.getId() : null)

            .oAuth2Id(vo.getOAuth2Id())
            .oAuth2LoginId(vo.getOAuth2LoginId())

            .email(vo.getEmail())
            .nickname((vo.getNickname() != null) ? vo.getNickname() : null)
            .profileImage(vo.getProfileImage())
            .name((vo.getName() != null) ? vo.getName() : null)

            .languages(convertLanguages(vo))
            .languagePoints(convertLanguagePoint(vo))

            .role((vo.getRole() != null) ? valueOf(vo.getRole()) : null)
            .build();
    }

    private static String convertLanguages(final UserModel vo) {
        return convertLanguageData(vo, LanguagePoint::name);
    }

    private static String convertLanguagePoint(final UserModel vo) {
        return convertLanguageData(vo, e -> String.valueOf(e.point()));
    }

    private static String convertLanguageData(final UserModel vo, final Function<LanguagePoint, String> function) {
        return vo.getLanguagePointList()
            .list()
            .stream()
            .map(function)
            .collect(Collectors.joining(UserJpaEntity.LANGUAGE_REGEX));
    }
}