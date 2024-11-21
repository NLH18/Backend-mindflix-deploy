package com.acme.mindflicks.platform.content.interfaces.rest;

import com.acme.mindflicks.platform.content.domain.model.aggregates.Content;
import com.acme.mindflicks.platform.content.domain.model.queries.*;
import com.acme.mindflicks.platform.content.domain.services.ContentCommandService;
import com.acme.mindflicks.platform.content.domain.services.ContentQueryService;
import com.acme.mindflicks.platform.content.interfaces.rest.resources.ContentResource;
import com.acme.mindflicks.platform.content.interfaces.rest.resources.CreateContentResource;
import com.acme.mindflicks.platform.content.interfaces.rest.transform.ContentResourceFromEntityAssembler;
import com.acme.mindflicks.platform.content.interfaces.rest.transform.CreateContentCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/content", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Content", description = "Operations related to content")
public class ContentController {
    private final ContentQueryService contentQueryService;
    private final ContentCommandService contentCommandService;

    public ContentController(ContentQueryService contentQueryService, ContentCommandService contentCommandService) {
        this.contentQueryService = contentQueryService;
        this.contentCommandService = contentCommandService;
    }

    @Operation(
            summary = "Create content",
            description = "Create content with the provided parameters"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Content created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<ContentResource> createContent(@RequestBody CreateContentResource resource) {
        Optional<Content> content = contentCommandService
                .handle(CreateContentCommandFromResourceAssembler.toCommand(resource));
        return content.map(source -> new ResponseEntity<>(ContentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    public ResponseEntity<List<ContentResource>> getAllContents() {
        var getAllContentsQuery = new GetAllContentQuery();
        var contents = contentQueryService.handle(getAllContentsQuery);
        if (contents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var contentResources = contents.stream()
                .map(ContentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(contentResources);
    }


    @Operation(
            summary = "Get content with param",
            description = "Get content with the provided parameters"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Content"),
            @ApiResponse(responseCode = "404", description = "Bad Request"),
    })
    @GetMapping
    public ResponseEntity<?> getContentWithParameters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String creatorId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String category) {
        if (title == null && creatorId == null && type == null && category == null) {
            return getAllContents();
        }

        var filteredContents = contentQueryService.handle(new GetAllContentQuery()).stream()
                .filter(content -> title == null || content.getTitle().equalsIgnoreCase(title))
                .filter(content -> creatorId == null || content.getCreatorId().equals(creatorId))
                .filter(content -> type == null || content.getType().equalsIgnoreCase(type))
                .filter(content -> category == null || content.getCategory().equalsIgnoreCase(category))
                .toList();

        if (filteredContents.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var contentResources = filteredContents.stream()
                .map(ContentResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(contentResources);
    }

    @GetMapping("/{contentId}")
    @Operation(summary = "Get content by id", description = "Get content by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "content found"),
            @ApiResponse(responseCode = "404", description = "content not found")})
    public ResponseEntity<ContentResource> getContentById(@PathVariable Long contentId) {
        var getContentByIdQuery = new GetContentByIdQuery(contentId);
        var content = contentQueryService.handle(getContentByIdQuery);
        if (content.isEmpty()) return ResponseEntity.notFound().build();
        var contentEntity = content.get();
        var contentResource = ContentResourceFromEntityAssembler.toResourceFromEntity(contentEntity);
        return ResponseEntity.ok(contentResource);
    }

}
