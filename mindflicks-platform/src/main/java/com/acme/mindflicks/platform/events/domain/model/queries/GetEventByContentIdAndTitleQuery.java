package com.acme.mindflicks.platform.events.domain.model.queries;

public record GetEventByContentIdAndTitleQuery(String title, String contentId) {
    public GetEventByContentIdAndTitleQuery {
        if (contentId == null || contentId.isBlank())
            throw new IllegalArgumentException("contentId cannot be null or empty");
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or empty");
    }
}
