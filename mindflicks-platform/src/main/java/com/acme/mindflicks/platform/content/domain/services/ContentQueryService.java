package com.acme.mindflicks.platform.content.domain.services;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.content.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ContentQueryService {

    List<Content> handle(GetAllContentQuery query);

    List<Content> handle(GetAllContentByTitleQuery query);

    List<Content> handle(GetAllContentByTypeQuery query);

    List<Content> handle(GetAllContentByCategoryQuery query);

    List<Content> handle(GetAllContentByCreatorIdQuery query);

    Optional<Content> handle(GetContentByTitleAndCreatorId query);

    Optional<Content> handle(GetContentByIdQuery query);
}
