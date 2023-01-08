package com.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity //@Bean코드를 사용할때 @Configuration을 사용
public class MySecurityConfig {
	
	
	@Bean //SecurityFilterChain,filterChain 객체를 저장해서 사용하는 요소
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorize ->
			authorize
				.antMatchers("/","/signup","/common/**").permitAll()
				// /* 해당하는 주소 모든 문자열, /** 폴더포함 모든 하위 요소들
				//권한이 필요하지 않은 주소나 주소패턴들
				//antMatchers 인증이 필요치않을때 permitAll을 입력
				//위에서 설정한 주소 이외 나머지 주소들은 인증을 해야 돼요->인증을 하기 위해서 -> 로그인
				.anyRequest().authenticated() 
				//anyRequest 모든 요청주소에 대해서는 authenticated 인증정보를 획득해야한다
				//회원가입을 통해서
			);
	
		http.formLogin(login->
						login
						.loginPage("/signin") //get
						.usernameParameter("email")
						.passwordParameter("pass")
						.loginProcessingUrl("/signin") //post로 처리하고 security가 처리해줌
						.permitAll());
			//.formLogin(); , @Bean코드를 하나 더 만들거나 , http.formLogin으로 3가지 방법으로 만들 수 있다
			//.formLogin(withDefaults()) 생략
			//.httpBasic(withDefaults());생략
		//http.csrf().disable();
		return http.build();
	}

}
