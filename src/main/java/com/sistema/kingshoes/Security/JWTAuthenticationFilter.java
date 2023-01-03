package com.sistema.kingshoes.Security;

import java.util.Collections;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;



public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		AuthCredentials authCredentials = new AuthCredentials();
		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		} catch (IOException e) {

		}
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getEmail(),
				authCredentials.getPassword(),
				Collections.emptyList());

		return getAuthenticationManager().authenticate(usernamePAT);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		UserDetailsImpl userDatils = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDatils.getNombre(), userDatils.getUsername());
		//response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("Access-Control-Expose-Headers", "Authorization, Bearer ");
	    response.addHeader("Bearer", "Bearer "+token);
	    response.addHeader("Authorization", "Bearer " + token); 
		response.getWriter().append(token).flush();
		System.out.println("Datos " + token);
		super.successfulAuthentication(request, response, chain, authResult);
	}

}
