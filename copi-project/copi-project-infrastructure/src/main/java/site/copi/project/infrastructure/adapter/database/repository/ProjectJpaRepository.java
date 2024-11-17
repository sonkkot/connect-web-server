package site.copi.project.infrastructure.adapter.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import site.copi.project.infrastructure.adapter.database.entity.ProjectJpaEntity;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ProjectJpaEntity p SET p.isDeleted = true WHERE p.id = :id")
    void deleteById(@NonNull @Param("id") final Long id);

}