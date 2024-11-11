package site.copi.project.application.ports.output.command;

import site.copi.project.domain.value.ProjectWriter;

public interface ValidProjectOutputPort {

    void validAuthority(ProjectWriter projectWriter);

    void validWriter(ProjectWriter writer, ProjectWriter actor);
}
