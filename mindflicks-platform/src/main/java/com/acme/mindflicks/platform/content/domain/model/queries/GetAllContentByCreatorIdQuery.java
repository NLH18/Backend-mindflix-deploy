package com.acme.mindflicks.platform.content.domain.model.queries;

public record GetAllContentByCreatorIdQuery(String creatorId) {
    public GetAllContentByCreatorIdQuery {
        if (creatorId == null || creatorId.isBlank()) {
            throw new IllegalArgumentException("Creator id must not be null or empty");
        }
    }
}
