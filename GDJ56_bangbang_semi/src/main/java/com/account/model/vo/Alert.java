package com.account.model.vo;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alert {
	private int alertNo;
	private int sendUserNo;
	private int receiveUserNo;
	private int propertyNo;
	private String content;
	private Date alertDate;
	
}
