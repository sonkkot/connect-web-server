package site.copi.project.domain.specification.count;

import site.copi.project.domain.value.ProjectId;

import java.util.function.Predicate;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.isNotEquals;

public final class ProjectCountSpecification {

    public static Predicate<ProjectId> canIncrement(final ProjectId target) {
        return id -> isNotEquals(id, target);
    }
}