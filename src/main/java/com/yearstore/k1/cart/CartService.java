package com.yearstore.k1.cart;

import java.util.List;

public interface CartService {
	void insertcart(CartVO vo);
	void updatecart(CartVO vo);
	void deletecart(CartVO vo);
	void deleteAllcart(CartVO vo);
	String count(CartVO vo);
	List<CartVO> cartlist(CartVO vo);
	List<CartVO> cartsname(CartVO vo);
}
