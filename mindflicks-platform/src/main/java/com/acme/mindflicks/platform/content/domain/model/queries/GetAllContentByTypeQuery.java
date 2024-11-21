package com.acme.mindflicks.platform.content.domain.model.queries;

public record GetAllContentByTypeQuery(String type) {

    public GetAllContentByTypeQuery {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type must not be null or empty");
        }
    }
}
