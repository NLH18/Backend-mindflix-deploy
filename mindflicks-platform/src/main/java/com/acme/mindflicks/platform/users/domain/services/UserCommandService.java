package com.acme.mindflicks.platform.users.domain.services;

import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import com.acme.mindflicks.platform.users.domain.model.commands.CreateNewUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateNewUserCommand command);
}
