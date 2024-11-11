package site.copi.project.domain.policy.crud;

import site.copi.project.domain.aggregate.ProjectAggregate;
import site.copi.project.domain.aggregate.ProjectUpdateAggregate;
import site.copi.project.domain.value.ProjectId;
import site.copi.project.domain.value.ProjectWriter;

import java.util.function.Consumer;
import java.util.function.Function;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredNotNull;

public final class UpdateProjectPolicy {
    private final ProjectUpdateAggregate projectUpdateAggregate;
    private final ProjectId projectId;
    private ProjectAggregate projectAggregate;

    private UpdateProjectPolicy(ProjectUpdateAggregate projectUpdateAggregate) {
        this.projectUpdateAggregate = requiredNotNull(projectUpdateAggregate);
        this.projectId = projectUpdateAggregate.projectId();
    }

    public static UpdateProjectPolicy execute(ProjectUpdateAggregate projectUpdateAggregate) {
        return new UpdateProjectPolicy(projectUpdateAggregate);
    }

    public UpdateProjectPolicy load(final Function<ProjectId, ProjectAggregate> load) {
        this.projectAggregate = load.apply(this.projectId);
        return this;
    }

    public UpdateProjectPolicy validAuthority(final Consumer<ProjectWriter> validAuthority) {
        validAuthority.accept(this.projectAggregate.getProjectWriter());
        return this;
    }

    public UpdateProjectPolicy update() {
        this.projectAggregate.update(this.projectUpdateAggregate.projectTitle());
        this.projectAggregate.update(this.projectUpdateAggregate.projectContent());
        this.projectAggregate.update(this.projectUpdateAggregate.projectStackList());

        return this;
    }

    public <T> T persist(final Function<ProjectAggregate, T> persist) {
        return persist.apply(this.projectAggregate);
    }
}