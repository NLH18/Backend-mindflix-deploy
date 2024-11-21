package com.acme.mindflicks.platform.forums.application.internal.commandservices;

import com.acme.mindflicks.platform.content.infrastructure.persistence.jpa.ContentRepository;
import com.acme.mindflicks.platform.forums.domain.model.aggregates.Forum;
import com.acme.mindflicks.platform.forums.domain.model.commands.CreateForumCommand;
import com.acme.mindflicks.platform.forums.domain.model.commands.DeleteForumCommand;
import com.acme.mindflicks.platform.forums.domain.model.commands.UpdateForumCommand;
import com.acme.mindflicks.platform.forums.domain.services.ForumCommandService;
import com.acme.mindflicks.platform.forums.infrastructure.persistence.jpa.ForumRepository;
import com.acme.mindflicks.platform.users.infrastucture.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForumCommandServiceImpl implements ForumCommandService {
    private final ForumRepository forumRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    public ForumCommandServiceImpl(ForumRepository forumRepository, ContentRepository contentRepository, UserRepository userRepository) {
        this.forumRepository = forumRepository;
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Long handle(CreateForumCommand command) {
        if (forumRepository.existsByTitle(command.title()))
            throw new IllegalArgumentException("Forum with title %s already exists".formatted(command.title()));
        var content = contentRepository.findById(command.contentId()) .orElseThrow(() -> new IllegalArgumentException("Content with ID %s not found".formatted(command.contentId())));
        var user = userRepository.findById(command.userId()) .orElseThrow(() -> new IllegalArgumentException("User with ID %s not found".formatted(command.userId())));
        var forum = new Forum(command, content, user);
        try {
            forumRepository.save(forum);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving forum: %s".formatted(e.getMessage()));
        }
        return forum.getId();
    }

    @Override
    public Optional<Forum> handle(UpdateForumCommand command) {
        if (forumRepository.existsByTitleAndIdIsNot(command.title(), command.forumId()))
            throw new IllegalArgumentException("Forum with title %s already exists".formatted(command.title()));
        var result = forumRepository.findById(command.forumId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Forum with id %s not found".formatted(command.forumId()));
        var forumToUpdate = result.get();
        try {
            var updatedForum = forumRepository.save(forumToUpdate.updateInformation(command.title(), command.comment(), command.score()));
            return Optional.of(updatedForum);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating forum: %s".formatted(e.getMessage()));
        }
    }

    @Override
    public void handle(DeleteForumCommand command) {
        if (!forumRepository.existsById(command.forumId())) {
            throw new IllegalArgumentException("Forum with id %s not found".formatted(command.forumId()));
        }
        try {
            forumRepository.deleteById(command.forumId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting forum: %s".formatted(e.getMessage()));
        }
    }


}
