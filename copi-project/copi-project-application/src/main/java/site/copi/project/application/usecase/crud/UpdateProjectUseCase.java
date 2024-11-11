package site.copi.project.application.usecase.crud;

import site.copi.project.domain.aggregate.ProjectAggregate;
import site.copi.project.domain.aggregate.ProjectUpdateAggregate;
import site.copi.project.domain.value.ProjectWriter;

public interface UpdateProjectUseCase {
    ProjectAggregate update(ProjectUpdateAggregate projectUpdateAggregate, ProjectWriter actor);
}