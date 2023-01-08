package com.shop.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.domain.entity.MemberEntity;
import com.shop.domain.entity.MemberEntityRepository;

@Service //해당 클래스를 루트 컨테이너에 빈(Bean) 객체로 생성해주는 어노테이션
public class MyUserDetailsService implements UserDetailsService{
	//implements는 interface에 상속, extends는 일반클래스와 abstract클래스에 상속
	//회원테이블에 접근하기위해 DAO가 필요하다.(DAO는 DB의 데이터에 접근을 위한 객체) 
	@Autowired //필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입한다. (생성자,setter,필드) 
	private MemberEntityRepository memberEntityRepository;
	
	@Override //상위클래스에있는 메소드를 하위클래스로 재정의한것
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("email:"+email);
		//DB에 회원이 존재하는지 확인
		//쿼리메서드 //findBy는 where절
		//컬럼명은 Entity에 정의되어있는 컬럼명을 씀
		//email컬럼을 쓰기위해서는 MemberEntity 접근하는 email컬럼(PK가아님)
		Optional<MemberEntity> result=memberEntityRepository.findByEmail(email);
		if(result.isEmpty()) throw new UsernameNotFoundException("존재하지 않는 회원"); //result가 비어있으면 발생시킴
		
		return new MyUserDetails(result.get()); 
		//MemberEntity에있는 객체가 존재를하면 UserDetails로 만들어줌
	}
	
}
 