package com.challenge.tenpo.rest.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponseDTO implements Serializable {


	public ErrorResponseDTO(final int statusCode, final String message) {
		this.statusCode = statusCode;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}

	private int statusCode;
	private String message;
	private LocalDateTime timestamp;

}
