package site.copi.project.domain.policy.crud;

import site.copi.project.domain.value.ProjectId;
import site.copi.project.domain.value.ProjectWriter;

import java.util.function.Consumer;
import java.util.function.Function;

public final class DeleteProjectPolicy {
    private final ProjectId target;
    private ProjectWriter projectWriter;

    private DeleteProjectPolicy(ProjectId target) {
        this.target = target;
    }

    public static DeleteProjectPolicy execute(ProjectId projectId) {
        return new DeleteProjectPolicy(projectId);
    }

    public DeleteProjectPolicy loadWriter(final Function<ProjectId, ProjectWriter> loadWriter) {
        this.projectWriter = loadWriter.apply(this.target);
        return this;
    }

    public DeleteProjectPolicy validAuthority(final Consumer<ProjectWriter> validAuthority) {
        validAuthority.accept(this.projectWriter);
        return this;
    }

    public void remove(final Consumer<ProjectId> remove) {
        remove.accept(this.target);
    }
}