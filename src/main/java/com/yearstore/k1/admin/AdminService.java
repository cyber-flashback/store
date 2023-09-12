package com.yearstore.k1.admin;

import java.util.List;

public interface AdminService {
	void insertorder (AdminVO vo);
	void insertprice (AdminVO vo);
	void deleteorderlist (AdminVO vo);
	AdminVO selectordernumber (AdminVO vo);
	List<AdminVO> orderbuylist (AdminVO vo);
	List<AdminVO> ordernumberlist (AdminVO vo);
	List<AdminVO> adminlist (AdminVO vo);
	int admincount(AdminVO vo);
}
