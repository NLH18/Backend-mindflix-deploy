package com.acme.mindflicks.platform.users.application.internal;

import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import com.acme.mindflicks.platform.users.domain.model.queries.GetUserByIdQuery;
import com.acme.mindflicks.platform.users.domain.model.queries.GetUserByUserNameAndPassword;
import com.acme.mindflicks.platform.users.domain.services.UserQueryService;
import com.acme.mindflicks.platform.users.infrastucture.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findUserById(query.Id());
    }

    @Override
    public Optional<User> handle(GetUserByUserNameAndPassword query) {
        return userRepository.findUserByUserNameAndPassword(query.userName(), query.password());
    }


}
