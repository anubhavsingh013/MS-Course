package com.in28minutes.rest.webservices.restful_web_services.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
    // timestamp, message,details
    private LocalDateTime timestamp;
    private String message;
    private String detail;
    public ErrorDetails(LocalDateTime timestamp, String message, String detail) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.detail = detail;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetail() {
		return detail;
	}

}
