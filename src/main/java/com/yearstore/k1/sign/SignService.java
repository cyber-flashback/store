package com.yearstore.k1.sign;

import java.util.List;

public interface SignService {
	void signup(SignVO vo);
	void updatemember(SignVO vo);
	SignVO selectmember(SignVO vo);
	SignVO login(SignVO vo);
	String IDcheck(SignVO vo);
}
