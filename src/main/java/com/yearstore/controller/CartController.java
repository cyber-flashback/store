package com.yearstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yearstore.k1.cart.CartServiceImpl;
import com.yearstore.k1.cart.CartVO;
import com.yearstore.k1.order.OrderServiceImpl;
import com.yearstore.k1.order.OrderVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private CartServiceImpl service;
	
	@Autowired
	private OrderServiceImpl Oservice;
	
	@GetMapping("/Cartinsert")
	String Cartinsert(CartVO vo, @RequestParam String[] idx) {
		System.out.println("vo확인"+idx[0]);
		service.insertcart(vo);
		return "redirect:/storedetaillist?idx="+idx[0];
	}
	
	@GetMapping("/Cartlist")
	String Cartlist(CartVO vo, Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		vo.setUsername(username);
		model.addAttribute("cartlist", service.cartlist(vo));
		model.addAttribute("cartcount", service.count(vo));
		model.addAttribute("cartsname", service.cartsname(vo));
		
		return "/cart/cartlist.html";
	}
	
	@GetMapping("/Cartupdate")
	String Cartupdate(Model model, @RequestParam int[] amount, @RequestParam String[] goods, @RequestParam String[] username) {
		CartVO vo = new CartVO();
		for (int i = 0 ; i < amount.length ; i++) {
			vo.setAmount(amount[i]);
			vo.setGoods(goods[i]);
			vo.setUsername(username[0]);
			service.updatecart(vo);
		}
		return "redirect:/Cartlist";
	}
	
	@GetMapping("/CartAlldelete")
	String CartAlldelete(CartVO vo, Model model) {
		service.deleteAllcart(vo);
		return "redirect:/Cartlist";
	}
	
	@GetMapping("/Cartdelete")
	String Cartdelete(CartVO vo, Model model) {
		service.deletecart(vo);
		return "redirect:/Cartlist";
		
	}
	
	@GetMapping("/Cartorder")
	String Cartorder(Model model, CartVO vo,
			@RequestParam String[] sname,
			@RequestParam String[] goods,
			@RequestParam int[] price,
			@RequestParam int[] amount,
			@RequestParam String[] username
			) {
		OrderVO ovo = new OrderVO();
		for (int i = 0 ; i < amount.length ; i++) {
			ovo.setSname(sname[i]);
			ovo.setGoods(goods[i]);
			ovo.setPrice(price[i]);
			ovo.setAmount(amount[i]);
			ovo.setUsername(username[0]);
			Oservice.insertorder(ovo);
			}
		service.deleteAllcart(vo);
		return "redirect:/Orderlist";
	}
	
}
