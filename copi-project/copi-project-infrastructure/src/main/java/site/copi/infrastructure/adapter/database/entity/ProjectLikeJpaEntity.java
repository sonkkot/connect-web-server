package site.copi.infrastructure.adapter.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.copi.project.domain.value.ProjectId;
import site.copi.project.domain.value.ProjectWriter;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "TB_PROJECT")
@NoArgsConstructor(access = PROTECTED)
public class ProjectLikeJpaEntity extends ProjectBaseJpaEntity {

    @Positive
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Positive
    @Column(nullable = false)
    private Long userId;

    @Positive
    @Column(nullable = false)
    private Long projectId;

    @Builder
    public ProjectLikeJpaEntity(Long id, Long userId, Long projectId) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
    }

    @Builder(builderMethodName = "aggregateBuilder")
    public ProjectLikeJpaEntity(ProjectId id, ProjectWriter userId, ProjectId projectId) {
        this.id = id.id();
        this.userId = userId.id();
        this.projectId = projectId.id();
    }
}