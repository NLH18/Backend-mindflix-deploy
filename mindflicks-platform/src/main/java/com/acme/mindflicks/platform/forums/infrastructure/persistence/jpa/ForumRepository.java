package com.acme.mindflicks.platform.forums.infrastructure.persistence.jpa;

import com.acme.mindflicks.platform.forums.domain.model.aggregates.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {
    boolean existsByTitleAndId(String title, Long forumId);
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdIsNot(String title, Long id);
}
