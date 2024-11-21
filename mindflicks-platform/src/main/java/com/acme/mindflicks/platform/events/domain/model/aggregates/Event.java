package com.acme.mindflicks.platform.events.domain.model.aggregates;

import com.acme.mindflicks.platform.events.domain.model.commands.CreateEventCommand;
import com.acme.mindflicks.platform.shared.domain.entities.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Event extends AuditableAbstractAggregateRoot<Event> {

    @Getter
    @Column(nullable = false)
    private String title;

    @Getter
    @Column(nullable = false)
    private String description;

    @Getter
    @Column(nullable = false)
    private String startDate;

    @Getter
    @Column(nullable = false)
    private String endDate;

    @Getter
    @Column(nullable = false)
    private String contentId;

    protected Event() {}

    /**
     * @summary Constructor for Event
     * It creates a new Event instance based on the CreateEventCommand command.
     * @param command - The CreateEventCommand command
     */
    public Event(CreateEventCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.startDate = command.startDate();
        this.endDate = command.endDate();
        this.contentId = command.contentId();
    }
}
