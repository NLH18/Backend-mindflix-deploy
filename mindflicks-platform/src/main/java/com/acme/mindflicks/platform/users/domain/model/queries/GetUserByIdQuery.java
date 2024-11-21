package com.acme.mindflicks.platform.users.domain.model.queries;

public record GetUserByIdQuery(Long Id) {
    public GetUserByIdQuery {
        if (Id == null ) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
