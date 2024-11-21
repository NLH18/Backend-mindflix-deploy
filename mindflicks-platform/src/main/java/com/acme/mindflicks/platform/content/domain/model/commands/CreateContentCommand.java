package com.acme.mindflicks.platform.content.domain.model.commands;

public record CreateContentCommand(String creatorId, String type, String title, String description,String category,String objective,String feeling, String image) {
    public CreateContentCommand{
        if (creatorId == null || creatorId.isBlank()) {
            throw new IllegalArgumentException("Creator Id must not be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Type must not be null or empty");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or empty");
        }

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description must not be null or empty");
        }

        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Category must not be null or empty");
        }

        if (objective == null || objective.isBlank()) {
            throw new IllegalArgumentException("Objective must not be null or empty");
        }

        if (feeling == null || feeling.isBlank()) {
            throw new IllegalArgumentException("Feeling must not be null or empty");
        }

        if (image == null || image.isBlank()) {
            throw new IllegalArgumentException("Image must not be null or empty");
        }
    }
}
