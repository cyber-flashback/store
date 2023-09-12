package com.yearstore.k1.store;

import java.util.List;

public interface StoreService {
	void storeinsert(StoreVO vo);
	void storeupdate(StoreVO vo);
	void storeupdate2(StoreVO vo);
	List<StoreVO> storeselect(StoreVO vo);
	List<StoreVO> storeselectsname(StoreVO vo);
	List<StoreVO> storeselectval(StoreVO vo);
	StoreVO storedetailselect(StoreVO vo);
	List<StoreVO> buylist(StoreVO vo);
	StoreVO storebuylist(StoreVO vo);
	int storecount(StoreVO vo);
}
