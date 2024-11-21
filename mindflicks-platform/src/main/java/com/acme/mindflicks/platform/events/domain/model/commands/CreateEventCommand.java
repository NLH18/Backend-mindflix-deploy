package com.acme.mindflicks.platform.events.domain.model.commands;

/**
 * Command to create an event
 * @param title The title of the event
 * @param contentId the id content of the event
 * @summary Command to create an event
 */
public record CreateEventCommand(String title, String description,
                                 String startDate, String endDate, String contentId) {

    public CreateEventCommand {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or empty");
        if(description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be null or empty");
        if(startDate == null || startDate.isBlank())
            throw new IllegalArgumentException("startDate cannot be null or empty");
        if(endDate == null || endDate.isBlank())
            throw new IllegalArgumentException("endDate cannot be null or empty");
        if (contentId == null || contentId.isBlank())
            throw new IllegalArgumentException("content cannot be null or empty");

    }
}
