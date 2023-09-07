package fr.gescom.gescom_demo_spring.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.gescom.gescom_demo_spring.dao.GestionUserDao;
import fr.gescom.gescom_demo_spring.entities.AppUser;
import fr.gescom.gescom_demo_spring.filter.JwtAuthenticationFilter;
import fr.gescom.gescom_demo_spring.filter.JwtAuthorizationFilter;
import fr.gescom.gescom_demo_spring.repository.AppRoleRepository;
import fr.gescom.gescom_demo_spring.repository.AppUserRepository;

@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AppUserRepository gestionUserDao;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {				
				AppUser user = gestionUserDao.findUserByUsername(username);			  	  
				  
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				  
				  user.getListRoles().forEach(r->{				  
				  authorities.add(new SimpleGrantedAuthority(r.getRole()));				  
				  });
				 
				  return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities); 		  
			} });
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		  http.cors();
		  http.csrf().disable(); 
		  http.sessionManagement()
		  	  .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.authorizeRequests().antMatchers("/login/**").permitAll();
		//http.formLogin();
		http.authorizeRequests().anyRequest().authenticated();
		
		//Ajout du filtre
		 http.addFilter(new JwtAuthenticationFilter(this.authenticationManagerBean()));
		// http.addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);	 
		 http.addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
