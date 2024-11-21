package com.acme.mindflicks.platform.users.interfaces.rest.resources;

import com.acme.mindflicks.platform.users.domain.model.valueobjects.EmailAddress;
import com.acme.mindflicks.platform.users.domain.model.valueobjects.Membership;

import java.text.SimpleDateFormat;
import java.util.Date;

public record UserResource(Long id, String name, String lastName,
                           String userName, String birthDate, String phone,
                           EmailAddress email, String password, Membership membership){}
