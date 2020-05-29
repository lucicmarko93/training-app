package com.training.business.common;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class ConstraintExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
	
	@Override
	public Response toResponse(ConstraintViolationException exception) {
		log.error("{}", exception.getMessage());
		return Response.status(Status.BAD_REQUEST)
				.entity(ErrorMessage.builder()
						.status(400)
						.reason(Status.BAD_REQUEST.getReasonPhrase())
						.message(exception.getMessage())
						.timestamp(LocalDateTime.now())
						.build())
				.build();
	}

}
