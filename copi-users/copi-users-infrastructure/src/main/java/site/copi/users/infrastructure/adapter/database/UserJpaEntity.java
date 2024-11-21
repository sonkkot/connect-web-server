package site.copi.users.infrastructure.adapter.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.copi.common.infrastructure.database.entity.CopiBaseJpaEntity;
import site.copi.users.infrastructure.model.LanguagePoint;
import site.copi.users.infrastructure.model.LanguagePointList;

import java.util.Arrays;
import java.util.stream.IntStream;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;
import static site.copi.users.infrastructure.adapter.database.UserJpaEntity.UserRole.USER_ROLE;

@Getter
@Entity
@Table(name = "TB_USERS")
@NoArgsConstructor(access = PROTECTED)
class UserJpaEntity extends CopiBaseJpaEntity {
    static final String LANGUAGE_REGEX = ",";

    public enum UserRole {
        USER_ROLE(),
        ;
    }

    @Positive
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String oAuth2Id;
    @NotBlank
    @Column(nullable = false)
    private String oAuth2LoginId;

    @NotBlank
    @Column(nullable = false)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String nickname;
    @NotBlank
    @Column(nullable = false)
    private String profileImage;
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String languages;
    @NotBlank
    @Column(nullable = false)
    private String languagePoints;
    @NotNull
    @Enumerated(STRING)
    @Column(nullable = false)
    private UserRole role;

    public static UserJpaEntity init() {
        return UserJpaEntity.builder().build();
    }

    @Builder
    public UserJpaEntity(Long id, String oAuth2Id, String oAuth2LoginId, String email, String nickname, String profileImage, String name, String languages, String languagePoints, UserRole role) {
        this.id = id;
        this.oAuth2Id = oAuth2Id;
        this.oAuth2LoginId = oAuth2LoginId;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.name = name;
        this.languages = languages;
        this.languagePoints = languagePoints;
        this.role = role == null ? USER_ROLE : role;
        // TODO : validation & validMessage
    }

    public LanguagePointList getLanguagePointList() {
        final var languageList = Arrays.asList(this.languages.split(LANGUAGE_REGEX));
        final var languagePointList = Arrays.stream(this.languagePoints.split(LANGUAGE_REGEX)).map(Long::parseLong).toList();

        return new LanguagePointList(
            IntStream.range(0, languageList.size())
                .mapToObj(i -> LanguagePoint.of(
                    languageList.get(i),
                    languagePointList.get(i))
                )
                .toList()
        );
    }
}