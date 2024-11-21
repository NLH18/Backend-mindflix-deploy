package com.acme.mindflicks.platform.forums.interfaces.rest.resources;

public record UpdateForumResource(String title, String comment, Integer score) {
    public UpdateForumResource{
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Comment is required");
        }
        if (score == null || score < 1 || score > 5) {
            throw new IllegalArgumentException("score cannot be null and must be between 1 and 5");
        }
    }
}
