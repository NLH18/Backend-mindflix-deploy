package com.acme.mindflicks.platform.content.domain.model.queries;

public record GetAllContentByCategoryQuery(String category) {
    public GetAllContentByCategoryQuery {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category must not be null or empty");
        }
    }
}