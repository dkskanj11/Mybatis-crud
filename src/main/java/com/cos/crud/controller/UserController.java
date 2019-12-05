package com.cos.crud.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.cos.crud.model.User;
import com.cos.crud.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository uRepo;
	
//	@PostMapping("/")
	
	@PostMapping("/user/save")
	public String save(User user) {
		
		try {
			uRepo.save(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/join")
	public String join() {
		
		return "/user/join";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	@PostMapping("/user/login")
	public String login(HttpSession session, String username, String password) {
		
		User u = uRepo.findByUsernameAndPassword(username,password);
		
		if(u != null) {
			session.setAttribute("user", u);
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/user/profile")
	public String profile() {
		
		return "user/UserProfile";
	}
}
