package site.copi.project.domain.value;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredPositive;

public record ProjectLikeCount(
    int count
) {
    public ProjectLikeCount(int count) {
        this.count = requiredPositive(count);
    }

    public static ProjectLikeCount init() {
        return new ProjectLikeCount(0);
    }

    public ProjectLikeCount increment() {
        return new ProjectLikeCount(this.count + 1);
    }
}