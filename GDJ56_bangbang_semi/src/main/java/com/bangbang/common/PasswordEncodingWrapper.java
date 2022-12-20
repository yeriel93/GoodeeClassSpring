package com.bangbang.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncodingWrapper extends HttpServletRequestWrapper {

	public PasswordEncodingWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	private String getSha512(String value) {	
		
		MessageDigest md=null;
		try {
			md=MessageDigest.getInstance("SHA-512");
			
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] bytes=value.getBytes();
		md.update(bytes);
		byte[] encryptData=md.digest();
		
		return Base64.getEncoder().encodeToString(encryptData);
	}
	
	@Override
	public String getParameter(String name) {
		if(name.contains("Pw")) {
			return getSha512(super.getParameter(name));
		}
		return super.getParameter(name);
	}

}
