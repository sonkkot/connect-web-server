package site.copi.project.application.usecase.crud;

import site.copi.project.domain.aggregate.ProjectAggregate;

public interface CreteProjectUseCase {
    <T> T create(ProjectAggregate projectAggregate);
}
