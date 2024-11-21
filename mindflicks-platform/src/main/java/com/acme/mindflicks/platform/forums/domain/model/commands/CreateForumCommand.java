package com.acme.mindflicks.platform.forums.domain.model.commands;

public record CreateForumCommand(Long contentId, Long userId, String title, String comment, Integer score) {
    public CreateForumCommand{
        if (contentId == null || contentId <= 0) {
            throw new IllegalArgumentException("contentId cannot be null or less than 1");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("userId cannot be null or less than 1");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or blank");
        }
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("comment cannot be null or blank");
        }
        if (score == null || score < 1 || score > 5) {
            throw new IllegalArgumentException("score cannot be null and must be between 1 and 5");
        }
    }
}
