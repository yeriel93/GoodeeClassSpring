<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>
<link href="<%=request.getContextPath() %>/css/account/pwCheckforUpdateStyle.css" type="text/css" rel="stylesheet">
 
<section class="content">
<style>
#loginBtn2{border:none;}
input::placeholder {  
  font-size: 13px;
}
#divOuter{
    /* border: 1px solid red; */
    align-content: center;

}
#pwCheckContainer{
    /* border: 1px solid #075A2A; */
    width: fit-content;
    height: fit-content;
    margin-left: 40%;
    /* background-color: rgba(255, 255, 255, 0.792); */
}
#pwCheckContainer>#pwCheckForm>hr{
    width:260px;
    margin-left: 0px;
}
#pwCheckContainer>#pwCheckForm>h1{
    padding-top: 30px;
    margin-bottom: 10px;
}
#pwCheckContainer>#pwCheckForm>input{
    width:250px;
    height:30px;
}
#pwCheckContainer>#pwCheckForm>a>img{
    width:260px;
    margin-top: 0px;
}
#etcContainer{
    /* border: 1px solid #075A2A; */
    margin-left: 40%;
    margin-right: 20%;

    display:flex;
}

#pwCheckBtn2{
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
    
}


#pwCheckContainer>#pwCheckForm>input{
  width: 250px;
  height: 32px;
  font-size: 15px;
  border: 0;
  border-radius: 15px;
  outline: none;
  padding-left: 10px;
  background-color: rgb(255, 255, 255);
}

input::placeholder {  
  font-size: 13px;
}

</style>

<body>
    <div id="divOuter">
        <div id="pwCheckContainer">
        	<form id="pwCheckForm" action="<%=request.getContextPath() %>/account/pwCheckEnd.bb" method="post">
	            <h1>내 정보 수정</h1>
	            <hr>	            
	            <h3>비밀번호</h3>
	            <input type="password" name="userPw" id="userPw" placeholder="비밀번호를 입력해주세요">
	            <input type="hidden" name="userId" id="userId" value="<%=loginUser.getId()%>" readonly>
	            <br>
	            <button id="pwCheckBtn2">비밀번호 확인</button>
	            <br>
      
	        </form>    
        </div>              
  
    </div>
	
</section>
<%@ include file="/views/common/footer.jsp"%>