<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="/views/common/mypageMain.jsp"%>

<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<link href="<%=request.getContextPath() %>/css/account/updateUserStyle.css" type="text/css" rel="stylesheet">

  <div id="divOuter">
        <div id="updateuser-Container">
            <h1>내 정보 수정</h1>
            <hr>
            <h3>아이디</h3>
            <input type="text" id="userId" readonly>
            <h3>비밀번호</h3>
            <input type="password" id="userPw" required>
            <button class="btns">비밀번호 변경</button>
            <h3>비밀번호 확인</h3>
            <input type="password" id="userPw" required>
            <h3>이름</h3>
            <input type="text" id="userName">
            <h3>이메일</h3>
            <input type="text" id="userEmail" readonly>

            <h3>휴대폰 번호</h3>
            <input type="text" id="userPhone">
            <h3>생년월일</h3>
            <input type="text" id="userPhone">

            <br>
            <button id="signupBtn">정보수정</button>
            <br>

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