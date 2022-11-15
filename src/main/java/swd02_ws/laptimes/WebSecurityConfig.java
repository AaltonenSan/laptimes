package swd02_ws.laptimes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import swd02_ws.laptimes.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()
				.antMatchers("/",
						"/static/**",
						"/h2-console/**",
						"/register",
						"/tracks/",
						"/css/**",
						"/images/**",
						"/js/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.csrf().ignoringAntMatchers("/h2-console/**")
			.and()
			.headers().frameOptions().sameOrigin()
			.and()
			.formLogin()
				.loginPage("/login/")
				.defaultSuccessUrl("/", true)
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
				.httpBasic();
			return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}