package com.board;

import org.apache.coyote.http11.Http11OutputBuffer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.board.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Log4j2
public class WebSecurityConfig {
	
	private final AuthSuccessHandler authSuccessHandler;
	private final AuthFailureHandler authFailureHandler;
	private final UserDetailsServiceImpl userDetailsService;
	
	// 스프링시큐리티에서 암호화 관련 객체를 가져다가 스프링빈으로 등록 
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 스프링 시큐리티 적용 제외 대상 --> 스프링빈을 등록
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/images","/css","/profile", "/js");
	}
	
	// 스프링시큐리티 로그인 화면 사용 비활성화, CSRF/CORS 공격 방어용 보안 설정 비활성화 
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception {
		
		// 스프링 시큐리티의 FormLogin 설정 
		http.formLogin((login) -> login
				.usernameParameter("userid") // Spring Security에서 사용하는 id 변수명 username. 그래서, 사용자 지정 id 변수명을 이렇게 가르쳐 줘야 함.
				.loginPage("/member/login")  // 사용자 지정 login 화면 및 명령문을 사용할 때 그 경로를 스프링 시큐리티에게 공지...
				.successHandler(authSuccessHandler)		 
				.failureHandler(authFailureHandler));
		
		// 스프링 시큐리티의 자동로그인 설정
		http
			.rememberMe((me) -> me
				.key("jingom368")
				.alwaysRemember(false)
				.tokenValiditySeconds(3600*24*7)
				.rememberMeParameter("remember-me")
				.userDetailsService(userDetailsService)
				.authenticationSuccessHandler(authSuccessHandler));
		
		// 스프링 시큐리티의 접근 권한 설정(Authentication)
		http
			.authorizeHttpRequests((authz) -> authz
					.requestMatchers("/member/**").permitAll()
					.requestMatchers("/board/**").hasAnyAuthority("USER","MASTER")
					.requestMatchers("/master/**").hasAnyAuthority("MASTER")
					.anyRequest().authenticated());
		
		// 세션 설정
		http 
			.sessionManagement((management) -> management
					.maximumSessions(1)
					.maxSessionsPreventsLogin(false)
					.expiredUrl("/member/login"));
		
		// 스프링 시큐리티의 로그 아웃
		http
			.logout((logout) -> logout
			.logoutUrl("/member/logout")
			.logoutSuccessUrl("/member/login")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID","remember-me")
			.permitAll());
		
		http
			.csrf((csrf) -> csrf.disable());
		http
			.cors((cors) -> cors.disable());
		
		log.info("************************* 스프링 시큐리티 설정 완료 *************************");
		
		return http.build();
	}
}