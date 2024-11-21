package com.acme.mindflicks.platform.content.domain.model.queries;

public record GetContentByTitleAndCreatorId(String title, String creatorId) {
    public GetContentByTitleAndCreatorId {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or empty");
        }
        if (creatorId == null || creatorId.isBlank()) {
            throw new IllegalArgumentException("Creator ID must not be null or empty");
        }
    }
}