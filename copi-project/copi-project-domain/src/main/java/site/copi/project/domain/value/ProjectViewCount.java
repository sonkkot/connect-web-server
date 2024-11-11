package site.copi.project.domain.value;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredPositive;

public record ProjectViewCount(
    int count
) {
    public ProjectViewCount(int count) {
        this.count = requiredPositive(count);
    }

    public static ProjectViewCount init() {
        return new ProjectViewCount(0);
    }

    public ProjectViewCount increment() {
        return new ProjectViewCount(this.count + 1);
    }
}