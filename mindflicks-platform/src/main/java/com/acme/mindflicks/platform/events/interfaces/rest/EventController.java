package com.acme.mindflicks.platform.events.interfaces.rest;

import com.acme.mindflicks.platform.events.domain.model.aggregates.Event;
import com.acme.mindflicks.platform.events.domain.model.queries.GetAllEventsQuery;
import com.acme.mindflicks.platform.events.domain.model.queries.GetEventByIdQuery;
import com.acme.mindflicks.platform.events.domain.services.EventCommandService;
import com.acme.mindflicks.platform.events.domain.services.EventQueryService;
import com.acme.mindflicks.platform.events.interfaces.rest.resources.CreateEventResource;
import com.acme.mindflicks.platform.events.interfaces.rest.resources.EventResource;
import com.acme.mindflicks.platform.events.interfaces.rest.transform.CreateEventCommandFromResourceAssembler;
import com.acme.mindflicks.platform.events.interfaces.rest.transform.EventResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/events", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Events")
public class EventController {
    private final EventCommandService eventCommandService;
    private final EventQueryService eventQueryService;

    public EventController(EventCommandService eventCommandService, EventQueryService eventQueryService) {
        this.eventCommandService = eventCommandService;
        this.eventQueryService = eventQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<EventResource> createEvent(@RequestBody CreateEventResource resource) {
        var createEventCommand = CreateEventCommandFromResourceAssembler.toCommandFromResource(resource);
        var event = eventCommandService.handle(createEventCommand);
        if(event.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var createdEvent = event.get();
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(createdEvent);
        return new ResponseEntity<>(eventResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events found"),
            @ApiResponse(responseCode = "404", description = "Events not found")
    })
    public ResponseEntity<List<EventResource>> getAllEvents(){
        var events = eventQueryService.handle(new GetAllEventsQuery());
        if(events.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var eventResources = events.stream()
                .map(EventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(eventResources);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get an event by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event found"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<EventResource> getEventById(@PathVariable Long id){
        var event = eventQueryService.handle(new GetEventByIdQuery(id));
        if(event.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var eventEntity = event.get();
        var eventResource = EventResourceFromEntityAssembler.toResourceFromEntity(eventEntity);
        return ResponseEntity.ok(eventResource);
    }
}
