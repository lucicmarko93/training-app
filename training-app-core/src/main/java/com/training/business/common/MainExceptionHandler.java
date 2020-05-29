package com.training.business.common;

import java.time.LocalDateTime;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.training.business.common.exceptions.ConflictException;
import com.training.business.common.exceptions.ResourceNotFoundException;
import com.training.business.common.exceptions.ServiceIsNotAvailableException;

import lombok.extern.slf4j.Slf4j;

/**
 * Main exception handler for REST api services 
 * @author marko.lucic
 *
 */
@Provider
@Slf4j
public class MainExceptionHandler implements ExceptionMapper<EJBTransactionRolledbackException> {

	@Override
	public Response toResponse(EJBTransactionRolledbackException exception) {
		log.error("{}", exception.getMessage());

		if (isCause(ResourceNotFoundException.class, exception)) {
			return Response.status(Status.NOT_FOUND)
					.entity(ErrorMessage.builder()
							.status(404)
							.reason(Status.NOT_FOUND.getReasonPhrase())
							.message(exception.getMessage())
							.timestamp(LocalDateTime.now())
							.build())
					.build();
		}
		
		if (isCause(ServiceIsNotAvailableException.class, exception)) {
			return Response.status(Status.SERVICE_UNAVAILABLE)
					.entity(ErrorMessage.builder()
							.status(503)
							.reason(Status.SERVICE_UNAVAILABLE.getReasonPhrase())
							.message(exception.getMessage())
							.timestamp(LocalDateTime.now())
							.build())
					.build();
		}
		
		if (isCause(ConflictException.class, exception)) {
			return Response.status(Status.CONFLICT)
					.entity(ErrorMessage.builder()
							.status(409)
							.reason(Status.CONFLICT.getReasonPhrase())
							.message(exception.getMessage())
							.timestamp(LocalDateTime.now())
							.build())
					.build();
		}
		
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(ErrorMessage.builder()
						.status(500)
						.reason(Status.INTERNAL_SERVER_ERROR.getReasonPhrase())
						.message(exception.getMessage())
						.timestamp(LocalDateTime.now())
						.build())
				.build();

	}

	public static boolean isCause(Class<? extends Throwable> expected, Throwable exc) {
		return expected.isInstance(exc) || (exc != null && isCause(expected, exc.getCause()));
	}

}
