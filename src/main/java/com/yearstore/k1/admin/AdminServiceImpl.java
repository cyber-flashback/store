package com.yearstore.k1.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao dao;
	
	@Override
	public void insertorder(AdminVO vo) {
		dao.insertorder(vo);
	}

	@Override
	public void insertprice(AdminVO vo) {
		dao.insertprice(vo);
	}

	@Override
	public AdminVO selectordernumber(AdminVO vo) {
		// TODO Auto-generated method stub
		return dao.selectordernumber(vo);
	}

	@Override
	public void deleteorderlist(AdminVO vo) {
		dao.deleteorderlist(vo);
	}

	@Override
	public List<AdminVO> orderbuylist(AdminVO vo) {
		// TODO Auto-generated method stub
		return dao.orderbuylist(vo);
	}

	@Override
	public List<AdminVO> ordernumberlist(AdminVO vo) {
		// TODO Auto-generated method stub
		return dao.ordernumberlist(vo);
	}

	@Override
	public List<AdminVO> adminlist(AdminVO vo) {
		// TODO Auto-generated method stub
		return dao.adminlist(vo);
	}

	@Override
	public int admincount(AdminVO vo) {
		// TODO Auto-generated method stub
		return dao.admincount(vo);
	}


}
