package com.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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

import com.bangbang.common.PasswordEncodingWrapper;
import com.user.model.service.UserService;

/**
 * Servlet implementation class TempPasswordServlet
 */
@WebServlet(name="tempPw", urlPatterns = "/user/tempPw.bb")
public class TempPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TempPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//입력한 정보 받아와서		
		String userId=request.getParameter("userId"); 
		String userEmail=request.getParameter("userEmail"); 
		String userPhone=request.getParameter("userPhone"); 
//		System.out.println(userId);
//		System.out.println(userEmail);
//		System.out.println(userPhone);
		
		//일치한다면?
		int result=UserService.getUserService().searchPw(userId,userEmail,userPhone);
//		System.out.println("제발" + result);
		if(result>1) {
			//일치할때
			//사용자가 입력한 이메일 주소

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
			
			//임시비밀번호 만들어서
			Random rnd=new Random();
			StringBuffer tempPw=new StringBuffer(); //임시비번
			for(int i=0;i<10;i++) {
				if(rnd.nextBoolean()) {
					tempPw.append((char)((int)(rnd.nextInt(26)+97)));
				}else {
					tempPw.append(rnd.nextInt(10));
				}
			}
//			System.out.println(tempPw);		
			
			//2022-12-22 12:43 추가
			PasswordEncodingWrapper pew = new PasswordEncodingWrapper(request);
			StringBuffer tempPw2 = new StringBuffer(pew.makeTempPassword(tempPw.toString()));
//			System.out.println(tempPw);
//			System.out.println("테스트");
//			System.out.println(tempPw2);
			
			

			//db에서 변경해주기
			int changeResult=UserService.getUserService().tempPassword(result,tempPw2.toString()); //result는 USER_NO
			
			
			//임시비밀번호 잠깐 저장 -> 할필요 없네 ?
			request.setAttribute("tempPw", tempPw);	
//			HttpSession ss=request.getSession();
//			ss.setAttribute("certNum", certNum);	
			
			try {
				msg.setSentDate(new Date());
				
				msg.setFrom(new InternetAddress("gdj56.bangbang.gmail.com","BANGBANG"));
				InternetAddress to=new InternetAddress(userEmail);
				msg.setRecipient(Message.RecipientType.TO, to);
				msg.setSubject("'방 구해줘 방' "+userId+"님의 임시 비밀번호","UTF-8");
				msg.setText("안녕하세요. '방 구해줘 방' "+userId+"님의 임시비밀번호 입니다. \n "
						+ "로그인 후 새로운 비밀번호로 변경해주세요.\n 임시비밀번호 :  "+tempPw, "UTF-8");
				
				Transport.send(msg);
				
			}catch(AddressException e){
				e.printStackTrace();
			}catch(MessagingException e) {
				e.printStackTrace();
			}catch(UnsupportedEncodingException e) {
				e.printStackTrace();
			}			

			request.getRequestDispatcher("/views/user/searchPwResult.jsp").forward(request, response);
			
		}else {
			//일치하지 않을 때
			request.setAttribute("tempPw", null);	
			request.getRequestDispatcher("/views/user/searchPwResult.jsp").forward(request, response);
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
