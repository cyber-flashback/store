package com.yearstore.k1.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao dao;
	
	@Override
	public void insertcart(CartVO vo) {
		dao.insertcart(vo);
		
	}

	@Override
	public List<CartVO> cartlist(CartVO vo) {
		// TODO Auto-generated method stub
		return dao.cartlist(vo);
	}

	@Override
	public void updatecart(CartVO vo) {
		dao.updatecart(vo);
		
	}

	@Override
	public void deleteAllcart(CartVO vo) {
		dao.deleteAllcart(vo);
		
	}

	@Override
	public void deletecart(CartVO vo) {
		dao.deletecart(vo);
		
	}

	@Override
	public String count(CartVO vo) {
		// TODO Auto-generated method stub
		return dao.count(vo);
	}

	@Override
	public List<CartVO> cartsname(CartVO vo) {
		// TODO Auto-generated method stub
		return dao.cartsname(vo);
	}

}
