package com.acme.mindflicks.platform.forums.domain.services;

import com.acme.mindflicks.platform.forums.domain.model.aggregates.Forum;
import com.acme.mindflicks.platform.forums.domain.model.queries.GetAllForumsQuery;
import com.acme.mindflicks.platform.forums.domain.model.queries.GetForumByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ForumQueryService {
    Optional<Forum> handle(GetForumByIdQuery query);
    List<Forum> handle(GetAllForumsQuery query);
}
