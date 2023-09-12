package com.yearstore.k1.order;

import lombok.Data;

@Data
public class OrderVO {
	private String sname;
	private String goods;
	private int price;
	private int amount;
	private int deliveryfee;
	private String username;
	private String address;
	private String address2;
	
	private String name;
	private String zipcode;
	}
