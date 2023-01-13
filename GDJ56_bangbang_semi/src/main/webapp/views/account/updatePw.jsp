<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.user.model.vo.User,com.broker.model.vo.Broker" %>
<%
	User loginUser = (User)session.getAttribute("loginUser");
	Broker loginBroker = (Broker)session.getAttribute("loginBroker");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<title>비밀번호 변경</title>
</head>
<style>
body{
	background-color: #d9dfd4;
}
input{
    width: 250px;
    height: 32px;
    font-size: 15px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(255, 255, 255);
}
#updatePwContainer{
    padding: 10px;
}
#logoContainer{
    padding-top: 7px;
    padding-left: 9px;
}
#updatePwBtn{
    width:260px;
    height:40px;
    /* margin-left: 40px; */
    margin-top: 30px;
    margin-bottom: 10px;
    background-color: #075A2A;
    color: white;
    border-color: #075a2a8c;
    border-radius: 5px;
    border:none;
    font-size: 15px;
}
input::placeholder {  
  font-size: 13px;
}

</style>

<body>
        <div id="logoContainer">
            <img src="<%=request.getContextPath() %>/images/logo.png" alt="" width="105px" height="75px">
        </div>
        <div id="updatePwContainer">
            <form id="updatePwForm" action="<%=request.getContextPath() %>/account/updatePasswordEnd.bb" method="post" onsubmit="return fn_pwCheck();">                
                <hr>
                <h3>비밀번호 변경</h3>
                <hr>
                <input type="hidden" name="userId" id="userId" value="<%=loginUser.getId()%>">
                <h4>현재 비밀번호</h4>
                <input type="password" name="beforePw" id="beforePw" placeholder="현재 비밀번호를 입력해주세요.">
                <h4>변경할 비밀번호</h4>
                <input type="password" name="afterPw" id="afterPw" placeholder="변경할 비밀번호를 입력해주세요.">
                <h4>변경할 비밀번호 확인</h4>
                <input type="password" name="afterPwChk" id="afterPwChk" placeholder="변경할 비밀번호를 다시 입력해주세요.">
                <button id="updatePwBtn">변경하기</button>
            </form>
        </div>
        <script>
            const fn_pwCheck=()=>{
                const afterPw=$("#afterPw").val();
                const afterPwChk=$("#afterPwChk").val()
                if(afterPw!=afterPwChk){
                    alert("비밀번호가 일치하지 않습니다.")
                    return false;
                }
            }

            //비번 정규표현식
			$(()=>{
				$("#afterPw").blur(e=>{
					const afterPw=$("#afterPw").val();
					const pwChk=/^[a-zA-Z0-9]+$/ //정규표현식

					//비밀번호 정규표현식
					if(!pwChk.test(afterPw)||afterPw.trim().length<8){
						alert("⛔ 비밀번호는 8자 이상, 영문자/숫자로만 구성할 수 있습니다.⛔");
						$("#afterPw").val('');

						
					}					
				})
			})

        </script>
</body>
</html>