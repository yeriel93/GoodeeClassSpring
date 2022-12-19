<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>
<%@ page import="java.util.List,com.account.model.vo.AlertListC" %>
<%
	List<AlertListC> userAlertC=(List<AlertListC>)request.getAttribute("userAlertC");
%>

<link href="<%=request.getContextPath() %>/css/account/inquiry_CStyle.css" type="text/css" rel="stylesheet">
<style>
	#pageBar{
	    text-align: center;
	    margin-top: 15px;
	    margin-bottom: 20px;
	}
	
	#propertyImg{
	    width:70px;
	    height: 50px;
	    padding:0px;
	    margin: 0px;
	}
</style>

<section class="content">
     <br>
     <br><br>    

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
	             <%if(userAlertC.isEmpty()){ %>
	             	<tr>
	             		<td colspan="10">조회된 결과가 없습니다.</td>
	             	</tr>
	             <%} else{
	             	for(int i=0;i<userAlertC.size();i++){%>               
                 <tr>
                     <td><%=userAlertC.get(i).getAlertNo() %></td>
                     <td><a href="<%=request.getContextPath() %>/property/propertyInfo.bb?propertyNo=<%=userAlertC.get(i).getPropertyNo() %>"><%=userAlertC.get(i).getPropertyNo() %></a></td>
                     <td><img id="propertyImg" src="<%=request.getContextPath() %>/upload/property/<%=userAlertC.get(i).getRenamedFilename() %>"></td>
                     <td><%=userAlertC.get(i).getRenttype() %></td>
                     <td><%=userAlertC.get(i).getDeposit() %></td>
                     <td><%=userAlertC.get(i).getMonthlycharge() %></td>
                     <td><%=userAlertC.get(i).getOfficename() %></td>
                     <td><%=userAlertC.get(i).getTelephone() %></td>
                     <td><%=userAlertC.get(i).getAlertDate() %></td>
                     <%if(userAlertC.get(i).getFeedback_content()==null){ %>
	                     <td id="replyState">답변 대기</td>
                     <%} else{%>
	                     <td id="replyState"><%=userAlertC.get(i).getFeedback_content() %></td>
                     <%} %>
	
                 </tr>
              <%}
              }%>                 
             </tbody>
         </table>
         <div id="pageBar">
         	<%=request.getAttribute("pageBar") %>
         </div>

 </section>



<%-- <%@ include file="/views/common/footer.jsp"%> --%>