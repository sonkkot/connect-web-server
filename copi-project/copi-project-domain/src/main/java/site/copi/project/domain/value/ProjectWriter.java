package site.copi.project.domain.value;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredPositive;

public record ProjectWriter(
    Long id
) {
    public ProjectWriter(Long id) {
        this.id = requiredPositive(id);
    }
}