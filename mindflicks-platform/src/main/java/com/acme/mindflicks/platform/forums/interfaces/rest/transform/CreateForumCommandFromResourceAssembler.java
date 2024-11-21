package com.acme.mindflicks.platform.forums.interfaces.rest.transform;

import com.acme.mindflicks.platform.forums.domain.model.commands.CreateForumCommand;
import com.acme.mindflicks.platform.forums.interfaces.rest.resources.CreateForumResource;

public class CreateForumCommandFromResourceAssembler {
    public static CreateForumCommand toCommandFromResource(CreateForumResource resource) {
        return new CreateForumCommand(resource.contentId(), resource.userId(), resource.title(), resource.comment(), resource.score());
    }
}
