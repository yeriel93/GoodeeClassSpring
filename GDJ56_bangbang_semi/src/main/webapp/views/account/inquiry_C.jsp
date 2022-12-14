<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>

<link href="<%=request.getContextPath() %>/css/account/inquiry_CStyle.css" type="text/css" rel="stylesheet">
<section class="content">
     <br>
     <br><br>

     <form action="">

         <table style="width: 1400px; margin: auto;">
             <thead>
             	<tr>
	                <th>문의번호</th>
	                <th>매물번호</th>
	                <th>방사진</th>
	                <th>전/월세</th>
	                <th>보증금</th>
	                <th>월세</th>
	                <th>문의 부동산명</th>
	                <th>부동산 연락처</th>
	                <th>문의일자</th>
	                <th>받은 답변</th>

            	</tr>
             </thead>
             <tbody>
                 <tr>
                     <td>123456</td>
                     <td><a href="">매물번호1234</a></td>
                     <td>방 사진</td>
                     <td>전세</td>
                     <td>4000</td>
                     <td>25</td>
                     <td>유림 부동산 </td>
                     <td>2022-10-16</td>
                     <td>2022-10-16</td>
                     <!-- <td id="replyState" style="color:green">
                        답변 대기
                     </td> -->
                     <td id="replyState">
                        낼 연락할게~
                     </td>

                 </tr>
                 <tr>
                    <td>123456</td>
                    <td><a href="">매물번호1234</a></td>
                    <td>방 사진</td>
                    <td>전세</td>
                    <td>4000</td>
                    <td>25</td>
                    <td>유림 부동산 </td>
                    <td>2022-10-16</td>
                    <td>2022-10-16</td>
                     <td id="replyState" style="color:green">
                        답변 대기
                     </td>
                     <!-- <td id="replyState">
                        답변 완료
                     </td> -->

                 </tr>

             </tbody>
         </table>
     </form> 
 </section>
</body>
</html>

<%-- <%@ include file="/views/common/footer.jsp" %> --%>
</section>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>