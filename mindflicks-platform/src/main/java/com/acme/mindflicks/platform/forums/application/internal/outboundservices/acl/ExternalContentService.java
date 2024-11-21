package com.acme.mindflicks.platform.forums.application.internal.outboundservices.acl;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.content.interfaces.acl.ContentContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalContentService {
        private final ContentContextFacade contentContextFacade;

        public ExternalContentService(ContentContextFacade contentContextFacade) {
            this.contentContextFacade = contentContextFacade;
        }

        public Optional<Content> fetchContentById(Long contentId) {
            try {
                var content = contentContextFacade.getContentById(contentId);
                return Optional.of(content);
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }

        public Long createContent(String creatorId, String type, String title, String description, String category, String objective, String feeling, String image) {
            return contentContextFacade.createContent(creatorId, type, title, description, category, objective, feeling, image);
        }

}
