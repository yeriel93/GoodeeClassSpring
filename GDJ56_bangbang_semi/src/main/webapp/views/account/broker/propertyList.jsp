<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%-- <%@ include file="/views/common/mypageMain.jsp"%> --%>

<!-- css -->
<link href="<%=request.getContextPath() %>/css/account/broker/propertyList.css" type="text/css" rel="stylesheet">

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
                         <input type="button" class="button" value=" 수정 " onclick="fn_update()" style="cursor:pointe">
                     </td>
                     <td>
                         <input type="checkbox" name="">
                     </td>
                 </tr>
             </tbody>
         </table>
     </form>
     <script>
     	const fn_update=()=>{
     		window.open("<%=request.getContextPath()%>/account/broker/updateProperty.bb","_blank");
     	}
     </script> 
 </section>
</body>
</html>

<%@ include file="/views/common/footer.jsp" %>