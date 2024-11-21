package com.acme.mindflicks.platform.content.domain.services;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.content.domain.model.commands.CreateContentCommand;

import java.util.Optional;

public interface ContentCommandService {
    Optional<Content> handle(CreateContentCommand command);
}
