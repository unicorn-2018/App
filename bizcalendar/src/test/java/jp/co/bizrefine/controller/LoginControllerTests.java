package jp.co.bizrefine.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import jp.co.bizrefine.domain.model.Code;
import jp.co.bizrefine.domain.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTests {

	@Autowired
	LoginController loginController;

	MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	public void testToCalendarMain() {
		final String testMethodName = "testToCalendarMain";

		// 期待値
		final int expectedId = 0;
		final String expectedName = "";

		// パラメータ設定
		final String testToken = "";

		System.out.println("■ ■ ■ start:" + testMethodName + " ■ ■ ■");
		try {
			MvcResult results = mockMvc.perform(MockMvcRequestBuilders
					.post("/login")
					.cookie(new Cookie[] { new Cookie("auth", testToken) })
					).andReturn();

			Map<String, Object> response = results.getModelAndView().getModel();
			User actualUser = (User) response.get("user");

			// 結果の検証
			Assert.assertEquals(expectedId, actualUser.getUserId());
			Assert.assertEquals(expectedName, actualUser.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("■ ■ ■ end:" + testMethodName + " ■ ■ ■");
	}

	@Test
	public void testGetUserFromToken() {
		final String testMethodName = "testGetUserFromToken";

		// テスト用パラメータ設定
		final String testToken = "";
		final String testSecretKey = "";


		System.out.println("■ ■ ■ start:" + testMethodName + " ■ ■ ■");

		User actualUser = null;
		try {
			actualUser = loginController.getUserFromToken(testToken, testSecretKey);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}

		// 結果の検証
		Assert.assertNotNull(actualUser);
		Assert.assertNotNull(actualUser.getUserId());
		Assert.assertNotNull(actualUser.getName());
		// 結果を出力
		System.out.println("ユーザID : " + actualUser.getUserId());
		System.out.println("名前 : " + actualUser.getName());

		System.out.println("■ ■ ■ end:" + testMethodName + " ■ ■ ■");
	}

	// TODO コンポーネントクラスを作成した時に一緒に移行する
	@Test
	public void testFindCode() {
		final String testMethodName = "testFindCode";

		// テスト用パラメータ設定
		final String testCodeId = "";
		final String testCodeKey = "";

		// 期待値
		final String expectedNaiyo1 = "";


		System.out.println("■ ■ ■ start:" + testMethodName + " ■ ■ ■");

		List<Code> actualListCode = null;
		try {
			actualListCode = loginController.findCode(testCodeId, testCodeKey);
		} catch (Exception e) {
			Assert.fail();
		}

		// 結果の検証
		Assert.assertNotNull(actualListCode);
		Assert.assertNotNull(actualListCode.get(0));
		Assert.assertEquals(expectedNaiyo1, actualListCode.get(0).getNaiyo1());

		System.out.println("■ ■ ■ end:" + testMethodName + " ■ ■ ■");
	}
}