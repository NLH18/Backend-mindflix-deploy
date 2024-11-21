package com.acme.mindflicks.platform.users.interfaces.rest.transform;

import com.acme.mindflicks.platform.users.domain.model.commands.CreateNewUserCommand;
import com.acme.mindflicks.platform.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateNewUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateNewUserCommand(resource.name(), resource.lastName(), resource.userName(), resource.birthDate(),
                                     resource.phone(), resource.email(), resource.password(), resource.membership());
    }
}
