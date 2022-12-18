<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href="<%=request.getContextPath() %>/css/account/feedbackStyle.css" type="text/css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src = "<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<title>답장하기</title>
</head>
<style>
body{
	background-color: #d9dfd4;
}
input{
    width: 150px;
    height: 28px;
    font-size: 15px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    margin-top: 5px;   
    margin-left: 10px; 
    background-color: rgb(255, 255, 255);
}
#feedbackContainer{
    padding: 10px;
}
#logoContainer{
    padding-top: 7px;
    padding-left: 9px;
}
#feedbackBtn{
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
#outer{
    display: flex;
}
#feedbackContainer{
    justify-content: center;
}
.feedbackContent{
    width:100%;
    height:45px;
    border-radius: 5px;
    margin-top: 13px;
}
td.con{
  text-align: center;
}

</style>

<body>        
    
    <div id="logoContainer">
        <img src="<%=request.getContextPath() %>/images/logo.png" alt="" width="105px" height="75px">
    </div>
    <div id="outer">
        <div id="feedbackContainer">
            <form id="feedbackForm" action="<%=request.getContextPath() %>/account/feedbackEnd.bb" method="post" >                
                <hr>
                <h3>답장하기</h3>
                <hr>
                <table>
                    <tr>
                        <td>문의 번호</td>
                        <td class="con"><input type="text" name="alertNo" value="<%=request.getAttribute("alertNo") %>" readonly></td>
                    </tr>
                    <tr>
                        <td>문의 매물번호</td>
                        <td class="con"><input type="text" name="propertyNo" value="<%=request.getAttribute("propertyNo") %>" readonly></td>
                    </tr>
                    <tr>
                        <td>문의 고객명</td>
                        <td class="con"><input type="text" name="userCName" value="<%=request.getAttribute("userCName") %>" readonly></td>
                    </tr>
                </table>
                <select class="feedbackContent" name="feedbackContent">
                    <option value="곧 연락드리겠습니다." selected="selected">곧 연락드리겠습니다. </option>
                    <option value="편한 시간에 연락부탁드립니다.">편한 시간에 연락부탁드립니다.</option>
                    <option value="연락처로 전화드리겠습니다.">연락처로 전화드리겠습니다.</option>
                </select>
                <input type="hidden" name="userCNo" value="<%=request.getAttribute("userCNo")%>">              
                <input type="hidden" name="userBNo" value="<%=request.getAttribute("userBNo")%>">              
                <div id="buttonContainer">
                    <button id="feedbackBtn">답장하기</button>
                </div>
            </form>
        </div>
    </div>

    <script>

    </script>
</body>
</html>