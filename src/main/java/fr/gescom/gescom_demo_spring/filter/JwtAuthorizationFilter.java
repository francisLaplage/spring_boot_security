package fr.gescom.gescom_demo_spring.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


public class JwtAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	//1-	On récupère le token	
		String autorizationToken = request.getHeader("Authorization");
	//2- 	On le teste
		if(autorizationToken != null && autorizationToken.startsWith("Bearer ")) {
			try {
				
				String jwt = autorizationToken.substring(7);
				
				//2-1 on vérifie le token avac la signature qui a été utilisée à l'envoie
					Algorithm algo = Algorithm.HMAC256("mySecret1234");
					
					JWTVerifier jwtVerifier = JWT.require(algo).build();
					
					DecodedJWT decodedJWT= jwtVerifier.verify(jwt);
					
					//ici s'il n'y a pas d'exception, on récupère le username et les rôles
					String username = decodedJWT.getSubject();
					String [] roles = decodedJWT.getClaim("roles").asArray(String.class);
					
					Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
					
						for(String r : roles) {
							authorities.add(new SimpleGrantedAuthority(r));
						}				
					//on créer un user de string
					UsernamePasswordAuthenticationToken authenticationToken =
							new UsernamePasswordAuthenticationToken(username, null,authorities );
					//on authentifie l'utilisateur
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					
					// après l'avoir authentifier, on demande à Spring de passer aux filtre suivant
					filterChain.doFilter(request, response);					
			}catch (Exception e) {
				response.setHeader("error-message",e.getMessage() );
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		}else {
			//ici, si l'entête ne contient pas Bearer, alors on passe au filtre suivant
			filterChain.doFilter(request, response);
		}
		
	}

}
