<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link href="<%=request.getContextPath() %>/css/common/mypageMainStyle.css" type="text/css" rel="stylesheet">


<%if(loginUser!=null&&loginUser.getUserLevel()=='B'){ %>
	<section class="mypage_B">
	    <div class="mypageTitle">
	        <h1 id="titleText">MY 방방</h1>
	    </div>
	    <div class="profile">
	        <div class="profile2">
	            <h5 id="userName"><%=loginUser.getName() %> 님</h5> 
	            <h5 id="userLevel">중개사회원</h5>
	        </div>
	    </div>
	    <div class="mypageMenu" style="margin:auto;">
	
	            <a href="<%=request.getContextPath()%>/account/updateBrokerMain.bb">내 정보 수정</a>
	            <a href="<%=request.getContextPath()%>/account/inquiryBroker.bb">문의 목록</a>
	            <a href="">내놓은 방 목록</a>
	            <a href="<%=request.getContextPath()%>/account/withdrawal.bb">회원탈퇴</a>
	
	    </div>
	</section>
<%} %>
<% if(loginUser!=null&&loginUser.getUserLevel()=='C'){ %>
	<section class="mypage_C">
	    <div class="mypageTitle">
	        <h1 id="titleText">MY 방방</h1>
	    </div>
	    <div class="profile">
	        <div class="profile2">
	            <h5 id="userName"><%=loginUser.getName() %> 님</h5> 
	            <h5 id="userLevel">일반회원</h5>
	        </div>
	    </div>
	    <div class="mypageMenu" style="margin:auto;">
	
	            <a href="<%=request.getContextPath()%>/account/updateUser.bb">내 정보 수정</a>
	            <a href="<%=request.getContextPath()%>/account/inquiry.bb">문의 목록</a>
	            <a href="<%=request.getContextPath()%>/account/withdrawal.bb">회원탈퇴</a>
	
	    </div>
	</section>
<%} %>

<%-- <%@ include file="/views/common/footer.jsp"%> --%>