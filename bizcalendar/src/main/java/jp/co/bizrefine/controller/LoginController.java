package jp.co.bizrefine.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jp.co.bizrefine.domain.model.Code;
import jp.co.bizrefine.domain.model.Group;
import jp.co.bizrefine.domain.model.User;
import jp.co.bizrefine.domain.service.EventService;
import jp.co.bizrefine.domain.service.UserService;
import jp.co.bizrefine.util.JwtAuthProviderUtil;

@RestController
public class LoginController {

	@Autowired
	UserService userService;
	@Autowired
	EventService eventService;

	/*
	 * code_tbからnaiyoを取得するためのキー TODO 定数クラスに追加する(定数クラスではprivate削除)
	 */
	private interface CODE_KEY {
		// コード種別 URI
		String URI = "uri";
		// 接続先 AXIS
		String AXIS = "axis";
	}

	/*
	 * トークンのデコードに使用するclaim
	 */
	private interface CLAIM {
		// ユーザID
		String USER_ID = "user_id";
		// 名前(フルネーム)
		String NAME = "name";
	}

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
	public ModelAndView toCalendarMain(@CookieValue("auth") String reqToken) throws Exception {

		// シークレットキー取得
		List<Code> listSecretKey = this.findCode(CODE_KEY.URI, CODE_KEY.AXIS);
		if (null == listSecretKey || null == listSecretKey.get(0)) return null;

		// トークンからユーザ情報取得
		User verifiedUser = this.getUserFromToken(reqToken, listSecretKey.get(0).getNaiyo1());

		// ユーザを認証する
		User authedUser = userService.findAuth(verifiedUser);

		// リクエストのトークンが不正の場合、以降の処理には進めない
		// そのため、以降では暫定的にトークンの検証を行わない
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

		ModelAndView mv = new ModelAndView();
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

	private User getUserFromToken(String token, String secretKey) {
		// トークンの検証
		JwtAuthProviderUtil.verifyRequestToken(token, secretKey);

		// トークンのデコード
		DecodedJWT decodedJWT = JwtAuthProviderUtil.decodeRequestToken(token);

		// ユーザ情報を取得
		User verifiedUser = new User();
		verifiedUser.setUserId(decodedJWT.getClaim(CLAIM.USER_ID).asInt());
		// トークンから取得した名前の前後に " が付いているため取り除く
		verifiedUser.setName(decodedJWT.getClaim(CLAIM.NAME).toString().replaceAll("\"", ""));
		return verifiedUser;
	}

	/*
	 * code_tbから情報取得 TODO コンポーネントクラスを作成して移行する
	 */
	private List<Code> findCode(String codeId, String codeKey) throws JsonParseException, JsonMappingException {

		Code inCode = new Code();
		inCode.setCodeId(codeId);
		inCode.setCode(codeKey);
		List<Code> outListCode = eventService.findCodes(inCode);

		// code_tbからの取得結果が無い場合はnull返却
		if (null == outListCode) return null;
		return outListCode;
	}
}