package jp.co.bizrefine.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtAuthProviderUtil {

	public static void verifyRequestToken(final String reqToken, final String secretKey) {
		JWT.require(Algorithm.HMAC256(secretKey)).build().verify(reqToken);			
	}
	
	public static DecodedJWT decodeRequestToken(final String reqToken) {
		return JWT.decode(reqToken);
	}
}