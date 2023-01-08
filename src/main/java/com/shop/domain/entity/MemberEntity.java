package com.shop.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.shop.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate //JPA Entity에 사용되는 어노테이션, 하나의 테이블에 많은 수의 컬럼중에 몇몇개의 컬럼만 자주 업데이트하는 경우에 사용함
@Builder //빌터패턴을 통해서 객체를 만들수있다
@AllArgsConstructor //필드가 아규먼트로 존재하는 생성자 파라미터가 많을 경우 가독성이 좋지 않다.
@NoArgsConstructor //기본생성자  컨트롤+스페이스눌렀을때 생기는 생성자
@Getter //Getter 인스턴스 변수를 반환, Setter 인스턴스 변수를 대입하거나 수정
@Table(name ="MyMember" )
@Entity
public class MemberEntity { //DB라고 생각.
	//PK를 나타내기위해 @Id 어노테이션을 사용, 생성 전략을 정의하기 위해 @GeneratedValue를 사용
	@Id //데이터베이스 테이블의 기본 키(PK)와 객체의 필드를 매핑시켜주는 어노테이션, @Id만 사용할 경우 기본 키를 직접 할당해 주어야 한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키를 직접 할당하는 대신 데이터베이스가 생성해주는 값을 사용하려면 사용해주면 된다.
	private long no; //no값은 자동으로 반영,
	//속성으로는 strategy가 있는데, 이를 통해 자동 생성 전략을 지정해 줄 수 있고 IDENTITY 전략은 기본 키 생성을 데이터베이스에 위임하는 전략
	//nullable은 DDL에 적용되고 default값은 true이고 false로 설정하면 DDL 생성 시 not null 제약 조건이 붙는다.
	@Column(nullable = false, unique = true) // unique 중복이메일 
	private String email; 
	@Column(nullable = false) // @Column은 객체 필드와 DB 테이블 컬럼을 맵핑
	private String pass;
	@Column(nullable = false)
	private String name;
	
	@Column(columnDefinition = "timestamp") //columnDefinition은 원하는 컬럼 타입으로 데이터 추출
	@CreationTimestamp //INSERT 쿼리가 발생할 때, 현재 시간을 값으로 채워서 쿼리를 생성한다.
	private LocalDateTime createdDate;
	@Column(columnDefinition = "timestamp")
	@UpdateTimestamp //UPDATE 쿼리가 발생할 때, 현재 시간을 값으로 채워서 쿼리를 생성한다.
	private LocalDateTime updatedDate;
	//@CollectionTable(name = "테이블이름")
	//롤(ROLE): 1:N 1 대 다(roleset) 연관관계로 폴인키가 만들어짐 
	@Enumerated(EnumType.STRING)//저장유형 DB에 문자열로 저장
	@ElementCollection(fetch = FetchType.EAGER) //값 타입 컬렉션을 매핑할 때 사용, FetchType.EAGER는 즉시로딩, LAZY지연로딩 실무에서는 많이씀
	@Builder.Default //기본값을 설정을위한 어노테이션
	private Set<MyRole> roleset=new HashSet<>(); //HashSet : 순서를 보장 하지 않는 set
	//List : 저장공간이 필요에 의해 자동으로 늘어나고 순서가있고 중복을허용 
	//Set : 집합,순서가 없다. 집합이므로 중복된 데이터가 들어갈 수 없고 빠른 속도를 가지고 있다.
	//Map : 키와 데이터를 같이 저장,Key(키)랑 Value(값)으로 나눠서 데이터 관리, 순서는 없으며, 키에 대한 중복은 없고 빠른 속도를 가지고 있다.
	
	//role 저장하는 편의 메서드
	public MemberEntity addRole(MyRole role) {
		roleset.add(role); //편의 메서드를 만들어줌
		return this;
	}
}
