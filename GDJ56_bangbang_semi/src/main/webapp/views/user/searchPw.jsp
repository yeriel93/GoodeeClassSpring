<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link href="<%=request.getContextPath() %>/css/user/searchPwStyle.css" type="text/css" rel="stylesheet">

<section class="content">
    <div id="divOuter">
        <div id="searchContainer">
            <h1>비밀번호 찾기</h1>
            <hr>
            <h3>아이디</h3>
            <input type="text" id="userId" placeholder=" (필수) 아이디를 입력해주세요">
            <h3>이메일</h3>
            <input type="text" id="userEmail" placeholder=" (필수) 이메일 주소를 입력해주세요">
            <h3>휴대폰 번호</h3>
            <input type="text" id="userPhone" placeholder=" (필수) 휴대폰 번호를 입력해주세요">
            <br>            
            <div id="searchTip">
                <p>회원정보에 등록된 정보와 일치해야 <strong>비밀번호</strong>를 찾을 수 있습니다.</p>
            </div>   
            <button id="searchPwBtn">비밀번호 찾기</button>
            <br>

        </div>            
        

    </div>
</section>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>