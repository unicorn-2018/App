package jp.co.bizrefine.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.co.bizrefine.domain.model.Code;
import jp.co.bizrefine.domain.model.Group;
import jp.co.bizrefine.domain.model.User;
import jp.co.bizrefine.domain.service.EventService;
import jp.co.bizrefine.domain.service.UserService;

@RestController
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	EventService eventService;

	/**
	 * ログイン画面を展開する
	 *
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView toCalendarTop() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("CalendarTop");
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
	public ModelAndView toCalendarMain(@ModelAttribute User user) throws Exception {
		ModelAndView mv = new ModelAndView();

		// 前処理から受け取ったUserクラスを認証する
		User authedUser = userService.findAuth(user);
		// メンバーの取得
		List<User> members = userService.findMembers();
		List<Group> groups = userService.findGroups();
		// イベント種別（色コード）の取得
		Code code = new Code();
		code.setCodeId("E_001");
		List<Code> eventTypes = eventService.findCodes(code);
		// イベントアイコンの取得
		code = new Code();
		code.setCodeId("E_002");
		List<Code> icons = eventService.findCodes(code);
		// タスク状況の取得
		code = new Code();
		code.setCodeId("T_001");
		List<Code> status = eventService.findCodes(code);
		// タスク種別（色コード）の取得
		code = new Code();
		code.setCodeId("T_002");
		List<Code> taskTypes = eventService.findCodes(code);

		if (authedUser != null) {
			mv.setViewName("CalendarMain");
			mv.addObject("user", authedUser);
			mv.addObject("members", members);
			mv.addObject("groups", groups);
			mv.addObject("eventTypes", eventTypes);
			mv.addObject("icons", icons);
			mv.addObject("status", status);
			mv.addObject("taskTypes", taskTypes);
		} else {
			mv.setViewName("CalendarTop");
			LocalDate date = LocalDate.now();
			String title = "Calendar　" + String.valueOf(date.getYear());
			mv.addObject("title", title);
			mv.addObject("message", "ユーザーID、またはパスワードが間違っています。");
		}

		return mv;
	}
}