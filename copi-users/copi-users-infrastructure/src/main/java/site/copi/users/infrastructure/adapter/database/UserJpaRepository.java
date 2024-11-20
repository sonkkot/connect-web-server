package site.copi.users.infrastructure.adapter.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    @Query("SELECT u FROM UserJpaEntity u WHERE u.isDeleted = false AND u.oAuth2Id = :oAuth2Id")
    Optional<UserJpaEntity> findByOAuth2Id(@Param("oAuth2Id") String oAuth2Id);

    default UserJpaEntity loadByOAuth2Id(String oAuth2Id) {
        return this.findByOAuth2Id(oAuth2Id)
            .orElseThrow(() -> new IllegalArgumentException("Not Found User"));
        // TODO : exception..
    }
}