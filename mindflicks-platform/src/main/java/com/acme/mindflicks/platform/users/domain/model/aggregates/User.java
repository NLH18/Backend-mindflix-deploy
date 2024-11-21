package com.acme.mindflicks.platform.users.domain.model.aggregates;

import com.acme.mindflicks.platform.shared.domain.entities.AuditableAbstractAggregateRoot;
import com.acme.mindflicks.platform.users.domain.model.commands.CreateNewUserCommand;
import com.acme.mindflicks.platform.users.domain.model.valueobjects.EmailAddress;
import com.acme.mindflicks.platform.users.domain.model.valueobjects.Membership;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User  extends AuditableAbstractAggregateRoot<User> {

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Column(nullable = false)
    private String lastName;

    @Getter
    @Column(nullable = false)
    private String userName;

    @Getter
    @Column(nullable = false)
    private String birthDate;

    @Getter
    @Column(nullable = false)
    private String phone;

    @Getter
    @Column(nullable = false)
    @Embedded
    private EmailAddress email;

    @Getter
    @Column(nullable = false)
    private String password;

    @Getter
    private Membership membership;


    protected User(){
        //JPA required
    }

    public User(CreateNewUserCommand command) {
        this.name = command.name();
        this.lastName = command.lastName();
        this.userName = command.userName();
        this.birthDate = command.birthDate();
        this.phone = command.phone();
        this.email = new EmailAddress(command.email());
        this.password = command.password();
        this.membership = command.membership();

    }



}
