package site.copi.project.domain.policy.crud;

import site.copi.project.domain.aggregate.ProjectAggregate;
import site.copi.project.domain.value.ProjectWriter;

import java.util.function.Consumer;
import java.util.function.Function;

public final class PersistProjectPolicy {
    private final ProjectAggregate projectAggregate;

    private PersistProjectPolicy(ProjectAggregate projectAggregate) {
        this.projectAggregate = projectAggregate;
    }

    public static PersistProjectPolicy execute(ProjectAggregate projectAggregate) {
        return new PersistProjectPolicy(projectAggregate);
    }

    public PersistProjectPolicy validAuthority(final Consumer<ProjectWriter> validAuthority) {
        validAuthority.accept(this.projectAggregate.getProjectWriter());
        return this;
    }

    public <T> T persist(final Function<ProjectAggregate, T> persist) {
        return persist.apply(this.projectAggregate);
    }
}