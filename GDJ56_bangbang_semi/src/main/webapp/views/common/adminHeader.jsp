<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- adminHeader.css -->
<link href="<%=request.getContextPath()%>/css/common/adminHeaderStyle.css" type="text/css" rel="stylesheet">
<!-- jQuery -->
	<script src = "<%=request.getContextPath()%>/js/jquery-3.6.1.min.js"></script>
<title>방방 Admin</title>
</head>
<body>
	<header>
		<div id="logoContainer"><img src="<%=request.getContextPath()%>/images/logo.png" style="width:130px;height:80px;"><br>관리자 페이지</div>
		<div><p><a href="<%=request.getContextPath()%>/admin/user.bb">회원관리</a></p></div>
		<div><p><a href="<%=request.getContextPath()%>/admin/broker.bb">중개사관리</a></p></div>
		<div><p><a href="<%=request.getContextPath()%>/admin/property.bb">매물관리</a></p></div>
		<div><p><a href="<%=request.getContextPath()%>/admin/report.bb">신고관리</a></p></div>
	</header>
	 <script>
    	$("div#logoContainer>img").click(e=>{
    		location.assign("<%=request.getContextPath()%>/");
    	})
    </script>
	<hr>