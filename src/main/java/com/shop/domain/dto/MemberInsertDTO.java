package com.shop.domain.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.domain.entity.MemberEntity;

import lombok.Setter;

@Setter //lombok을 사용하기때문에 Setter하면 따로따로 안만들어줘도된다
public class MemberInsertDTO {
	
	private String email;
	private String pass;
	private String name;
	
	
	public MemberEntity toEntity(PasswordEncoder pe) {
		// TODO Auto-generated method stub
		return MemberEntity.builder()
				.email(email)
				.pass(pe.encode(pass)) //비밀번호 암호화해서 entity에 매핑
				.name(name)
				.build();
	}

}
