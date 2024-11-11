package site.copi.infrastructure.adapter.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.copi.infrastructure.adapter.database.entity.ProjectLikeJpaEntity;

public interface ProjectLikeJpaRepository extends JpaRepository<ProjectLikeJpaEntity, Long> {

}
