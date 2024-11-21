package com.acme.mindflicks.platform.forums.interfaces.rest.resources;


public record CreateForumResource(Long contentId, Long userId, String title, String comment, Integer score) {
    public  CreateForumResource{
        if (contentId == null || contentId <= 0) {
            throw new IllegalArgumentException("contentId is required");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId is required");
        }
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
