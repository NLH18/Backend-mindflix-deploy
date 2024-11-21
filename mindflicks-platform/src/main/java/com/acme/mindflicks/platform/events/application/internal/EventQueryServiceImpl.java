package com.acme.mindflicks.platform.events.application.internal;

import com.acme.mindflicks.platform.events.domain.model.aggregates.Event;
import com.acme.mindflicks.platform.events.domain.model.queries.GetAllEventsByTitleQuery;
import com.acme.mindflicks.platform.events.domain.model.queries.GetAllEventsQuery;
import com.acme.mindflicks.platform.events.domain.model.queries.GetEventByIdQuery;
import com.acme.mindflicks.platform.events.domain.model.queries.GetEventByContentIdAndTitleQuery;
import com.acme.mindflicks.platform.events.domain.services.EventQueryService;
import com.acme.mindflicks.platform.events.infrastructure.persistence.jpa.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventQueryServiceImpl implements EventQueryService {

    private final EventRepository eventRepository;

    public EventQueryServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Handle the get all Event entities by content ID
     *
     * @param query the get all Event entities by content ID query
     * @return The list of Event entities if exists
     * @throws IllegalArgumentException if the content ID is null or empty
     * @see GetAllEventsByTitleQuery
     */
    @Override
    public List<Event> handle(GetAllEventsByTitleQuery query) {
        return eventRepository.findAllByTitle(query.title());
    }

    /**
     * Handle the get Event entity by title
     *
     * @param query the get Event entity by title query
     * @return The Event entity if exists
     * @see GetEventByIdQuery
     */
    @Override
    public Optional<Event> handle(GetEventByIdQuery query) {
        return eventRepository.findById(query.id());
    }

    /**
     * Handle the get Event entity by content ID and title
     *
     * @param query the get Event entity by content ID and title query
     * @return The Event entity if exists
     * @see GetEventByContentIdAndTitleQuery
     */
    @Override
    public Optional<Event> handle(GetEventByContentIdAndTitleQuery query) {
        return eventRepository.findByTitleAndContentId(query.contentId(), query.title());
    }

    @Override
    public List<Event> handle(GetAllEventsQuery query) {
        return eventRepository.findAll();
    }
}
