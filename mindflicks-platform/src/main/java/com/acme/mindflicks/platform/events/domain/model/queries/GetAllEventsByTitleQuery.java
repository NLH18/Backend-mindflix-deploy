package com.acme.mindflicks.platform.events.domain.model.queries;

public record GetAllEventsByTitleQuery(String title) {
    public GetAllEventsByTitleQuery {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("id cannot be null");
    }
}
