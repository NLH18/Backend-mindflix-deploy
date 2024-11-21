package com.acme.mindflicks.platform.forums.domain.model.aggregates;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.forums.domain.model.commands.CreateForumCommand;
import com.acme.mindflicks.platform.shared.domain.entities.AuditableAbstractAggregateRoot;
import com.acme.mindflicks.platform.users.domain.model.aggregates.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Forum extends AuditableAbstractAggregateRoot<Forum> {
    @Getter
    @ManyToOne
    @JoinColumn(name = "content_id")
    private Content content;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private Integer score;

    public Forum() {
    }

    public Forum(Content content, User userId, String title, String comment, Integer score) {
        this.content = content;
        this.userId = userId;
        this.title = title;
        this.comment = comment;
        this.score = score;
    }

    public Forum(CreateForumCommand command, Content content, User user){
        this.content = content;
        this.userId = user;
        this.title = command.title();
        this.comment = command.comment();
        this.score = command.score();
    }

    public Forum updateInformation (String title,String comment, Integer score) {
        this.title = title;
        this.comment = comment;
        this.score = score;
        return this;
    }
}
