package com.acme.mindflicks.platform.users.interfaces.acl;

import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import com.acme.mindflicks.platform.users.domain.model.valueobjects.Membership;

public interface UserContextFacade {
    Long createUser(String name, String lastName, String userName, String birthDate, String phone, String email, String password, Membership membership);
    User getUserById(Long userId);
}
