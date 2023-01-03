package com.sistema.kingshoes.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;	
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;


@Configuration
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:8080/login", allowCredentials="*")
public class WebSecurityConfig {
	
	
	private final UserDetailsService userDetailsService;
	private final JWTAuthorizationFilter jwtAuthorizationFiltrer;
	
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http,AuthenticationManager auth) throws Exception {
		JWTAuthenticationFilter jwtAuthenticationFilter=new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager(auth);
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		return http
				.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST,"/login/**","/tiendas/save","/marca/save","/direccion/save","/pedidos/save").permitAll()
				.antMatchers(HttpMethod.GET,"/categoria/**","/almacen/**","/tiendas/**","/tallas/**","/marca/**").permitAll()
				.antMatchers(HttpMethod.GET,"/files/view/**","/files/view/archivos/**","/municipio/**","/pedidos/**").permitAll()
				.antMatchers(HttpMethod.GET,"/carrito/**","/departamentos/**","/direccion/**","/pedidos/save","/pedido/almacen/**").permitAll()
				.antMatchers(HttpMethod.POST,"/usuarios/save","/api/files","/productos/save","/almacen/save","/pedido/almacen/save").permitAll()
				
				.antMatchers(HttpMethod.PUT,"/usuarios/update/**","/direccion/**","/pedidos/**").permitAll()
				
				.antMatchers(HttpMethod.GET,"/productos/**","/usuarios/correo/**").permitAll()
				
				.anyRequest()
				.authenticated()
				
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				
				
				.and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFiltrer,UsernamePasswordAuthenticationFilter.class)
				
				.build();
		
	}
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder())
				.and()
				.build();
				
		
	}

	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	

}
