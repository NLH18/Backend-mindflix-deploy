package com.acme.mindflicks.platform.events.infrastructure.persistence.jpa;

import com.acme.mindflicks.platform.events.domain.model.aggregates.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for Event entity
 * @summary JPA repository for Event entity
 * This interface extends JpaRepository, which provides all the CRUD operations for the Event entity.
 * It extends JpaRepository with Event as the entity and Long as the type of the primary key.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Find all events by title
     * @param title The title of the event
     * @return List of events with the specified title
     */
    List<Event> findAllByTitle(String title);

    /**
     * Check if an Event exists by title and startDate
     * @param title The title of the event
     * @param contentId The start date of the event
     * @return True if an Event exists with the specified title and start date, false otherwise
     */
    boolean existsByTitleAndContentId(String title, String contentId);

    Optional<Event> findByTitleAndContentId(String title, String contentId);
}
