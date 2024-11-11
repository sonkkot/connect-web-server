package site.copi.project.domain.aggregate;

import lombok.Builder;
import site.copi.project.domain.value.ProjectContent;
import site.copi.project.domain.value.ProjectId;
import site.copi.project.domain.value.ProjectStackList;
import site.copi.project.domain.value.ProjectTitle;

import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredNotNull;

public record ProjectUpdateAggregate(
    ProjectId projectId,
    ProjectStackList projectStackList,
    ProjectTitle projectTitle,
    ProjectContent projectContent
) {

    @Builder
    public ProjectUpdateAggregate(
        ProjectId projectId,
        ProjectStackList projectStackList,
        ProjectTitle projectTitle,
        ProjectContent projectContent
    ) {
        this.projectId = requiredNotNull(projectId);
        this.projectStackList = requiredNotNull(projectStackList);
        this.projectTitle = requiredNotNull(projectTitle);
        this.projectContent = requiredNotNull(projectContent);
    }
}