package jp.co.bizrefine.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AbstractExceptionResolver implements HandlerExceptionResolver {

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
			String message = ex.getClass() + " : " + ex.getMessage();
			mv.addObject("message", message);
			mv.setViewName("Error");
		}
		return mv;
	}
}
