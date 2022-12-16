<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.centercoordinate.model.vo.CenterCoordinate" %>

<%@ page import ="com.user.model.vo.User" %>
<%
	User loginUser = (User)session.getAttribute("loginUser");
%>  
<%
	List<CenterCoordinate> list = (List<CenterCoordinate>)request.getAttribute("centerCoordinate");
	//BD 브랜치 테스트
	//YR 브랜치 테스트
%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방방(방 구해줘~방) </title>
<script src = "<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/first/firstpage.css">
</head>
<body>
	<div id="container">
        <div id="leftContainer" style="background-image:url('<%=request.getContextPath()%>/images/firstimage.jpg');">
            <div id= logo>
                <img src="<%=request.getContextPath()%>/images/logo.png" alt="" width="140px" height="100px">
            </div>
        </div>
        <div id="rightContainer">
        
	        <%if(loginUser == null){ %>
		        <div id="buttonContainer">
		            <button onclick='location.replace("<%=request.getContextPath()%>/user/login.bb")'><p>로그인 | 회원가입</p></button>
		        </div>
	        <%} else { %>
	        	<div id="buttonContainer">
	            	<button onclick='location.replace("<%=request.getContextPath()%>/user/logout.bb")'><p>로그아웃</p></button>
	        	</div>
	        <%} %>
	        
            <div id="searchContainer">
                <div>어디를 찾고 계신가요?</div>
                <form action="<%=request.getContextPath()%>/searchAddress.bb" method="post" onsubmit="return fn_addressCheck();" >
                	<div id="addressContainer">
	                	<div>
	                		<select name="gu" onchange="getGu();">
	                			<option value="-">구 선택</option>
	                		</select>
	                	</div>
	                	<div>
	                		<select name="dong">
	                			<%-- <%if(list!=null){ %>
	                				<%for(CenterCoordinate cc : list){ %>
	                					<option value = "<%=cc.getDong()%>"><%=cc.getDong()%></option>
	                				<%} %>
	                			<%} else {%> --%>
	                				<option value="-">동 선택</option>
	                			<%-- <%} %> --%>
	                		</select>
	                	</div>
	                	<input type="submit" value ="검색">
					</div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
<style>
	div#interFooter{
		height:0px !important;
	}
</style>
<%@ include file ="/views/common/footer.jsp" %>
<script>

	const gu = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구", "용산구","은평구", "종로구","중구","중랑구"];
	$(()=>{	
		<%if(list!=null){%>
			$("[name=gu]").html("");
			drawGu();
			$("[name=gu]").val("<%=list.get(0).getGu()%>")
		<%} else {%>
			drawGu();
		<%}%>
	})
	const getGu = () =>{
		let gu = $("[name=gu]").first().val();
		<%-- location.replace('<%=request.getContextPath()%>/changedong.do?gu='+gu); --%>
		
		/* ajax 추가 */
		$.ajax({
			url:"<%=request.getContextPath()%>/changedong.bb",
			type:"get",
			data:{gu:gu},
			success:data=>{
/* 				console.log(data); */	
				$("select[name=dong]").html("");
				data.forEach(v=>{
					const option = $("<option>").val(v.dong).text(v.dong);
					$("select[name=dong]").append(option);
				})
			}
		})
		
		
	}
	const drawGu = () => {
		for(let i=0;i<gu.length;i++){
			const guOption = $("<option>").val(gu[i]).text(gu[i]);
			$("[name=gu]").append(guOption);
		}	
	}
	const fn_addressCheck = () => {
		let gu = $("[name=gu]").val();
		let dong = $("[name=dong]").val();
		if(gu == "-" || dong == "-"){
			alert("동과 구를 정확히 선택해주세요");
			return false;
		}
	}

</script>