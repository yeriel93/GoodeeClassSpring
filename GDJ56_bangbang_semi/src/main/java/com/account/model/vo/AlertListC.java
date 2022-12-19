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

public class AlertListC {
	private int alertNo;
	private int sendUserNo;
	private int receiveUserNo;
	private int propertyNo;
	private String content;
	private Date alertDate;
	private String officename;
	private String telephone;
	private String renttype;
	private int deposit;
	private int monthlycharge;
	private String renamedFilename;
	private String feedback_content;
}
