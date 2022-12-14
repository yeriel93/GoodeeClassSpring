package com.property.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Property {
	private int propertyNo;
	private int brokerNo;
	private String address;
	private double longitude;
	private double latitude;
	private String floor;
	private String renttype;
	private int deposit;
	private int monthlyCharge;
	private int managementCharge;
	private char electric;
	private char water;
	private char gas;
	private String propertyStructure;
	private double area;
	private Date vacancyDate;
	private String vacancy;
	private char pet;
	private char parking;
	private String detail;
	private Date enrollDate;
	private Date editDate;
	private char hiding;
	private String thumbnail;
	private Files[]	files;
}
