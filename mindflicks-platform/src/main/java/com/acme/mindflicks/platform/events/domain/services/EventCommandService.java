package com.acme.mindflicks.platform.events.domain.services;

import com.acme.mindflicks.platform.events.domain.model.commands.CreateEventCommand;
import com.acme.mindflicks.platform.events.domain.model.aggregates.Event;

import java.util.Optional;

/**
 * Service for handling Event commands.
 * @summary Service for handling Event commands
 * This interface provides methods for handling Event-related commands.
 */
public interface EventCommandService {

    /**
     * Handles the create Event command
     * @param command The create Event command
     * @throws IllegalArgumentException if any required fields in the command are invalid
     * @see CreateEventCommand
     */
    Optional<Event> handle(CreateEventCommand command);
}
