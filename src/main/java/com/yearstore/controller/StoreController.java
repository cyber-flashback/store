package com.yearstore.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.yearstore.k1.store.StoreServiceImpl;
import com.yearstore.k1.store.StoreVO;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContext;

@Controller
public class StoreController {
	
	String path="";
	
	@Autowired // 서블릿 주입하기
	private ServletContext servletContext;
	
	// @PostConstruct 어노테이션을 설정해놓은 
	// init 메소드는 WAS가 띄워질 때 실행된다. 
	@PostConstruct 
	public void init() {
		path = servletContext.getRealPath("/goodsimg/");
		System.out.println("path:" + path);
	}
	
	@Autowired
	private StoreServiceImpl service;
	
	@GetMapping("/storelist")
	String storelist(StoreVO vo, Model model,
			@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		int total = service.storecount(vo);
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
		vo = new StoreVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		vo.setCh1(c1);
		vo.setCh2(c2);
		if (total == 0) {
			total = 1;
			vo.setEndPage(total);
			vo.setLastPage(total);
		}
		model.addAttribute("paging", vo);
		model.addAttribute("storelist", service.storeselect(vo));
		return "/store/storelist.html";
		
	}
	
	@GetMapping("/storedetaillist")
	String storedetaillist(StoreVO vo, Model model) {
		model.addAttribute("storedetaillist", service.storedetailselect(vo));
		model.addAttribute("buylist", service.buylist(vo));
		return "/store/storedetaillist.html";
		
	}
	
	@GetMapping("/storebuylist")
	String storebuylist(StoreVO vo, Model model) {
		model.addAttribute("storebuylist", service.storebuylist(vo));
		return "/store/storebuylist.html";
	}
	
	@GetMapping("/storeedit")
	String storeedit(StoreVO vo, Model model) {
		model.addAttribute("editlist", service.buylist(vo));
		return "/store/storeedit.html";
	}
	
	@RequestMapping("/storeupdate")
	String storeupdate(Model model,
			@RequestParam(required=false) String[] goodsimg,
			@RequestParam(required=false) String[] sname,
			@RequestParam(required=false) String[] goods,
			@RequestParam(required=false) String[] price,
			@RequestParam(required=false) int[] snum,
			@RequestParam(required=false) MultipartFile[] goodsfile) throws Exception, IOException{
		StoreVO vo = new StoreVO();
		
		long time = System.currentTimeMillis();
		SimpleDateFormat daytime =new SimpleDateFormat("HHmmss");
		String timeStr=daytime.format(time);
		for (int i=0 ; i < goodsimg.length ; i++ ) {
		String goodsimgi = (String) goodsimg[i];
		MultipartFile  file = goodsfile[i];
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
        if (fileName.equals("")) {
           	vo.setSname(sname[i]);
        	vo.setGoods(goods[i]);
        	vo.setPrice(price[i]);
        	vo.setSnum(snum[i]);
        	System.out.println("vo확인"+vo);
        	service.storeupdate2(vo);
        }else {

        	File f = null;
        	 f = new File(path+goodsimg[i]);
        	 if (!goodsimgi.equals("noimg.jpg")) {
        	  f.delete(); 
        	 }
        	
        	f=new File(path+fileName);
    		// 중복파일 존재시
        	if( !file.isEmpty()) {
    			if (f.exists()) {
    			  String onlyFileName =fileName.substring(0,fileName.lastIndexOf(".")); //
    			  String extension =fileName.substring(fileName.lastIndexOf(".")); //
    			  fileName=onlyFileName+"_"+timeStr+extension;
    			}
    					  
    		} else {
    			 fileName= "noimg.jpg"; //
    		}
    		
    		file.transferTo(new File(path+ fileName ));
        	vo.setSname(sname[i]);
        	vo.setGoods(goods[i]);
        	vo.setPrice(price[i]);
        	vo.setSnum(snum[i]);
    		vo.setGoodsimg(fileName);
        	service.storeupdate(vo);
        }
		}

		return "redirect:/storelist";
	}
	
	@GetMapping("/kakaomap")
	String kakaomap(StoreVO vo, Model model) {
		model.addAttribute("kakaomap", service.storedetailselect(vo));
		return "/store/map.html";
	}
			
}
