package com.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shop.domain.dto.MemberInsertDTO;
import com.shop.domain.entity.MemberEntityRepository;
import com.shop.security.MyRole;
import com.shop.service.MemberService;

@Service //해당 클래스를 루트 컨테이너에 빈(Bean) 객체로 생성해주는 어노테이션
public class MemberServiceProcess implements MemberService {
	//implements는 interface에 상속, extends는 일반클래스와 abstract클래스에 상속
	
	@Autowired //필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다. (생성자,setter,필드) 
	private MemberEntityRepository repository;
	
	@Autowired //필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다. (생성자,setter,필드) 
	private PasswordEncoder passwordEncoder;
	
	@Override //상위클래스에있는 메소드를 하위클래스로 재정의한것
	public void save(MemberInsertDTO dto) {
		//mybatis를 쓸경우 mapper를 쓰거나 DB를 미리 구축한다
		//DAO : mybatis(mapper) - jpa(repository), (DAO는 DB의 데이터에 접근을 위한 객체) 
		//회원가입정보(dto)를 테이블에 저장
		repository.save(dto.toEntity(passwordEncoder).addRole(MyRole.USER));
		//save는 JpaRepository가 만들어논 메서드
		//save하기전에 toEntity안에있는 요소들 먼저 처리
		//MemberEntity안의 builder를 만들어줄때 passwordEncoder를 이용해서
		//encode를해가지고 인코딩되있는것을 셋팅해서 build해서 Entity안에서 만들어주기때문에
		//결국 Entity는 인코딩되어있는 엔티티 
	}

}
