<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="/views/common/mypageMain.jsp"%>

<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<link href="<%=request.getContextPath() %>/css/account/updateUserStyle.css" type="text/css" rel="stylesheet">
<style>
#updateuser-Container>#updateuserForm>input{
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
}
.btns{
    background-color: #075A2A;
    color:white;
    width:100px;
    height:30px;
    border-radius: 3px;
    border:none;

}

</style>
<div id="divOuter">
  <div id="updateuser-Container">
    <form id="updateuserForm" action="<%=request.getContextPath()%>/account/updateUserEnd.bb" method="post">

      <h1>내 정보 수정</h1>
      <hr>
      <h3>아이디</h3>
      <input type="text" name="userId" id="userId" value="<%=loginUser.getId()%>" readonly>
      <h3>비밀번호</h3>
      <input type="password" name="userPw" id="userPw" required>
      <button class="btns">비밀번호 변경</button>

      <h3>이름</h3>
      <input type="text" name="userName" id="userName" >
      <h3>이메일</h3>
      <input type="text" name="userEmail" id="userEmail" value="<%=loginUser.getEmail()%>" readonly>
      
      <h3>휴대폰 번호</h3>
      <input type="text" name="userPhone" id="userPhone">
      <h3>생년월일</h3>
      <input type="text" name="userBirth" id="userBirth">
      
      <br>
      <button id="signupBtn">정보수정</button>
      <br>
    </form>
    
  </div>          

</div>

<script>
	var tabs = $('.tabs');
	var selector = $('.tabs').find('a').length;
	//var selector = $(".tabs").find(".selector");
	var activeItem = tabs.find('.active');
	var activeWidth = activeItem.innerWidth();
	$(".selector").css({
	  "left": activeItem.position.left + "px", 
	  "width": activeWidth + "px"
	});
	
	$(".tabs").on("click","a",function(e){
	  e.preventDefault();
	  $('.tabs a').removeClass("active");
	  $(this).addClass('active');
	  var activeWidth = $(this).innerWidth();
	  var itemPos = $(this).position();
	  $(".selector").css({
	    "left":itemPos.left + "px", 
	    "width": activeWidth + "px"
	  });
	});
</script>
</body>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>