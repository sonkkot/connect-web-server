package site.copi.infrastructure.adapter.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.copi.project.domain.value.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "TB_PROJECT")
@NoArgsConstructor(access = PROTECTED)
public class ProjectJpaEntity extends ProjectBaseEntity {
    private static final String STACK_REGEX = ",";

    @Positive
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Positive
    @Column(nullable = false)
    private Long writer;

    @NotBlank
    @Column(nullable = false)
    private String title;
    @NotBlank
    @Column(nullable = false)
    private String content;
    @Column()
    private String stackList;

    @PositiveOrZero
    @Column(nullable = false)
    private int viewCount;

    @Builder
    public ProjectJpaEntity(Long id, Long writer, String title, String content, ProjectStackList stackList, int viewCount) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.stackList = convert(stackList);
        this.viewCount = viewCount;
    }

    @Builder(builderMethodName = "aggregateBuilder")
    public ProjectJpaEntity(ProjectId id, ProjectWriter writer, ProjectTitle title, ProjectContent content, ProjectStackList stackList, ProjectViewCount viewCount) {
        this.id = id == null ? null : id.id();
        this.writer = writer.id();
        this.title = title.title();
        this.content = content.content();
        this.stackList = convert(stackList);
        this.viewCount = viewCount.count();
    }

    private static String convert(ProjectStackList stackList) {
        return stackList.list()
            .stream()
            .map(Enum::name)
            .collect(Collectors.joining(STACK_REGEX));
    }

    public ProjectId toProjectId() {
        return new ProjectId(this.id);
    }

    public ProjectWriter toProjectWriter() {
        return new ProjectWriter(this.writer);
    }

    public ProjectTitle toProjectTitle() {
        return new ProjectTitle(this.title);
    }

    public ProjectContent toProjectContent() {
        return new ProjectContent(this.content);
    }

    public ProjectStackList toProjectStackList() {
        return new ProjectStackList(
            Arrays.stream(this.stackList.split(STACK_REGEX))
                .map(ProjectStack::valueOf)
                .toList()
        );
    }

    public ProjectViewCount toProjectViewCount() {
        return new ProjectViewCount(this.viewCount);
    }
}