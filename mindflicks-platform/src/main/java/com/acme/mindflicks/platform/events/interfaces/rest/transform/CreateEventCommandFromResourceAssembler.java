package com.acme.mindflicks.platform.events.interfaces.rest.transform;

import com.acme.mindflicks.platform.events.domain.model.commands.CreateEventCommand;
import com.acme.mindflicks.platform.events.interfaces.rest.resources.CreateEventResource;

/**
 * Assembler to convert a CreateEventResource to a CreateEventCommand
 */
public class CreateEventCommandFromResourceAssembler {
    public static CreateEventCommand toCommandFromResource(CreateEventResource resource) {
        return new CreateEventCommand(resource.title(),
                resource.description(),
                resource.startDate(),
                resource.endDate(),
                resource.contentId()
        );
    }
}
