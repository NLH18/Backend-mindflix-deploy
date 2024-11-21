package com.acme.mindflicks.platform.content.interfaces.rest.transform;

import com.acme.mindflicks.platform.content.domain.model.commands.CreateContentCommand;
import com.acme.mindflicks.platform.content.interfaces.rest.resources.CreateContentResource;

public class CreateContentCommandFromResourceAssembler {
    public static CreateContentCommand toCommand(CreateContentResource resource) {
        return new CreateContentCommand(resource.creatorId(),
                resource.type(),
                resource.title(),
                resource.description(),
                resource.category(),
                resource.objective(),
                resource.feeling(),
                resource.image());
    }
}
