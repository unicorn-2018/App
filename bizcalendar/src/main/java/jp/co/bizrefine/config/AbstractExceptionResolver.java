package jp.co.bizrefine.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AbstractExceptionResolver implements HandlerExceptionResolver {

	private static final Logger LOGEXP = Logger.getLogger(Logger.class.getName());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		if (request instanceof HttpServletRequest) {
			HttpSession session = request.getSession();
			if (session.isNew()) {
				mv.addObject("message", "sessiontimeout");
				mv.setViewName("Error");
			}
		}
		if (response instanceof HttpServletResponse) {
			mv.addObject("message", "responseerror");
			mv.setViewName("Error");
		}
		if (ex instanceof NullPointerException) {
			mv.addObject("message", "null");
			mv.setViewName("Error");
		} else {
			mv.addObject("message", ex.getMessage());
			mv.setViewName("Error");
		}

		FileHandler fHandler = null;
		try {
			fHandler = new FileHandler("myapp.log", true);
            fHandler.setFormatter(new SimpleFormatter());
            LOGEXP.addHandler(fHandler);
            LOGEXP.log(Level.INFO, ex.getMessage());
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

		return mv;
	}
}
