package com.bs.spring.model.vo;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
//������̼��� �̿��ؼ� SpringBean���� ����ϱ�
@Component
public class Food {
	private String name;
	private int price;
	private String type;
	private Person p;
	
//	@Autowired
//	@Qualifier(value="yeonji")
//	public void setPerson(Person p) {
//		this.p=p;
//	}
}
