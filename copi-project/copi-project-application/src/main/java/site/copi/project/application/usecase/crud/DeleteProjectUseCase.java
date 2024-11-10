package site.copi.project.application.usecase.crud;

import site.copi.project.domain.value.ProjectId;
import site.copi.project.domain.value.ProjectWriter;

public interface DeleteProjectUseCase {
    void delete(ProjectId projectId, ProjectWriter projectWriter);
}
