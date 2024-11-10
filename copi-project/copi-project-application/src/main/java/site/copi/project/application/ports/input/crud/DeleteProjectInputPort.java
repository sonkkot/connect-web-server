package site.copi.project.application.ports.input.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.copi.project.application.ports.output.command.CommandProjectOutputPort;
import site.copi.project.application.ports.output.command.ValidProjectOutputPort;
import site.copi.project.application.ports.output.query.QueryProjectOutputPort;
import site.copi.project.application.usecase.crud.DeleteProjectUseCase;
import site.copi.project.domain.policy.crud.DeleteProjectPolicy;
import site.copi.project.domain.value.ProjectId;
import site.copi.project.domain.value.ProjectWriter;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteProjectInputPort implements DeleteProjectUseCase {
    private final CommandProjectOutputPort commandProjectOutputPort;
    private final QueryProjectOutputPort queryProjectOutputPort;
    private final ValidProjectOutputPort validProjectOutputPort;

    @Override
    public void delete(ProjectId target, ProjectWriter actor) {
        DeleteProjectPolicy.execute(target)
            .loadWriter(queryProjectOutputPort::loadWriter)
            .validAuthority(writer -> validProjectOutputPort.validWriter(writer, actor))
            .remove(commandProjectOutputPort::remove);
    }
}