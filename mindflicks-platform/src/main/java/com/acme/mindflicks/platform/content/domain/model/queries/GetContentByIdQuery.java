package com.acme.mindflicks.platform.content.domain.model.queries;

public record GetContentByIdQuery(Long contentId) {
    public GetContentByIdQuery{
        if (contentId == null || contentId <= 0) throw new IllegalArgumentException("Content id is required.");
    }
}
