<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link href="<%=request.getContextPath() %>/css/user/searchPwStyle.css" type="text/css" rel="stylesheet">
<style>
#searchContainer>#searchPw>input{
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
#searchPwBtn{
    width:260px;
    height:40px;
    /* margin-left: 40px; */
    margin-top: 25px;
    margin-bottom: 10px;
    background-color: #075A2A;
    color: white;
    border-color: #075a2a8c;
    border-radius: 5px;
    border:none;
}

input::placeholder {  
  font-size: 13px;
}

</style>
<section class="content">
    <div id="divOuter">
        <div id="searchContainer">
            <form id="searchPw" action="<%=request.getContextPath() %>/user/tempPw.bb" method="post">
                
                <h1>비밀번호 찾기</h1>
                <hr>
                <h3>아이디</h3>
                <input type="text" name="userId" id="userId" placeholder=" (필수) 아이디를 입력해주세요">
                <h3>이메일</h3>
                <input type="text" name="userEmail" id="userEmail" placeholder=" (필수) 이메일 주소를 입력해주세요">
                <h3>휴대폰 번호</h3>
                <input type="text" name="userPhone" id="userPhone" placeholder=" (필수) 휴대폰 번호를 입력해주세요">
                <br>            
                <div id="searchTip">
                    <p>회원정보에 등록된 정보와 <br>일치해야 <strong>비밀번호</strong>를 찾을 수 있습니다.</p>
                </div>   
                <button id="searchPwBtn">비밀번호 찾기</button>
                <br>
                                
            </form>
        </div>            
        

    </div>
</section>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>