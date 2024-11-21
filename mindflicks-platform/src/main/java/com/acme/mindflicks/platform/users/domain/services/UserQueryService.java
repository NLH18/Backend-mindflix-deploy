package com.acme.mindflicks.platform.users.domain.services;

import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import com.acme.mindflicks.platform.users.domain.model.queries.GetUserByIdQuery;
import com.acme.mindflicks.platform.users.domain.model.queries.GetUserByUserNameAndPassword;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle(GetUserByIdQuery query);

    Optional<User> handle(GetUserByUserNameAndPassword query);
}
