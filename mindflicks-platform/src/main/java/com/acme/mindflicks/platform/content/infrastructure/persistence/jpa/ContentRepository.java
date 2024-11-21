package com.acme.mindflicks.platform.content.infrastructure.persistence.jpa;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findAll();

    List<Content> findAllByTitle(String title);

    List<Content> findAllByType(String type);

    List<Content> findAllByCategory(String category);

    List<Content> findAllByCreatorId(String creatorId);

    boolean existsByTitleAndCreatorId(String title, String creatorId);

    Optional<Content> findByTitleAndCreatorId(String title, String creatorId);

}
