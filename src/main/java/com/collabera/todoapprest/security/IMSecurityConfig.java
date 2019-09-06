package com.collabera.todoapprest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
public class IMSecurityConfig extends WebSecurityConfigurerAdapter
{
//	@Autowired
//	UserService userService;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder builder) throws Exception
	{
		builder.inMemoryAuthentication().withUser("alice").password(passwordEncoder().encode(" ")).roles("USER",
				"ADMIN");
//		builder.userDetailsService(userService)
//		.passwordEncoder(passwordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
//		http.authorizeRequests()
//		.antMatchers("/", "/login", "/webjars/**", "/css/**")
//		.permitAll()
//		.antMatchers("/*todo*")
//		.hasAnyRole("USER", "ADMIN")
//		.antMatchers("/welcome")
//		.hasAnyRole("USER", "ADMIN")
//		.antMatchers("/settings")
//		.hasAnyRole("ADMIN")
//		.antMatchers("/**")
//		.hasAnyRole("ADMIN")
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.defaultSuccessUrl("/", true)
//		.failureUrl("/login?error=true")
//		.permitAll()
//		.and()
//		.logout()
//		.logoutSuccessUrl("/login?logout=true")
//		.invalidateHttpSession(true)
//		.permitAll()
//		.and()
//		.exceptionHandling()
//		.accessDeniedPage("/forbidden")
//		.and()
//		.csrf()
//		.disable();
		http.httpBasic().and().authorizeRequests()
		.antMatchers("/users/**")
		.hasRole("USER").and()
		.csrf().disable()
		.headers().frameOptions().disable();
		
		// form based security which is enabled for app
//		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/", "/*todos*/**")
//				.access("hasRole('USER')").and().formLogin();
//		http.authorizeRequests()
//		.antMatchers("/login")
//		.permitAll()
//		.antMatchers("/", "/*todos*/**")
//		.access("hasRole('USER')")
//		.and()
//		.formLogin();
		
		
//		.loginPage("/login")
//		.loginProcessingUrl("/perform_login")
//		.and()
//		.logout()
//		.logoutUrl("/login");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
