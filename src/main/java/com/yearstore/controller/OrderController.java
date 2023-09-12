package com.yearstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yearstore.k1.order.OrderServiceImpl;
import com.yearstore.k1.order.OrderVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	OrderController(){
		System.out.println("orderController 확인");
	}

	@Autowired
	private OrderServiceImpl service;
	
		
	@GetMapping("/Orderlist")
	String Orderlist(OrderVO vo, Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		vo.setUsername(username);
		model.addAttribute("orderlist", service.orderlist(vo));
		model.addAttribute("ordermember", service.ordermember(vo));
		System.out.println("ordermember"+service.ordermember(vo));
		return "/order/orderlist.html";
	}
}