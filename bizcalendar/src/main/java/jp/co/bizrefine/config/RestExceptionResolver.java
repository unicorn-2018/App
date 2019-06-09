package jp.co.bizrefine.config;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RestExceptionResolver {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractErrorWebExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleException(Exception exception) {
		LOG.error("ApiExceptionHandler", exception);
		Map<String, String> body = Collections.singletonMap("message", exception.getMessage());
		return body;
	}
}
