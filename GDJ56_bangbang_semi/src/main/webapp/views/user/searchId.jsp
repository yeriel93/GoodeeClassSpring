<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link href="<%=request.getContextPath() %>/css/user/searchIdStyle.css" type="text/css" rel="stylesheet">
<style>
    #searchContainer>#searchId>input{
    width: 245px;
    height: 32px;
    font-size: 15px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(255, 255, 255);    
}

.content{
	margin-top: 35px;
}
</style>

<section class="content">
	<div id="divOuter">
        <div id="searchContainer">
        	<form id="searchId" action="<%=request.getContextPath()%>/user/searchIdEnd.bb" method="post">
                <h1>아이디 찾기</h1>
                <hr>
                <h3>이름</h3>
                <input type="text" name="userName" id="userName" placeholder=" (필수) 이름을 입력해주세요">
                <h3>이메일</h3>
                <input type="text" name="userEmail" id="userEmail" placeholder=" (필수) 이메일 주소를 입력해주세요">
                <h3>휴대폰 번호</h3>
                <input type="text" name="userPhone" id="userPhone" placeholder=" (필수) 예시: 010-0000-0000">
                <br>            
                <div id="searchTip">
                    <p>회원정보에 등록된 정보와 일치해야 <strong>아이디</strong>를 찾을 수 있습니다.</p>
                </div>   
                <button id="searchIdBtn">아이디 찾기</button>
                <br>
        	</form>

        </div>             

    </div>
</section>
<script>
    //이메일 정규표현식
    $("#userEmail").blur(e=>{
            const userEmail=$("#userEmail").val();
            const emailChk=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[a-z]+\.[a-z]{2,3}/
            
            if(!emailChk.test(userEmail)){
                alert("⛔ 이메일을 정확히 입력해주세요 ⛔");
                $("#userEmail").val("");
                setTimeout(function(){ 
                    $("#userEmail").focus();
                }, 10)
                
                return false;
            }					
            
    })   

    //휴대폰 번호 정규표현식
    $("#userPhone").blur(e=>{
            const userPhone=$("#userPhone").val();
            const phoneChk=/^\d{3}-\d{3,4}-\d{4}$/
            
            if(!phoneChk.test(userPhone)){
                alert("⛔ 휴대폰번호를 정확히 입력해주세요 ⛔");
                // $("#userPhone").val("");
                setTimeout(function(){ 
                    $("#userPhone").focus();
                }, 10)
                
                return false;
            }					
            
    })   
    
    
</script>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>