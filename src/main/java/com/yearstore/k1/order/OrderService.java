package com.yearstore.k1.order;

import java.util.List;

public interface OrderService {
	void insertorder(OrderVO vo);
	OrderVO ordermember(OrderVO vo);
	List<OrderVO> orderlist (OrderVO vo);
}
