package jp.co.bizrefine.util;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtAuthProviderUtilTests {
	
	@Test
	public void testVerifyRequestToken() {
		final String testMethodName = "testVerifyRequestToken";

		final String reqToken = "";
		final String secretKey = "";
		
		System.out.println("■ ■ ■ start: " + testMethodName + " ■ ■ ■");
		try {
			JwtAuthProviderUtil.verifyRequestToken(reqToken, secretKey);
		} catch (JWTVerificationException jWTVerificationException) {
			Assert.fail();
		}
		System.out.println("■ ■ ■ end  : " + testMethodName + " ■ ■ ■");
	}

	@Test
	public void testDecodeRequestToken() {
		final String testMethodName = "testDecodeRequestToken";
		
		final String reqToken = "";
		final Long testUserId = 0L;
		final String testUserName = "";
		
		System.out.println("■ ■ ■ start: " + testMethodName + " ■ ■ ■");
		DecodedJWT decodedJWT = null;
		try {
			decodedJWT = JwtAuthProviderUtil.decodeRequestToken(reqToken);
		} catch (JWTDecodeException jWTDecodeException) {
			Assert.fail();
		}
		
		Assert.assertNotNull(decodedJWT);
		Assert.assertEquals(testUserId, decodedJWT.getClaim("user_id").asLong());
		Assert.assertEquals(testUserName, decodedJWT.getClaim("name").toString().replaceAll("\"", ""));
		
		System.out.println("■ ■ ■ end  : " + testMethodName + " ■ ■ ■");
	}
}