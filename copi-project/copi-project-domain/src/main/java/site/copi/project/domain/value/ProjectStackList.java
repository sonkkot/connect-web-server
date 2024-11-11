package site.copi.project.domain.value;

import java.util.List;

public record ProjectStackList(
    List<ProjectStack> list
) {
    public void update(ProjectStackList projectStackList) {
        this.list.clear();
        this.list.addAll(projectStackList.list);
    }
}