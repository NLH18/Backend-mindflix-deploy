package com.acme.mindflicks.platform.content.application.acl;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.content.domain.model.commands.CreateContentCommand;
import com.acme.mindflicks.platform.content.infrastructure.persistence.jpa.ContentRepository;
import com.acme.mindflicks.platform.content.interfaces.acl.ContentContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ContentContextFacadeImpl implements ContentContextFacade {
        private final ContentRepository contentRepository;

        public ContentContextFacadeImpl(ContentRepository contentRepository) {
            this.contentRepository = contentRepository;
        }

        @Override
        public Long createContent(String creatorId, String type, String title, String description, String category, String objective, String feeling, String image) {
            var createContentCommand = new CreateContentCommand(creatorId, type, title, description, category, objective, feeling, image);
            var content = new Content(createContentCommand);
            content = contentRepository.save(content);
            return content.getId();
        }

        @Override
        public Content getContentById(Long contentId) {
            return contentRepository.findById(contentId)
                    .orElseThrow(() -> new IllegalArgumentException("Content not found"));
        }

}
