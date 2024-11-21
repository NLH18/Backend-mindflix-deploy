package com.acme.mindflicks.platform.forums.interfaces.rest.transform;

import com.acme.mindflicks.platform.forums.domain.model.commands.CreateForumCommand;
import com.acme.mindflicks.platform.forums.domain.model.commands.UpdateForumCommand;
import com.acme.mindflicks.platform.forums.interfaces.rest.resources.CreateForumResource;
import com.acme.mindflicks.platform.forums.interfaces.rest.resources.UpdateForumResource;

public class UpdateForumCommandFromResourceAssembler {
    public static UpdateForumCommand toCommandFromResource(Long forumId, UpdateForumResource resource) {
        return new UpdateForumCommand(forumId, resource.title(), resource.comment(), resource.score());
    }
}
