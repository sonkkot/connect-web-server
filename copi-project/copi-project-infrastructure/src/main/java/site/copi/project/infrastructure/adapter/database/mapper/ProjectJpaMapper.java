package site.copi.project.infrastructure.adapter.database.mapper;

import org.springframework.stereotype.Service;
import site.copi.project.domain.aggregate.ProjectAggregate;
import site.copi.project.infrastructure.adapter.database.entity.ProjectJpaEntity;

@Service
public class ProjectJpaMapper {
    public ProjectJpaEntity convert(final ProjectAggregate target) {
        return ProjectJpaEntity.aggregateBuilder()
            .projectId(target.getProjectId())
            .projectWriter(target.getProjectWriter())

            .projectTitle(target.getProjectTitle())
            .projectContent(target.getProjectContent())
            .projectStackList(target.getProjectStackList())

            .projectViewCount(target.getProjectViewCount())
            .build();
    }

    public ProjectAggregate convert(final ProjectJpaEntity target) {
        return ProjectAggregate.builder()
            .projectId(target.toProjectId())
            .projectWriter(target.toProjectWriter())

            .projectTitle(target.toProjectTitle())
            .projectContent(target.toProjectContent())
            .projectStackList(target.toProjectStackList())

            .projectViewCount(target.toProjectViewCount())
            .build();
    }
}