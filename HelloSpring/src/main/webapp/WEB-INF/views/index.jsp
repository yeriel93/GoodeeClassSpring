<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!-- 
	해더파일불러오기
	title값을 전달해서 출력해야함 -> MainPage가 출력
 -->
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="MainPage"/>
</jsp:include>


	<section>
		<h2>Hello Spring</h2>
		<div style="display: flex; justify-content: center;">
			<img src="${path }/resources/images/tea.png" width="800" height="500" id="center-image" alt="스프링로고">
		</div>
		
		<button class="btn btn-outline-dark my-2 my-sm-0" onclick="fn_memberAjax();">회원조회</button>
		<button class="btn btn-outline-dark my-2 my-sm-0" onclick="jsonTest();">회원가입</button> 
		<div id="membercontainer"></div>
		
	</section>

	<script>
		function jsonTest(){
			//비동기통신을 할때 js기본제공하는 함수 이용하기.
			//fetch()함수를 제공함, 동기식 통신을 위해서 promise객체를 반환을 함. 
			//fetch(url주소, 전송에 대한 옵션).then(콜백함수 : 예) response=>response.json()).then(data=>{로직});
			fetch("${path}/member/ajax/insert",{
				method:"post",
				headers:{
					"Content-Type":"application/json"	
				}, //요청헤더 설정
				body:JSON.stringify({userId:"test22",password:"1234",userName:"test22"}) //전송할 데이터
			})
			.then(response=>{console.log(response); return response.json(); })
			.then(data=>{ //success callback함수와 동일한 기능
				console.log(data);
			});
		}
		const fn_memberAjax=()=>{
			$.get("${path}/member/memberList.do",data=>{
				console.log(data);
				const table=$("<table>");
				const header=$("<tr>");
				header.append("<th>아이디</th>")
					.append("<th>이름</th>")
					.append("<th>나이</th>")
					.append("<th>성별</th>")
					.append("<th>전화번호</th>")
					.append("<th>이메일</th>")
					.append("<th>주소</th>")
					.append("<th>취미</th>")
					.append("<th>가입일</th>");
				table.append(header);
				data.forEach(e=>{
					let tr=$("<tr>");
					let userId=$("<td>").text(e.userId);
					let name=$("<td>").text(e.userName);
					let age=$("<td>").text(e.age);
					let gender=$("<td>").text(e.gender);
					let phone=$("<td>").text(e.phone);
					let email=$("<td>").text(e.email);
					let address=$("<td>").text(e.address);
					let hobby=$("<td>").text(e.hobby.toString());
					let enrolldate=$("<td>").text(new Date(e.enrollDate));
					tr.append(userId).append(name).append(age).append(gender)
					.append(phone).append(email).append(address).append(hobby)
					.append(enrolldate);
					table.append(tr);
				});
				$("#membercontainer").html(table);
			});
		}
	</script>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>