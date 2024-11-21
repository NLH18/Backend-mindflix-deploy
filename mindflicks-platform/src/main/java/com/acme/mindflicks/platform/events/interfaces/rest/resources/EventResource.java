package com.acme.mindflicks.platform.events.interfaces.rest.resources;

public record EventResource(Long id,
                            String title,
                            String description,
                            String startDate,
                            String endDate,
                            String contentId) {
}
