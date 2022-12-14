package com.broker.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Broker {
	private int brokerNo;
	private int userNo;
	private String registrationNo;
	private String officeName;
	private String officeAddress;
	private String telephone;
	private char admissionState;
	private int propertyCount;
	private Date RestrictionDate;
	private Date enrollDate;
	private Date editDate;
}
