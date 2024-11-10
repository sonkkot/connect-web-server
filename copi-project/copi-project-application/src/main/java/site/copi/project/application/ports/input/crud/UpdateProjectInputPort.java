package site.copi.project.application.ports.input.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.copi.project.application.ports.output.command.CommandProjectOutputPort;
import site.copi.project.application.ports.output.command.ValidProjectOutputPort;
import site.copi.project.application.ports.output.query.QueryProjectOutputPort;
import site.copi.project.application.usecase.crud.UpdateProjectUseCase;
import site.copi.project.domain.aggregate.ProjectUpdateAggregate;
import site.copi.project.domain.policy.crud.UpdateProjectPolicy;
import site.copi.project.domain.value.ProjectWriter;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateProjectInputPort implements UpdateProjectUseCase {
    private final CommandProjectOutputPort commandProjectOutputPort;
    private final QueryProjectOutputPort queryProjectOutputPort;
    private final ValidProjectOutputPort validProjectOutputPort;

    @Override
    public <T> T update(ProjectUpdateAggregate projectUpdateAggregate, ProjectWriter actor) {
        return UpdateProjectPolicy.execute(projectUpdateAggregate)
            .load(queryProjectOutputPort::load)
            .validAuthority(writer -> validProjectOutputPort.validWriter(writer, actor))
            .update()
            .persist(commandProjectOutputPort::persist);
    }
}