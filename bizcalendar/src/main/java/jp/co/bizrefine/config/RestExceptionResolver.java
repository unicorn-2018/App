package jp.co.bizrefine.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RestExceptionResolver {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AbstractErrorWebExceptionHandler.class);
	private static final Logger LOGEXP = Logger.getLogger(Logger.class.getName());

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleException(Exception exception) {
		LOG.error("ApiExceptionHandler", exception);
		Map<String, String> body = Collections.singletonMap("message", "エラーが発生しました。");
		FileHandler fHandler = null;
		try {
			fHandler = new FileHandler("myapp.log", true);
			fHandler.setFormatter(new SimpleFormatter());
			LOGEXP.addHandler(fHandler);
			LOGEXP.log(Level.INFO, exception.getMessage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fHandler != null) {
				fHandler.flush();
				fHandler.close();
			}
		}
		return body;
	}
}
