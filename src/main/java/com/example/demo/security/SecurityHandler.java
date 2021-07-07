package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityHandler {

	public final String apikey = "skddu33dfssd332100ccc2rtx";

	private void checkJWT(String bearer, String tipo) throws Exception {
		if (bearer == null || !bearer.substring(0, 6).equals("Bearer"))
			throw new Exception("Token não presente ou inválido");

		String token = bearer.substring(7);
		Claims data = parseJWT(token);
		Date instant = new Date(System.currentTimeMillis());

		if (data.getExpiration().before(instant))
			throw new Exception("Token expirado");

		Object role = data.get("role");

		if(tipo.equals("MEDICO") && role.equals("ENFERMEIRO")) {
			throw new Exception("Usuário não permitido");
		}
		
		if (role == null)
			throw new Exception("Usuário não permitido");
	}

	public Boolean authorizeAll(String bearer) {
		try {
			checkJWT(bearer, "TODOS");
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public Boolean authorizeDoctor(String bearer) {
		try {
			checkJWT(bearer, "MEDICO");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String createJWT(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRole());
		String token = Jwts.builder()
				.setSubject("clinica")
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, apikey)
				.setExpiration(new Date(System.currentTimeMillis() + (60000 * 360))).compact();

		return token;
	}

	public Claims parseJWT(String jwt) {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(apikey)).parseClaimsJws(jwt)
				.getBody();
		return claims;
	}
}