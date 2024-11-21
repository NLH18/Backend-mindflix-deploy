package com.acme.mindflicks.platform.content.domain.model.aggregates;

import com.acme.mindflicks.platform.content.domain.model.commands.CreateContentCommand;
import com.acme.mindflicks.platform.shared.domain.entities.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Content extends AuditableAbstractAggregateRoot<Content> {

    @Column(nullable = false)
    private String creatorId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String objective;

    @Column(nullable = false)
    private String feeling;

    @Column(nullable = false)
    private String image;

    protected  Content() {}

    public Content(CreateContentCommand command) {
        this.type = command.type();
        this.creatorId = command.creatorId();
        this.title = command.title();
        this.description = command.description();
        this.category = command.category();
        this.objective = command.objective();
        this.feeling = command.feeling();
        this.image = command.image();
    }
}
