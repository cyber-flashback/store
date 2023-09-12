package com.yearstore.k1.sign;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignDao {
	void signup(SignVO vo);
	void updatemember(SignVO vo);
	SignVO selectmember(SignVO vo);
	SignVO login(SignVO vo);
	String IDcheck(SignVO vo);
}
