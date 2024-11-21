package com.acme.mindflicks.platform.content.application.internal.commandservices;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.content.domain.model.commands.CreateContentCommand;
import com.acme.mindflicks.platform.content.domain.services.ContentCommandService;
import com.acme.mindflicks.platform.content.infrastructure.persistence.jpa.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentCommandServiceImpl implements ContentCommandService {
    private final ContentRepository contentRepository;

    public ContentCommandServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public Optional<Content> handle(CreateContentCommand command) {
        if (contentRepository.existsByTitleAndCreatorId(command.title(), command.creatorId()))
            throw new IllegalArgumentException("Content with same creator ID already exists for this title");
        var content = new Content(command);
        var createContent = contentRepository.save(content);
        return Optional.of(createContent);
    }
}
