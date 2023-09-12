package com.yearstore.k1.cart;

import lombok.Data;

@Data
public class CartVO {
	private String username;
	private String sname;
	private String goods;
	private int price;
	private int amount;
	private String goodsimg;
}
