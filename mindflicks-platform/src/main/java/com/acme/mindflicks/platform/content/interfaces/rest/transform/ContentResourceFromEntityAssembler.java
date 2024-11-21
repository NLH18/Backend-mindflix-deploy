package com.acme.mindflicks.platform.content.interfaces.rest.transform;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.content.interfaces.rest.resources.ContentResource;

public class ContentResourceFromEntityAssembler {

    public static ContentResource toResourceFromEntity(Content entity) {
        return new ContentResource(entity.getId(), entity.getCreatorId(), entity.getType(), entity.getTitle(), entity.getDescription(), entity.getCategory(), entity.getObjective(), entity.getFeeling(), entity.getImage());
    }
}
