package com.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmailCertifyServlet
 */
@WebServlet("/user/certifyEmail.bb")
public class EmailCertifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailCertifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      ResourceBundle rb;
//      rb=ResourceBundle.getBundle("mail_server");
      
      //mail 서버 설정
		
		//사용자가 입력한 이메일 주소
		String userEmail=request.getParameter("userEmail");		
//		System.out.println(userEmail);
		
		//SMTP 서버정보 설정 - TLS
		Properties props = System.getProperties();
				
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.smtp.ssl.enable", "false");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
		Authenticator auth=new MailAuth();
		Session session=Session.getDefaultInstance(props, auth);
		MimeMessage msg=new MimeMessage(session);

		//인증번호
		int certNum=(int)(Math.random()*9999999+1111111);
//		System.out.println(certNum);
		
		//인증번호 세션에 저장
		HttpSession ss=request.getSession();
		ss.setAttribute("certNum", certNum);	
		
		try {
			msg.setSentDate(new Date());
			
			msg.setFrom(new InternetAddress("gdj56.bangbang.gmail.com","BANGBANG"));
			InternetAddress to=new InternetAddress(userEmail);
			msg.setRecipient(Message.RecipientType.TO, to);
			msg.setSubject("'방 구해줘 방' 회원가입 인증코드","UTF-8");
			msg.setText("안녕하세요. '방 구해줘 방' 회원가입 인증코드 입니다. \n 인증코드 :  "+certNum, "UTF-8");
			
			Transport.send(msg);
			
		}catch(AddressException e){
			e.printStackTrace();
		}catch(MessagingException e) {
			e.printStackTrace();
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		

		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
