package com.acme.mindflicks.platform.events.domain.services;

import com.acme.mindflicks.platform.events.domain.model.aggregates.Event;
import com.acme.mindflicks.platform.events.domain.model.queries.GetAllEventsByTitleQuery;
import com.acme.mindflicks.platform.events.domain.model.queries.GetAllEventsQuery;
import com.acme.mindflicks.platform.events.domain.model.queries.GetEventByIdQuery;
import com.acme.mindflicks.platform.events.domain.model.queries.GetEventByContentIdAndTitleQuery;

import java.util.List;
import java.util.Optional;

/**
 * Service for querying Event entities
 * @summary Service for querying Event entities
 * This interface provides methods for querying Event entities
 */
public interface EventQueryService {

    /**
     * Handle the get all Event entities by content ID
     * @param query the get all Event entities by content ID query
     * @return The list of Event entities if exists
     * @throws IllegalArgumentException if the content ID is null or empty
     * @see GetAllEventsByTitleQuery
     */
    List<Event> handle(GetAllEventsByTitleQuery query);

    Optional<Event> handle(GetEventByIdQuery query);

    /**
     * Handle the get Event entity by content ID and title
     * @param query the get Event entity by content ID and title query
     * @return The Event entity if exists
     * @see GetEventByContentIdAndTitleQuery
     */
    Optional<Event> handle(GetEventByContentIdAndTitleQuery query);

    List<Event> handle(GetAllEventsQuery query);
}
