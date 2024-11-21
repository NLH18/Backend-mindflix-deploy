package com.acme.mindflicks.platform.events.domain.model.queries;

public record GetEventByIdQuery(Long id) {
    public GetEventByIdQuery {
        if (id == null)
            throw new IllegalArgumentException("id cannot be null");
    }
}
