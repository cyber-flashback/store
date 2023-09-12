package com.yearstore.k1.store;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreDao dao;
	
	@Override
	public void storeinsert(StoreVO vo) {
		dao.storeinsert(vo);
		
	}

	@Override
	public List<StoreVO> storeselect(StoreVO vo) {
		// TODO Auto-generated method stub
		return dao.storeselect(vo);
	}

	@Override
	public StoreVO storedetailselect(StoreVO vo) {
		// TODO Auto-generated method stub
		return dao.storedetailselect(vo);
	}

	@Override
	public List<StoreVO> buylist(StoreVO vo) {
		// TODO Auto-generated method stub
		return dao.buylist(vo);
	}

	@Override
	public StoreVO storebuylist(StoreVO vo) {
		// TODO Auto-generated method stub
		return dao.storebuylist(vo);
	}

	@Override
	public int storecount(StoreVO vo) {
		// TODO Auto-generated method stub
		return dao.storecount(vo);
	}

	@Override
	public List<StoreVO> storeselectsname(StoreVO vo) {
		// TODO Auto-generated method stub
		return dao.storeselectsname(vo);
	}

	@Override
	public List<StoreVO> storeselectval(StoreVO vo) {
		// TODO Auto-generated method stub
		return dao.storeselectval(vo);
	}

	@Override
	public void storeupdate(StoreVO vo) {
		dao.storeupdate(vo);
		
	}

	@Override
	public void storeupdate2(StoreVO vo) {
		dao.storeupdate2(vo);
		
	}
	

}
