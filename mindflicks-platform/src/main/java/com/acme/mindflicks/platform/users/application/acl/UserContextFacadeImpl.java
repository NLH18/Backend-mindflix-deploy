package com.acme.mindflicks.platform.users.application.acl;

import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import com.acme.mindflicks.platform.users.domain.model.commands.CreateNewUserCommand;
import com.acme.mindflicks.platform.users.domain.model.valueobjects.Membership;
import com.acme.mindflicks.platform.users.infrastucture.persistence.jpa.UserRepository;
import com.acme.mindflicks.platform.users.interfaces.acl.UserContextFacade;
import org.springframework.stereotype.Service;

@Service
public class UserContextFacadeImpl implements UserContextFacade {
    private final UserRepository userRepository;

    public UserContextFacadeImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long createUser(String name, String lastName, String userName, String birthDate, String phone, String email, String password, Membership membership) {
        var createNewUserCommand = new CreateNewUserCommand(name, lastName, userName, birthDate, phone, email, password, membership);
        var user = new User(createNewUserCommand);
        user = userRepository.save(user);
        return user.getId();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}

