<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>허위 매물 신고</title>
<link href="<%=request.getContextPath() %>/css/property/fakeReportStyle.css" type="text/css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap" rel="stylesheet">
<!-- jQuery -->
	<script src = "<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
</head>
<body>
    <div>
        <div class=fakeReportContainer>
            <h1>허위매물신고</h1>
            <p>
                <hr>
                허위매물로 피해를 입으신 경우, 신고를 하실 수 있습니다.<br>
                허위매물 신고시, 아래 항목을 정확히 기재해주시고,<br>
                신고하신 내용은 확인 후 조치하겠습니다.<br>
                허위매물임이 확인된 경우에는 중개업소에 패널티가 부여되며,<br>
                신고내용이 정확하지 않거나, 허위신고일 경우 서비스 이용에 제한이 있으니 유의해주세요.
            </p>
            <hr>
            <label>
                <textarea name="content" placeholder="200자 이내로 작성해주세요"></textarea><br>
            </label>
            <hr>
            <input type="checkbox" name="agree">이와 같은 내용으로 신고합니다
            <button id="submitBtn">확인</button>
        </div>
    </div>
    <script>
       $("button#submitBtn").click(e=>{
    	  if($("textarea[name=content]").val()!="" && $("input[name=agree]").prop("checked") == true){
    		  $.ajax({
    			  url:"<%=request.getContextPath()%>/property/propertyInfo/fakeReportEnd.bb",
    			  type:"get",
    			  data:{
    				  propertyNo:"<%=request.getParameter("propertyNo")%>",
    				  userNo:"<%=request.getParameter("userNo")%>",
    				  content:$("textarea[name=content]").val()
    			  },
    			  success:data=>{
    				  if(data>0){
    					  alert("신고가 정상적으로 등록되었습니다.");
    				  } else {
    					  alert("신고실패. 이미 신고한 매물입니다.");
    				  }
    				  close();
    			  }
    		  });
    	  } else if($("textarea[name=content]").val()==""){
    		  alert("신고 사유를 입력해주세요.");
    	  } else if($("input[name=agree]").prop("checked") == false){
    		  alert("신고합니다에 체크해주세요.");
    	  }
    	   
    	   
       });
    </script>
</body>
</html>