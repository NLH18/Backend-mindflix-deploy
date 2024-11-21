package com.acme.mindflicks.platform.users.domain.model.queries;

public record GetUserByUserNameAndPassword(String userName, String password) {

    public GetUserByUserNameAndPassword {
        if (userName == null || userName.isBlank()) {
            throw new IllegalArgumentException("User name must not be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password must not be null or empty");
        }
    }
}
