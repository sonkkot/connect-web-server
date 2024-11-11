package site.copi.project.application.ports.output.query;

import site.copi.project.domain.aggregate.ProjectAggregate;
import site.copi.project.domain.value.ProjectId;
import site.copi.project.domain.value.ProjectWriter;

import java.util.List;

public interface QueryProjectOutputPort {
    ProjectWriter loadWriter(ProjectId projectId);
    ProjectAggregate load(ProjectId projectId);

    List<ProjectAggregate> load(ProjectWriter projectWriter);
}
