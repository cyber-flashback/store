package com.yearstore.k1.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao dao;
	
	@Override
	public List<OrderVO> orderlist(OrderVO vo) {
		// TODO Auto-generated method stub
		return dao.orderlist(vo);
	}

	@Override
	public void insertorder(OrderVO vo) {
		dao.insertorder(vo);		
	}

	@Override
	public OrderVO ordermember(OrderVO vo) {
		// TODO Auto-generated method stub
		return dao.ordermember(vo);
	}

}
