package br.fundatec;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenParser {
	
	private static final String SECRET = "secret";
	
//	private static final long TTL = -1;//1000 * 60 * 60 * 24; // 24 hours
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public static String parse(String token, String key) {
		Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

		Object value = claims.get(key);
		if (value != null) {
			return value.toString();
		}
		return null;
	}
	
	public static String createToken(String user) {

	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	 
	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    //Let's set the JWT Claims
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("usuario", user);
	    claims.put("created", DATE_FORMAT.format(new Date()));
	    JwtBuilder builder = Jwts.builder().setClaims(claims)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    //if it has been specified, let's add the expiration
//	    if (TTL >= 0) {
//		    long nowMillis = System.currentTimeMillis();
//	    	long expMillis = nowMillis + TTL;
//	        Date exp = new Date(expMillis);
//	        builder.setExpiration(exp);
//	    }
	 
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public static void main(String[] args) {
		System.out.println(createToken("adm"));
	}
}
