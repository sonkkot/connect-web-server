package site.copi.project.domain.value;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredString;

public record ProjectTitle(
    String title
) {
    public ProjectTitle(String title) {
        this.title = requiredString(title);
    }
}