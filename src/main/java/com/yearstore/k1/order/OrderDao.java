package com.yearstore.k1.order;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
	void insertorder(OrderVO vo);
	OrderVO ordermember(OrderVO vo);
	List<OrderVO> orderlist (OrderVO vo);
}
