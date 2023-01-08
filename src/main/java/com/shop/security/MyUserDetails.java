package com.shop.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.shop.domain.entity.MemberEntity;

import lombok.Getter;

@Getter //Getter 인스턴스 변수를 반환, Setter 인스턴스 변수를 대입하거나 수정
public class MyUserDetails extends User{

	private static final long serialVersionUID = 1L; //long은 64비트 정수
	private String email;// username과 동일하나 추가로 만들수 있다.
	private String name;//사용자 기준 : 이름정보 principal
	
	public MyUserDetails(MemberEntity e) {
		super(e.getEmail(), e.getPass(), e.getRoleset().stream()
				.map(role-> new SimpleGrantedAuthority(role.role))
				.collect(Collectors.toSet()));
		
		email=e.getEmail();
		name=e.getName();
	}

}
/*
 * 람다식(Lambda Expression)이란 함수를 하나의 식(expression)으로 표현한 것이다.
 * 함수를 람다식으로 표현하면 메소드의 이름이 필요 없기 때문에,
 * 람다식은 익명 함수(Anonymous Function)의 한 종류라고 볼 수 있다.
 * 익명함수(Anonymous Function)란 함수의 이름이 없는 함수로, 익명함수들은 모두 일급 객체이다.
 */

/*
 * 일급 객체란?
 * 변수나 데이터 구조 안에 담을 수 있다.
 * 파라미터로 전달 할 수 있다.
 * 반환값으로 사용할 수 있다.
 * 할당에 사용된 이름과 무관하게 고유한 구별이 가능하다.
 */