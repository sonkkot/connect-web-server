package site.copi.users.infrastructure.adapter.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.copi.common.infrastructure.database.entity.CopiBaseJpaEntity;
import site.copi.users.domain.value.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "TB_USERS")
@NoArgsConstructor(access = PROTECTED)
public class UserJpaEntity extends CopiBaseJpaEntity {

    @Positive
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

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
    private String languageList;
    @NotBlank
    @Column(nullable = false)
    private String repositoryList;

    @Builder
    public UserJpaEntity(Long id, String email, String nickname, String profileImage, String name, String languageList, String repositoryList) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.name = name;
        this.languageList = languageList;
        this.repositoryList = repositoryList;
    }

    @Builder(builderMethodName = "aggregateBuilder")
    public UserJpaEntity(UserId userId, UserEmail userEmail, UserNickname userNickname, UserProfileImage userProfileImage, UserName userName, UserLanguageList userLanguageList, UserRepositoryList userRepositoryList) {
        this.id = userId.id();
        this.email = userEmail.email();
        this.nickname = userNickname.nickname();
        this.profileImage = userProfileImage.path();
        this.name = userName.name();
        // TODO : parse
//        this.languageList = null;
//        this.repositoryList = null;
    }
}