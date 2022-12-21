<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link href="<%=request.getContextPath() %>/css/user/searchIdStyle.css" type="text/css" rel="stylesheet">

<%-- <%
	String searchIdResult=(String)request.getAttribute("searchIdResult");
%> --%>

<style>
#loginBtn2{
    width:260px;
    height:40px;
    /* margin-left: 40px; */
    margin-top: 10px;
    margin-bottom: 10px;
    background-color: #075A2A;
    color: white;
    border-color: #075a2a8c;
    border-radius: 5px;
    border:none;
    
}

</style>

<section class="content">
	<div id="divOuter">
        <div id="searchContainer">
        	<form id="searchId" action="<%=request.getContextPath()%>/user/searchIdEnd.bb" method="post">
                <h1>아이디 찾기</h1>
                <hr>
         		<%if(request.getAttribute("searchIdResult")==null){ %>
	                <div id="searchTip">
	                    <p>
	                    <strong>아이디 찾기 실패!</strong><br><br>
	                    입력한 정보와 일치하는 회원이 없습니다.
	                    </p>
	                </div>   
<%-- 	                <button id="loginBtn2" onclick="location.replace('<%=request.getContextPath()%>/user/searchId.bb')">아이디 찾기</button>
	                <br>
	                <button id="loginBtn2" onclick="location.replace('<%=request.getContextPath()%>/user/searchPw.bb')">비밀번호 찾기</button> --%>

        		<%}else{%> 
	                <div id="searchTip">
	                    <p>
	                    <h4>회원님의 아이디는</h4>
	                    <br>
	                    <%-- <h3><%=searchIdResult %></h3> --%>
	                    <h3><%=request.getAttribute("searchIdResult") %></h3>
	                    </p>
	                </div>  
	        		<h3></h3>
<%-- 	        		<button id="loginBtn2" onclick="location.replace('<%=request.getContextPath()%>/user/login.bb')">로그인</button>
	        		<br>
	        		<button id="loginBtn2" onclick="location.replace('<%=request.getContextPath()%>/user/searchPw.bb')">비밀번호 찾기</button> --%>
                
 				<%} %> 

                
                <br>
        	</form>

        </div>             

    </div>
</section>
<%@ include file="/views/common/footer.jsp"%>