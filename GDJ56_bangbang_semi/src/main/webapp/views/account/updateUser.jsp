<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/views/common/mypageMain.jsp"%>

<link href="<%=request.getContextPath() %>/css/account/updateUserStyle.css" type="text/css" rel="stylesheet">
<style>
#updateuser-Container>#updateuserForm>.input{
    width: 290px;
    height: 32px;
    font-size: 15px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(255, 255, 255);

}

#signupBtn{
    width:310px;
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
.btns{
    background-color: #075A2A;
    color:white;
    width:100px;
    height:30px;
    border-radius: 3px;
    border:none;

}
#userId,#userEmail{
  color:gray;
}


</style>
<div id="divOuter">
  <div id="updateuser-Container">
    <form id="updateuserForm" action="<%=request.getContextPath()%>/account/updateUserEnd.bb" method="post">

      <h1>내 정보 수정</h1>
      <hr>
      <h3>아이디</h3>
      <input type="text" class="input" name="userId" id="userId" value="<%=loginUser.getId()%>" readonly>
      <h3>비밀번호</h3>
      <input type="button" class="btns" value="비밀번호 변경하기" onclick="fn_updatePassword();" style="background-color: #075A2A;border-radius: 5px;width:300px;border:none;">

      <h3>이름</h3>
      <input type="text"class="input" name="userName" id="userName" value="<%=loginUser.getName()%>" placeholder="이름을 입력해주세요." >
      <h3>이메일</h3>
      <input type="text" class="input" name="userEmail" id="userEmail" value="<%=loginUser.getEmail()%>" readonly>
      
      <h3>휴대폰 번호</h3>
      <input type="text" class="input" name="userPhone" id="userPhone" value="<%=loginUser.getPhone()%>" placeholder="휴대폰 번호를 입력해주세요. (-포함)">
      <h3>생년월일</h3>
      <input type="text" class="input" name="userBirth" id="userBirth" value="<%=loginUser.getBirthday()%>" placeholder="예시) yyyy-mm-dd">
      <input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
      <br>
      <button id="signupBtn">정보수정</button>
      <br>
    </form>
    
  </div>          

</div>


<script>
  
  // const fn_updatePassword=()=>{
     <%-- open("<%=request.getContextPath()%>/member/updatePassword.do?userId=<%=loginMember!=null?loginMember.getUserId():""%>", --%>
  //       "_blank","width=400,height=210");
    
  // }


  //이메일 정규표현식
  $("#userEmail").blur(e=>{
      const userEmail=$("#userEmail").val();
      const emailChk=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[a-z]+\.[a-z]{2,3}/
      
      if(!emailChk.test(userEmail)){
        alert("⛔ 이메일을 정확히 입력해주세요 ⛔");
        $("#userEmail").val("");
        setTimeout(function(){ 
          $("#userEmail").focus();
        }, 10);
        
        
      }					
      
  })

  //생년월일 정규표현식
  $(()=>{
    $("#userBirth").blur(e=>{
      const birth=$("#userBirth").val();
      const birthChk=/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/
      if(!birthChk.test(birth)){
        alert("⛔ 생년월일을 yyyy-mm-dd 형식으로 입력해주세요 예시) 1995년 01월 02일 ⛔");
        $("#userBirth").val("");
        setTimeout(function(){
          $("#userBirth").focus();
        }, 10);
        
      }
    })
  })	

  //휴대폰 번호 정규표현식
  $("#userPhone").blur(e=>{
  const userPhone=$("#userPhone").val();
  const phoneChk=/^\d{3}-\d{3,4}-\d{4}$/

  if(!phoneChk.test(userPhone)){
      alert("⛔ 휴대폰번호를 정확히 입력해주세요 ⛔");
      $("#userPhone").val("");
      setTimeout(function(){ 
          $("#userPhone").focus();
      }, 10);
      
      
  }					

  }) 
</script>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>