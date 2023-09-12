package com.yearstore.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.yearstore.k1.store.StoreServiceImpl;
import com.yearstore.k1.store.StoreVO;

// 9fUhHWkn8J23BGz8obUfEF6zSihn8D%2F0juIFYW5quBlH9Ln9LwBqnYYPTpgVjGFvuOxz1s5Yqpmj6YYxEzanQw%3D%3D

@Controller
public class OpenController {
		
	@Autowired
	private StoreServiceImpl Service;
	
	   @GetMapping("apiExplorer.do")
        String apiExplorer(StoreVO vo) throws Exception {
		   
	        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15102255/v1/uddi:c198d295-7df7-49ad-a7a4-a70c8967d23e"); /*URL*/

	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=54SkmUmzIJi50HjBbf4xo6djul7W1FqrjNywdG34oDohy4BoND56PoM7i%2FwbubxcIvvbzhdNxOlNh9CkMCVWlg%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("page","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); 
	        urlBuilder.append("&" + URLEncoder.encode("perPage","UTF-8") + "=" + URLEncoder.encode("1261", "UTF-8"));    
	        
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        
	        BufferedReader rd;
	        
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());

 
	         try {
	        	 
	             JSONParser jsonParser = new JSONParser();
	             JSONObject jsonObject = (JSONObject) jsonParser.parse(sb.toString());
	             
	             // items의 배열을 추출
	             JSONArray InfoArray = (JSONArray) jsonObject.get("data");
	  
	             System.out.println("* items *");
	  
	             for(int i=0; i< InfoArray.size(); i++){
	  
	                 System.out.println("=items_"+i+" ===========================================");
	                  
	                 //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
	                 JSONObject object = (JSONObject) InfoArray.get(i);
	                  
	                 //JSON name으로 추출
	                 System.out.println("Info: 기본주소==>"+object.get("기본주소"));
	                 System.out.println("Info: 상세주소==>"+object.get("상세주소"));
	                 System.out.println("Info: 시군구==>"+object.get("시군구"));
	                 System.out.println("Info: 시도==>"+object.get("시도"));
	                 System.out.println("Info: 업체명==>"+object.get("업체명"));
	                 System.out.println("Info: 연락처==>"+object.get("연락처"));
	                 System.out.println("Info: 주요사업==>"+object.get("주요사업"));
	                 
	                 vo.setAddr(object.get("기본주소").toString());
	                 if (object.get("상세주소") == null) {
	                	 vo.setDeaddr(" ");
	                 } else {
	                 vo.setDeaddr(object.get("상세주소").toString());
	                 }
	                 vo.setDearea(object.get("시군구").toString());
	                 vo.setArea(object.get("시도").toString());
	                 vo.setSname(object.get("업체명").toString());
	                 if (object.get("연락처") == null) {
	                	 vo.setTel(" ");
	                 } else {
	                 vo.setTel(object.get("연락처").toString());
	                 }
	                 vo.setVal(object.get("주요사업").toString());
	                 
	                 Service.storeinsert(vo);
	             } 
	     
	         } catch (Exception e) {
	             // TODO Auto-generated catch block
	             e.printStackTrace();
	         }

	        
		return "redirect:end.html";
        
    }
}