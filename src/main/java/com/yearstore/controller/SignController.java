package com.yearstore.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yearstore.k1.sign.BCrypt;
import com.yearstore.k1.sign.SignServiceImpl;
import com.yearstore.k1.sign.SignVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class SignController {
	
	@Autowired
	private SignServiceImpl service;
	
	
	@GetMapping("/index")
	String index() {
		return "index.html";
	}	
	
	@GetMapping("/signup")
	String signup() {
		return "/sign/sign.html";
	}
	
	@GetMapping("/insertmember")
	String insertmember(SignVO vo) {
	    String pwd =BCrypt.hashpw(vo.getPassword(), BCrypt.gensalt());
	    vo.setPassword(pwd);
		service.signup(vo);
		return "redirect:index";
	}
	
	@GetMapping("/IDcheck")
	void IDcheck(HttpServletResponse response, SignVO vo) throws Exception {
		PrintWriter out=response.getWriter();
		String str = service.IDcheck(vo);
	    if (str==null) {
	      if (vo.getUsername()!="") {	
	        out.print("OK");
	      }
	    }else {
	      out.print("False");	
	    }
   
	}
	
	@GetMapping("/loginpage")
	String loginpage() {
		return "/login/login.html";
	}	
	
	@GetMapping("/login")
	String login(SignVO vo, HttpSession session) throws Exception {
		SignVO m =service.login(vo);
		if (m==null || m.equals(null)) {
			return "redirect:/loginpage";
			
		} else {
		   	if (BCrypt.checkpw(vo.getPassword(), m.getPassword())) {
		   		String username = m.getUsername();
		   		String name = m.getName();
		   		session.setAttribute("username", username);
		   		session.setAttribute("name", name);
		   		return "redirect:index";	
		   	}else {
		   		return "redirect:/loginpage";
		   	}		
		}
	}
	
	@GetMapping("/logout")
	 String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    session.invalidate();
	    return "redirect:/index";
	}
	
	@GetMapping("/memberinfo")
	String memberinfo(SignVO vo, HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		vo.setUsername(username);
		model.addAttribute("selectmember", service.selectmember(vo));
		return "/sign/memberinfo.html";
	}
	
	@GetMapping("/updatemember")
	String updatemember(SignVO vo, HttpSession session) {
		
		service.updatemember(vo);
		return "redirect:/index";
	}	
}
