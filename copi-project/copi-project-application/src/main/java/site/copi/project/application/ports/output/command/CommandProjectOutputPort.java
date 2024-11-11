package site.copi.project.application.ports.output.command;

import site.copi.project.domain.aggregate.ProjectAggregate;
import site.copi.project.domain.value.ProjectId;

public interface CommandProjectOutputPort {
    ProjectAggregate persist(ProjectAggregate projectAggregate);

    void remove(ProjectId projectId);
}