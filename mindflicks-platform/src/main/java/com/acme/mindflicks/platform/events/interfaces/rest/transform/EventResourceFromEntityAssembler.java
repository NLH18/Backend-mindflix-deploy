package com.acme.mindflicks.platform.events.interfaces.rest.transform;

import com.acme.mindflicks.platform.events.domain.model.aggregates.Event;
import com.acme.mindflicks.platform.events.interfaces.rest.resources.EventResource;

public class EventResourceFromEntityAssembler {
    public static EventResource toResourceFromEntity(Event entity){
        return new EventResource(entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getContentId());
    }
}
