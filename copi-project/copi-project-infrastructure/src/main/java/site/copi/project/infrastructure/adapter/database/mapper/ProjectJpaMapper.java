package site.copi.project.infrastructure.adapter.database.mapper;

import org.springframework.stereotype.Service;
import site.copi.project.infrastructure.adapter.database.entity.ProjectJpaEntity;
import site.copi.project.domain.aggregate.ProjectAggregate;

@Service
public class ProjectJpaMapper {
    public ProjectJpaEntity convert(final ProjectAggregate target) {
        return ProjectJpaEntity.aggregateBuilder()
            .id(target.getProjectId())
            .writer(target.getProjectWriter())

            .title(target.getProjectTitle())
            .content(target.getProjectContent())
            .stackList(target.getProjectStackList())

            .viewCount(target.getProjectViewCount())
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