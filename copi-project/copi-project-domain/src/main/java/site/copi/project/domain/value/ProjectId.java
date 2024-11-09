package site.copi.project.domain.value;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredPositive;

public record ProjectId(
    Long id
) {
    public ProjectId(Long id) {
        this.id = requiredPositive(id);
    }
}