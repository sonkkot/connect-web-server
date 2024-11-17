package site.copi.project.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.copi.project.infrastructure.adapter.database.mapper.ProjectJpaMapper;
import site.copi.project.infrastructure.adapter.database.repository.ProjectJpaRepository;
import site.copi.project.application.ports.output.command.CommandProjectOutputPort;
import site.copi.project.domain.aggregate.ProjectAggregate;
import site.copi.project.domain.value.ProjectId;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandProjectOutputAdapter implements CommandProjectOutputPort {
    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectJpaMapper projectJpaMapper;

    @Override
    public ProjectAggregate persist(ProjectAggregate projectAggregate) {
        final var projectJpaEntity = projectJpaMapper.convert(projectAggregate);
        final var saved = projectJpaRepository.save(projectJpaEntity);

        return projectJpaMapper.convert(saved);
    }

    @Override
    public void remove(ProjectId projectId) {
        projectJpaRepository.deleteById(projectId.id());
    }
}