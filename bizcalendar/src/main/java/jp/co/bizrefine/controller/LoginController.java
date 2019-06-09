package jp.co.bizrefine.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.co.bizrefine.domain.model.User;
import jp.co.bizrefine.domain.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	/**
	 * 初期画面を展開する
	 *
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView toCalendartop() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("Calendartop");
		mv.addObject("user", new User());
		LocalDate date = LocalDate.now();
		String title = "Calendar　" + String.valueOf(date.getYear());
		mv.addObject("title", title);

		return mv;
	}

	/**
	 * 初期画面を展開する
	 *
	 */
	@ResponseBody
	@PostMapping(value = "/login")
	public ModelAndView toCalendarmain(@ModelAttribute User user) throws Exception {
		ModelAndView mv = new ModelAndView();
		//前処理から受け取ったUserクラスを認証する
		User authedUser = userService.auth(user);

		if (authedUser != null) {
			// 認証成功時、遷移先を変更する
			mv.setViewName("Calendarmain");
			// 認証成功時、遷移先へ認証済みUserを渡す
			mv.addObject("user", authedUser);
		} else {
			mv.setViewName("Calendartop");
			LocalDate date = LocalDate.now();
			String title = "Calendar　" + String.valueOf(date.getYear());
			mv.addObject("title", title);
			mv.addObject("message", "ユーザーID、またはパスワードが間違っています。");
		}

		return mv;
	}

}