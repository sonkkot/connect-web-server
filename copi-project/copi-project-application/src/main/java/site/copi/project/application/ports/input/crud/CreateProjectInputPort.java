package site.copi.project.application.ports.input.crud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.copi.project.application.ports.output.command.CommandProjectOutputPort;
import site.copi.project.application.ports.output.command.ValidProjectOutputPort;
import site.copi.project.application.usecase.crud.CreteProjectUseCase;
import site.copi.project.domain.aggregate.ProjectAggregate;
import site.copi.project.domain.policy.crud.PersistProjectPolicy;

@Service
@Transactional
@RequiredArgsConstructor
class CreateProjectInputPort implements CreteProjectUseCase {
    private final ValidProjectOutputPort validProjectOutputPort;
    private final CommandProjectOutputPort commandProjectOutputPort;

    @Override
    public ProjectAggregate create(ProjectAggregate projectAggregate) {
        return PersistProjectPolicy.execute(projectAggregate)
            .validAuthority(validProjectOutputPort::validAuthority)
            .persist(commandProjectOutputPort::persist);
    }
}