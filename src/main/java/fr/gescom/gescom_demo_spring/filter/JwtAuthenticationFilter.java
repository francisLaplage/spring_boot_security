package fr.gescom.gescom_demo_spring.filter;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
	//1-	Récupération des params de connexion
	String username = request.getParameter("username");
	String password = request.getParameter("password");				
	//2-	Création d'un user de Spring
	UsernamePasswordAuthenticationToken authenticationToken = 
			new UsernamePasswordAuthenticationToken(username,password);
	
	return this.authenticationManager.authenticate(authenticationToken);
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		//1-	on récupère l'utilisateur authentifié avec getPrincipal() de l'objet authResult
		User user = (User)authResult.getPrincipal();
				
		//2-	Création du token (jwt) avec les données de l'utilisateur
			Algorithm algo = Algorithm.HMAC256("mySecret1234");
				
		String jwtAccessToken = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+20*60*1000))
				.withClaim("roles",
						user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
				.sign(algo);
	//3-	Retourner le token au client soit dans l'entête soit dans le corps de la reponse	
	//response.setHeader("Authorization", jwtAccessToken);	
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), jwtAccessToken);
	}
}
