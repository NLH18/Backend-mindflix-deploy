package com.acme.mindflicks.platform.content.application.internal.queryservices;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.content.domain.model.queries.*;
import com.acme.mindflicks.platform.content.domain.services.ContentQueryService;
import com.acme.mindflicks.platform.content.infrastructure.persistence.jpa.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentQueryServiceImpl implements ContentQueryService {
    private final ContentRepository contentRepository;

    public ContentQueryServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @Override
    public List<Content> handle(GetAllContentQuery query) {
        return contentRepository.findAll();
    }

    @Override
    public List<Content> handle(GetAllContentByTitleQuery query) {
        return contentRepository.findAllByTitle(query.title());
    }

    @Override
    public List<Content> handle(GetAllContentByTypeQuery query) {
        return contentRepository.findAllByType(query.type());
    }

    @Override
    public List<Content> handle(GetAllContentByCategoryQuery query) {
        return contentRepository.findAllByCategory(query.category());
    }

    @Override
    public List<Content> handle(GetAllContentByCreatorIdQuery query) {
        return contentRepository.findAllByCreatorId(query.creatorId());
    }

    @Override
    public Optional<Content> handle(GetContentByTitleAndCreatorId query) {
        return contentRepository.findByTitleAndCreatorId(query.title(), query.creatorId());
    }

    @Override
    public Optional<Content> handle(GetContentByIdQuery query) {
        return contentRepository.findById(query.contentId());
    }
}
