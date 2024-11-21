package com.acme.mindflicks.platform.users.interfaces.rest.transform;

import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import com.acme.mindflicks.platform.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

    public static UserResource toResourceFromEntity (User entity){
        return new UserResource(entity.getId(), entity.getName(), entity.getLastName(), entity.getUserName(),
                                entity.getBirthDate(), entity.getPhone(), entity.getEmail(), entity.getPassword(),
                                entity.getMembership());

    }
}
