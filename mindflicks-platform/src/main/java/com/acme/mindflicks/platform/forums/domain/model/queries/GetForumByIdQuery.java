package com.acme.mindflicks.platform.forums.domain.model.queries;

public record GetForumByIdQuery(Long forumId) {
    public GetForumByIdQuery {
        if (forumId == null || forumId <= 0) throw new IllegalArgumentException("Forum id is required.");
    }
}
