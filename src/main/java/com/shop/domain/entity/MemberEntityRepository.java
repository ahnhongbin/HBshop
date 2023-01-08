package com.shop.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long>{ //제네릭 표현
	// 항상 MemberEntity 매핑한다
	Optional<MemberEntity> findByEmail(String email);  // Optional 자바8버전이상 사용가능
	// Optional<T>는 NULL이 올 수 있는 값을 감싸는 Wrapper 클래스로, NPE가 발생하지 않도록 도와준다. 
	// Wrapper 클래스는 기본 자료타입(primitive type)을 객체로 다루기 위해서 사용하는 클래스들을 래퍼 클래스(wrapper class)라고 합니다.
	
	//자바로 프로그래밍 하다보면 정의되지 않은 객체에 대해 NULL값을 고려하게 되는 경우가 발생한다.
	//안정적인 실행을 위해서는 NULL값을 처리해 NPE(NullPointerException)가 발생하지 않게 체크해야 한다
}
