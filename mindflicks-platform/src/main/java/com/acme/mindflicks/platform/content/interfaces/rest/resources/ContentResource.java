package com.acme.mindflicks.platform.content.interfaces.rest.resources;

public record ContentResource(Long id, String creatorId, String type, String title, String description,String category,String objective,String feeling, String image) {
}
