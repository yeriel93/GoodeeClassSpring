<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="MemoList"/>
</jsp:include>

 <br />
       <!-- 메모목록 -->
       <table class="table">
           <tr>
               <th scope="col">번호</th>
               <th scope="col">메모</th>
               <th scope="col">날짜</th>
               <th scope="col">삭제</th>
           </tr>
		내용출력
       </table>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>