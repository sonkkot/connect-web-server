package site.copi.users.infrastructure.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserModel {
    private final Long id;
    private final String oAuth2Id;
    private final String oAuth2LoginId;
    private final String email;
    private final String nickname;
    private final String profileImage;
    private final String name;
    private final LanguagePointList languagePointList;
    private final String role;

    public static UserModel init() {
        return UserModel.builder().build();
    }

    @Builder
    public UserModel(Long id, String oAuth2Id, String oAuth2LoginId, String email, String nickname, String profileImage, String name, LanguagePointList languagePointList, String role) {
        this.id = id;
        this.oAuth2Id = oAuth2Id;
        this.oAuth2LoginId = oAuth2LoginId;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.name = name;
        this.languagePointList = languagePointList;
        this.role = role;
    }
}