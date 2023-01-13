package com.report.model.vo;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Report {
	private int userNo;
	private int propertyNo;
	private String content;
	private Date reportDate;
	private Date processingDate;
}
