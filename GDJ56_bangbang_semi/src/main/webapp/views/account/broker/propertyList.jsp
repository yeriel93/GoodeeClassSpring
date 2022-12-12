<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<!-- css -->
<link href="<%=request.getContextPath() %>/css/account/broker/propertyList.css" type="text/css" rel="stylesheet">

 <section class="content">
    <div class="mypageTitle">
    	<h1 id="titleText">MY 방방</h1>
    </div>
    <div class="profile">
        <div class="profile2">
            <h5 id="userName">중개사 님</h5> 
            <!-- 이부분 로그인 한 이름나오게 나중에 수정 -->
            <h5 id="userLevel">중개사회원</h5>
        </div>
    </div>
    <div class="mypageMenu">
        <a href="<%=request.getContextPath()%>/account/updateBrokerMain.bb">내 정보 수정</a>
        <a href="">알림 목록</a>
        <a href="">내놓은 방 목록</a>
        <a href="<%=request.getContextPath()%>/account/withdrawal.bb">회원탈퇴</a>
    </div>

     <br>
     <!-- 버튼 -->
     <div id="buttonDiv" style="margin-right: 10px">
         <input type="button" value="전체선택" class="button" onclick="">
         <input type="button" value=" 삭제 " class="button" onclick="">
     </div>
     <br><br>
     <!-- 매물 목록 -->
     <form action="">
         <table>
             <thead>
             	<tr>
	                <th>매물번호</th>
	                <th>사진</th>
	                <th>주소</th>
	                <th>구조</th>
	                <th>금액(만원)</th>
	                <th>관리비</th>
	                <th>입주가능일</th>
	                <th>매물등록일</th>
	                <th>수정</th>
	                <th><input type="checkbox" checked></th>
            	</tr>
             </thead>
             <tbody>
                 <tr>
                     <td>123456</td>
                     <td></td>
                     <td>관악로 6길10-2</td>
                     <td>원룸(오픈형)</td>
                     <td>4000/25</td>
                     <td>7만/월</td>
                     <td>협의입주</td>
                     <td>2022-10-16</td>
                     <td>
                         <input type="button" class="button" value=" 수정 " onclick="">
                     </td>
                     <td>
                         <input type="checkbox" name="">
                     </td>
                 </tr>
                 <tr>
                     <td>123456</td>
                     <td></td>
                     <td>관악로 6길10-2</td>
                     <td>원룸(오픈형)</td>
                     <td>4000/25</td>
                     <td>7만/월</td>
                     <td>협의입주</td>
                     <td>2022-10-16</td>
                     <td>
                         <input type="button" class="button" value=" 수정 " onclick="">
                     </td>
                     <td>
                         <input type="checkbox" name="">
                     </td>
                 </tr>
                 <tr>
                     <td>123456</td>
                     <td></td>
                     <td>관악로 6길10-2</td>
                     <td>원룸(오픈형)</td>
                     <td>4000/25</td>
                     <td>7만/월</td>
                     <td>협의입주</td>
                     <td>2022-10-16</td>
                     <td>
                         <input type="button" class="button" value=" 수정 " onclick="">
                     </td>
                     <td>
                         <input type="checkbox" name="">
                     </td>
                 </tr>
             </tbody>
         </table>
     </form> 
 </section>
</body>
</html>

<%-- <%@ include file="/views/common/footer.jsp" %> --%>