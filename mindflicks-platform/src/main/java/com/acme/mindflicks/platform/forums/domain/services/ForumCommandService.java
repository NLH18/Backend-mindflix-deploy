package com.acme.mindflicks.platform.forums.domain.services;

import com.acme.mindflicks.platform.forums.domain.model.aggregates.Forum;
import com.acme.mindflicks.platform.forums.domain.model.commands.CreateForumCommand;
import com.acme.mindflicks.platform.forums.domain.model.commands.DeleteForumCommand;
import com.acme.mindflicks.platform.forums.domain.model.commands.UpdateForumCommand;

import java.util.Optional;

public interface ForumCommandService {
    Long handle(CreateForumCommand command);

   Optional<Forum> handle(UpdateForumCommand command);

   void handle(DeleteForumCommand command);
}
