package site.copi.project.domain.value;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredString;

public record ProjectContent(
    String content
) {
    public ProjectContent(String content) {
        this.content = requiredString(content);
    }
}