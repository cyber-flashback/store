package com.yearstore.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.yearstore.k1.admin.AdminServiceImpl;
import com.yearstore.k1.admin.AdminVO;
import com.yearstore.k1.sign.SignServiceImpl;
import com.yearstore.k1.sign.SignVO;
import com.yearstore.k1.store.StoreVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private AdminServiceImpl service;
	
	@Autowired
	private SignServiceImpl idservice;
	
	@GetMapping("/Adminorder")
	String insertorder(AdminVO vo,
			@RequestParam String[] sname,
			@RequestParam String[] goods,
			@RequestParam int[] price,
			@RequestParam int[] amount
			) {
		for (int i = 0 ; i < amount.length ; i++) {
			AdminVO avo = new AdminVO();
			avo.setUsername(vo.getUsername());
			avo.setSname(sname[i]);
			avo.setGoods(goods[i]);
			avo.setPrice(price[i]);
			avo.setAmount(amount[i]);
			avo.setName(vo.getName());
			avo.setZipcode(vo.getZipcode());
			avo.setAddress(vo.getAddress());
			avo.setAddress2(vo.getAddress2());
			service.insertorder(avo);
		}
		service.insertprice(vo);
		service.deleteorderlist(vo);
		return "redirect:Orderend";
	}
	
	@GetMapping("/Orderend")
	String Orderend(AdminVO vo, Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		vo.setUsername(username);
		model.addAttribute("ordernumber", service.selectordernumber(vo));
		return "/order/orderend.html";
	}
	
	@GetMapping("/Orderbuylist")
	String Orderbuylist(AdminVO vo, Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		vo.setUsername(username);
		model.addAttribute("ordernumberlist",service.ordernumberlist(vo));
		model.addAttribute("orderbuylist",service.orderbuylist(vo));
		System.out.println(vo.getOrdernumber());
		return "/order/orderbuylist.html";
	}
	
	@GetMapping("/adminlist")
	String adminlist(AdminVO vo, Model model,
			@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		int total = service.admincount(vo);
		System.out.println(total);
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "10";
		}
		String c1 = vo.getCh1();
		String c2 = vo.getCh2();
		vo = new AdminVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		vo.setCh1(c1);
		vo.setCh2(c2);
		if (total == 0) {
			total = 1;
			vo.setEndPage(total);
			vo.setLastPage(total);
		}
		System.out.println(vo.getEndPage());
		model.addAttribute("paging", vo);
		System.out.println("페이징확인"+vo);
		model.addAttribute("adminlist", service.adminlist(vo));
		return "/admin/adminlist.html";
	}
	
	@GetMapping("/admindetaillist")
	String admindetaillist(AdminVO vo,SignVO svo, Model model) {
		model.addAttribute("adminid", idservice.IDcheck(svo));
		model.addAttribute("ordernumberlist",service.ordernumberlist(vo));
		model.addAttribute("orderbuylist",service.orderbuylist(vo));
		
		return "/admin/admindetaillist.html";
	}
	
	
}
