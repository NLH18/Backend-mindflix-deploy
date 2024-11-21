package com.acme.mindflicks.platform.forums.domain.model.commands;

public record DeleteForumCommand(Long forumId) {
    public DeleteForumCommand {
        if (forumId == null || forumId <= 0) {
            throw new IllegalArgumentException("forumId cannot be null or less than 1");
        }
    }
}
