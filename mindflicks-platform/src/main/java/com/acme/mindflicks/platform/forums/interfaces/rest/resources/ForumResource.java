package com.acme.mindflicks.platform.forums.interfaces.rest.resources;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.users.domain.model.aggregates.User;

public record ForumResource(Long forumId, Content contentId, User userId, String title, String comment, Integer score) {
}
