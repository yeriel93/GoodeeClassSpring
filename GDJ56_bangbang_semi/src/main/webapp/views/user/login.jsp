<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link href="<%=request.getContextPath() %>/css/user/loginStyle.css" type="text/css" rel="stylesheet">

<section class="content">
<style>
#loginBtn2{border:none;}
input::placeholder {  
  font-size: 13px;
}
</style>

<body>
    <div id="divOuter">
        <div id="loginContainer">
        	<form id="loginForm" action="<%=request.getContextPath() %>/user/loginEnd.bb" method="post">
	            <h1>로그인</h1>
	            <hr>
	            <h3>아이디</h3>
	            <input type="text" name="userId" id="userId" placeholder="아이디를 입력해주세요">
	            <h3>비밀번호</h3>
	            <input type="password" name="userPw" id="userPw" placeholder="비밀번호를 입력해주세요">
	            <br>
	            <button id="loginBtn2">로그인</button>
	            <br>
	            <!-- <a href="javascript:kakaoLogin();"><img src="../images/kakao_login_medium_wide.png" alt="" ></a>  -->       
	        </form>    
        </div>      
        
        <div id="etcContainer">
        <h5><a href="<%=request.getContextPath() %>/user/enroll.bb">회원가입</a></h5>
        <h5><a href="<%=request.getContextPath() %>/user/searchId.bb">아이디 찾기</a></h5>
        <h5><a href="<%=request.getContextPath() %>/user/searchPw.bb">비밀번호 찾기</a></h5>
        </div>      
    </div>

	<!-- 카카오 로그인  -->
<!--     <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script>
        // 28988ab62325a7e38f8fbc58e32dcb66
        window.Kakao.init("28988ab62325a7e38f8fbc58e32dcb66");
        function kakaoLogin(){
            window.Kakao.Auth.login({
                scope:'profile_nickname,profile_image,account_email,age_range',
                success: function(authObj){
                    console.log(authObj);
                    window.Kakao.API.request({
                        url:'/v2/user/me',
                        success: res => {
                            const kakao_account=res.kakao_account;
                            console.log(kakao_account);
                        }
                    });
                }
            });
        } -->
    </script>
</section>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>