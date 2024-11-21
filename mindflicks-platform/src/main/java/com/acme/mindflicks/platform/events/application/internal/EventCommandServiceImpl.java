package com.acme.mindflicks.platform.events.application.internal;

import com.acme.mindflicks.platform.events.domain.model.commands.CreateEventCommand;
import com.acme.mindflicks.platform.events.domain.model.aggregates.Event;
import com.acme.mindflicks.platform.events.domain.services.EventCommandService;
import com.acme.mindflicks.platform.events.infrastructure.persistence.jpa.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventCommandServiceImpl implements EventCommandService {

    private final EventRepository eventRepository;

    public EventCommandServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * Handles the create Event command
     *
     * @param command The create Event command
     * @throws IllegalArgumentException if the title or start date is null or empty
     * @see CreateEventCommand
     */
    @Override
    public Optional<Event> handle(CreateEventCommand command) {
        if (eventRepository.existsByTitleAndContentId(command.title(), command.contentId())) {
            throw new IllegalArgumentException("Event with this title and start date already exists");
        }
        var event = new Event(command);
        var createdEvent = eventRepository.save(event);
        return Optional.of(createdEvent);
    }
}
