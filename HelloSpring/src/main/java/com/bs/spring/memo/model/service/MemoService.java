package com.bs.spring.memo.model.service;

import java.util.List;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoService {
	
	int insertMemo(Memo m);
	
	List<Memo> selectMemoAll();
	
	int selectMemoListCount();

}
