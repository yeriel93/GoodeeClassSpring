package com.user.controller;

import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
	PasswordAuthentication pa;
	
	public MailAuth() {
	      String mail_id="";
	      String mail_pw="";
	      
	      try{	    	  
	          String mail = "C:\\Users\\yurim\\git\\GDJ56_bangbang_semi\\GDJ56_bangbang_semi\\src\\main\\resources\\sql\\user\\mail_server.properties";      
	          Properties props = new Properties();   
	          FileInputStream fis = new FileInputStream(mail);
	          props.load(new java.io.BufferedInputStream(fis));                      
	                     
	          mail_id=props.getProperty("id") ;
	          mail_pw=props.getProperty("pw") ;

//	          System.out.println(user) ;
//	          System.out.println(password);
	         
	      }catch(Exception e){
	         e.printStackTrace();
	      }
	      
	      pa=new PasswordAuthentication(mail_id, mail_pw);     
	
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
	
}
