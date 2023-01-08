package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.domain.dto.MemberInsertDTO;
import com.shop.service.MemberService;

@Controller
public class MemberController {

	@Autowired //필드 인젝션
	private MemberService service;
	
	//권한이필요한 주소
	@GetMapping("/signin-page")
	public String loginpage() {
		return "redirect:/";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "sign/signin"; //View - thymeleaf엔진
		//spring.tymeleaf.prefix=classpath:/templates/
		//spring.thymeleaf.suffix=.html
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "sign/signup";
	}
	
	@PostMapping("/signup") 
	public String signup(MemberInsertDTO dto) {
		
		service.save(dto);
		
		return "sign/signup";
	}
		
}
 