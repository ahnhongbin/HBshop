package com.shop.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //Getter 인스턴스 변수를 반환, Setter 인스턴스 변수를 대입하거나 수정
@RequiredArgsConstructor
//초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
//주로 의존성 주입(Dependency Injection) 편의성을 위해서 사용되곤 합니다.
public enum MyRole {

	USER("ROLE_USER", "회원"), //0 문자열 변수
	ADMIN("ROLE_ADMIN", "관리자"); //대문자로씀 일반적인 규칙

	final String role;
	final String title;

}
