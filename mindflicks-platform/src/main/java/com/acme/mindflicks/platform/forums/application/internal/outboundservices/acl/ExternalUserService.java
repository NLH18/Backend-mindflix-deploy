package com.acme.mindflicks.platform.forums.application.internal.outboundservices.acl;

import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import com.acme.mindflicks.platform.users.domain.model.valueobjects.Membership;
import com.acme.mindflicks.platform.users.interfaces.acl.UserContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalUserService {
        private final UserContextFacade userFacade;

        public ExternalUserService(UserContextFacade userFacade) {
            this.userFacade = userFacade;
        }

        public Optional<User> fetchUserById(Long userId) {
            try {
                var user = userFacade.getUserById(userId);
                return Optional.of(user);
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }

        public Long createUser(String name, String lastName, String userName, String birthDate, String phone, String email, String password, Membership membership) {
            return userFacade.createUser(name, lastName, userName, birthDate, phone, email, password, membership);
        }
}

