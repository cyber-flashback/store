package com.yearstore.k1.sign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService{

	@Autowired
	private SignDao dao;
	
	@Override
	public void signup(SignVO vo) {
		dao.signup(vo);
		
	}

	@Override
	public String IDcheck(SignVO vo) {
		return dao.IDcheck(vo);
	}

	@Override
	public SignVO login(SignVO vo) {
		// TODO Auto-generated method stub
		return dao.login(vo);
	}

	@Override
	public void updatemember(SignVO vo) {
		dao.updatemember(vo);
		
	}

	@Override
	public SignVO selectmember(SignVO vo) {
		// TODO Auto-generated method stub
		return dao.selectmember(vo);
	}

}
