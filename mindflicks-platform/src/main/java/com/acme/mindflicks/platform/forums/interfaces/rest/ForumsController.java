package com.acme.mindflicks.platform.forums.interfaces.rest;

import com.acme.mindflicks.platform.forums.application.internal.outboundservices.acl.ExternalContentService;
import com.acme.mindflicks.platform.forums.application.internal.outboundservices.acl.ExternalUserService;
import com.acme.mindflicks.platform.forums.domain.model.commands.DeleteForumCommand;
import com.acme.mindflicks.platform.forums.domain.model.queries.GetAllForumsQuery;
import com.acme.mindflicks.platform.forums.domain.model.queries.GetForumByIdQuery;
import com.acme.mindflicks.platform.forums.domain.services.ForumCommandService;
import com.acme.mindflicks.platform.forums.domain.services.ForumQueryService;
import com.acme.mindflicks.platform.forums.interfaces.rest.resources.CreateForumResource;
import com.acme.mindflicks.platform.forums.interfaces.rest.resources.ForumResource;
import com.acme.mindflicks.platform.forums.interfaces.rest.resources.UpdateForumResource;
import com.acme.mindflicks.platform.forums.interfaces.rest.transform.CreateForumCommandFromResourceAssembler;
import com.acme.mindflicks.platform.forums.interfaces.rest.transform.ForumResourceFromEntityAssembler;
import com.acme.mindflicks.platform.forums.interfaces.rest.transform.UpdateForumCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/forums", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Forums", description = "Available Forum Endpoints")
public class ForumsController {
    private final ForumCommandService forumCommandService;
    private final ForumQueryService forumQueryService;
    private final ExternalContentService externalContentService;
    private final ExternalUserService externalUserService;

    public ForumsController(ForumCommandService forumCommandService, ForumQueryService forumQueryService, ExternalContentService externalContentService, ExternalUserService externalUserService) {
        this.forumCommandService = forumCommandService;
        this.forumQueryService = forumQueryService;
        this.externalContentService = externalContentService;
        this.externalUserService = externalUserService;
    }

    @PostMapping
    @Operation(summary = "Create a new forum", description = "Create a new forum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "forum created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "forum not found")})
    public ResponseEntity<ForumResource> createForum(@RequestBody CreateForumResource resource) {
        var content = externalContentService.fetchContentById(resource.contentId());
        var user = externalUserService.fetchUserById(resource.userId());
        if (content.isEmpty() || user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var createForumCommand = CreateForumCommandFromResourceAssembler.toCommandFromResource(resource);
        var forumId = forumCommandService.handle(createForumCommand);
        if (forumId == null || forumId == 0L) return ResponseEntity.badRequest().build();
        var getForumByIdQuery = new GetForumByIdQuery(forumId);
        var forum = forumQueryService.handle(getForumByIdQuery);
        if (forum.isEmpty()) return ResponseEntity.notFound().build();
        var forumEntity = forum.get();
        var forumResource = ForumResourceFromEntityAssembler.toResourceFromEntity(forumEntity);
        return new ResponseEntity<>(forumResource, HttpStatus.CREATED);
    }

    @GetMapping("/{forumId}")
    @Operation(summary = "Get forum by id", description = "Get Forum by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Forum found"),
            @ApiResponse(responseCode = "404", description = "Forum not found")})
    public ResponseEntity<ForumResource> getForumById(@PathVariable Long forumId) {
        var getForumByIdQuery = new GetForumByIdQuery(forumId);
        var forum = forumQueryService.handle(getForumByIdQuery);
        if (forum.isEmpty()) return ResponseEntity.notFound().build();
        var forumEntity = forum.get();
        var forumResource = ForumResourceFromEntityAssembler.toResourceFromEntity(forumEntity);
        return ResponseEntity.ok(forumResource);
    }

    @GetMapping
    @Operation(summary = "Get all Forums", description = "Get all Forums")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Forums found"),
            @ApiResponse(responseCode = "404", description = "Forums not found")})
    public ResponseEntity<List<ForumResource>> getAllForums() {
        var forums = forumQueryService.handle(new GetAllForumsQuery());
        if (forums.isEmpty()) return ResponseEntity.notFound().build();
        var forumResources = forums.stream()
                .map(ForumResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(forumResources);
    }

    @PutMapping("/{forumId}")
    @Operation(summary = "Update Forum", description = "Update Forum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Forum updated"),
            @ApiResponse(responseCode = "404", description = "Forum not found")})
    public ResponseEntity<ForumResource> updateForum(@PathVariable Long forumId, @RequestBody UpdateForumResource resource) {
        var updateForumCommand = UpdateForumCommandFromResourceAssembler.toCommandFromResource(forumId, resource);
        var updatedForum = forumCommandService.handle(updateForumCommand);
        if (updatedForum.isEmpty()) return ResponseEntity.notFound().build();
        var updatedForumEntity = updatedForum.get();
        var updatedForumResource = ForumResourceFromEntityAssembler.toResourceFromEntity(updatedForumEntity);
        return ResponseEntity.ok(updatedForumResource);
    }

    @DeleteMapping("/{forumId}")
    @Operation(summary = "Delete Forum", description = "Delete Forum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Forum deleted"),
            @ApiResponse(responseCode = "404", description = "Forum not found")})
    public ResponseEntity<?> deleteForum(@PathVariable Long forumId) {
        var deleteForumCommand = new DeleteForumCommand(forumId);
        forumCommandService.handle(deleteForumCommand);
        return ResponseEntity.ok("Forum with given id successfully deleted");
    }
}
