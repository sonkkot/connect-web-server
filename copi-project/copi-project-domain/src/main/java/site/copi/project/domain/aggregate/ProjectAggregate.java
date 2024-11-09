package site.copi.project.domain.aggregate;

import lombok.Getter;
import site.copi.project.domain.value.*;

import static site.copi.project.domain.specification.count.ProjectCountSpecification.canIncrement;
import static site.copi.project.domain.specification.value.ProjectValueSpecification.ifNullAction;
import static site.copi.project.domain.specification.value.ProjectValueSpecification.requiredNotNull;

@Getter
public class ProjectAggregate {
    private final ProjectId projectId;
    private final ProjectWriter projectWriter;
    private final ProjectStackList projectStackList;

    private ProjectTitle projectTitle;
    private ProjectContent projectContent;

    private ProjectViewCount projectViewCount;
    private ProjectLikeCount projectLikeCount;

    public ProjectAggregate(ProjectId projectId, ProjectWriter projectWriter, ProjectStackList projectStackList, ProjectTitle projectTitle, ProjectContent projectContent, ProjectViewCount projectViewCount, ProjectLikeCount projectLikeCount) {
        this.projectId = projectId;
        this.projectStackList = projectStackList;
        this.projectWriter = requiredNotNull(projectWriter);
        this.projectTitle = requiredNotNull(projectTitle);
        this.projectContent = requiredNotNull(projectContent);
        this.projectViewCount = ifNullAction(projectViewCount, ProjectViewCount::init);
        this.projectLikeCount = ifNullAction(projectLikeCount, ProjectLikeCount::init);
    }

    public void incrementViewCount(final ProjectId target) {
        if (canIncrement(this.projectId).test(target)) {
            this.projectViewCount = this.projectViewCount.increment();
        }
    }

    public void incrementLikeCount(final ProjectId target) {
        if (canIncrement(this.projectId).test(requiredNotNull(target))) {
            this.projectLikeCount = this.projectLikeCount.increment();
        }
    }

    public void update(final ProjectContent projectContent) {
        this.projectContent = requiredNotNull(projectContent);
    }

    public void update(final ProjectTitle projectTitle) {
        this.projectTitle = requiredNotNull(projectTitle);
    }
}