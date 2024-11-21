package com.acme.mindflicks.platform.forums.interfaces.rest.transform;

import com.acme.mindflicks.platform.forums.domain.model.aggregates.Forum;
import com.acme.mindflicks.platform.forums.interfaces.rest.resources.ForumResource;

public class ForumResourceFromEntityAssembler {
    public static ForumResource toResourceFromEntity(Forum entity) {
        return new ForumResource(entity.getId(), entity.getContent(),entity.getUserId(), entity.getTitle(), entity.getComment(), entity.getScore());
    }
}
