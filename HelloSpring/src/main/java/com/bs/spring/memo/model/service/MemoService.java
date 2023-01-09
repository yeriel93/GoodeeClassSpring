package com.bs.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoService {
	
	int insertMemo(Memo m);
	
	List<Memo> selectMemoAll();
	
	int selectMemoListCount();

	List<Memo> selectMemoListPage(Map<String, Integer> param);
}
