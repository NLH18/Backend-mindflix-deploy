package com.acme.mindflicks.platform.forums.application.internal.queryservices;

import com.acme.mindflicks.platform.forums.domain.model.aggregates.Forum;
import com.acme.mindflicks.platform.forums.domain.model.queries.GetAllForumsQuery;
import com.acme.mindflicks.platform.forums.domain.model.queries.GetForumByIdQuery;
import com.acme.mindflicks.platform.forums.domain.services.ForumQueryService;
import com.acme.mindflicks.platform.forums.infrastructure.persistence.jpa.ForumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumQueryServiceImpl implements ForumQueryService {
    private final ForumRepository forumRepository;

    public ForumQueryServiceImpl(ForumRepository forumRepository) { this.forumRepository = forumRepository; }

    @Override
    public Optional<Forum> handle(GetForumByIdQuery query) {
        return forumRepository.findById(query.forumId());
    }

    @Override
    public List<Forum> handle(GetAllForumsQuery query) {
        return forumRepository.findAll();
    }
}
