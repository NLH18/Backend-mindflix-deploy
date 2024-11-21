package com.acme.mindflicks.platform.events.interfaces.rest.resources;

public record CreateEventResource(String title, String description,
                                  String startDate, String endDate,
                                  String contentId) {

}
