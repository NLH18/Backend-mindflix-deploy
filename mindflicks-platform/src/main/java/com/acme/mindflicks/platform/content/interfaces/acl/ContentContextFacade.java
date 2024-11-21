package com.acme.mindflicks.platform.content.interfaces.acl;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;

public interface ContentContextFacade {
    Long createContent(String creatorId, String type, String title, String description, String category, String objective, String feeling, String image);
    Content getContentById(Long contentId);
}
