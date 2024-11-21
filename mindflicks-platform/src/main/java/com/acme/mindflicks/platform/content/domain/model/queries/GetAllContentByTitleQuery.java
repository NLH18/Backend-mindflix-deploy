package com.acme.mindflicks.platform.content.domain.model.queries;

public record GetAllContentByTitleQuery(String title) {
    public GetAllContentByTitleQuery {
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Title must not be null or empty");
        }
    }
}
