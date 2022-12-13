package com.user.model.vo;

import java.sql.Date;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {
	private int userNo;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
	private Date birthday;
	private char userLevel;
	private Date enrollDate;
	private Date editDate;
}
