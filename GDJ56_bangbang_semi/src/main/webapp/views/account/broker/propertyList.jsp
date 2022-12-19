<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, 
				com.property.model.vo.Property"%>
<%
	List<Property> propertys = (List<Property>)request.getAttribute("propertys");
	/* System.out.println(propertys); */
%>
 
<%@ include file="/views/common/mypageMain.jsp"%>

<!-- css -->
<link href="<%=request.getContextPath() %>/css/account/broker/propertyList.css" type="text/css" rel="stylesheet">

	<br>
    <%if(propertys!=null && propertys.size()>0){ %>
    	
    <!-- 버튼 -->
    <div style="display: flex; justify-content: center;">
        <div id="buttonDiv" style="margin-right: 10px">
            <input type="button" value="전체선택" class="button" onclick="">
            <input type="button" value=" 삭제 " class="button" onclick="">
        </div>
    </div>
    
    <section style="display:flex; justify-content:center;">
    <!-- 매물목록 -->
        <div id="container">
   		<%for(Property p : propertys){%>
            <div class="propertyWrap">
                <input type="checkbox">
                <input type="button" value="수정" class="updateBtn" onclick="fn_updatePage(event)">

                <div class="imgDiv">
                    <img src="<%=request.getContextPath() %>/upload/property/<%=p.getThumbnail() %>" alt="">
                </div>
                
                <div class="infoDiv">
                    <div>
                        <span>매물번호</span>
                        <span><%=p.getPropertyNo() %></span>
                    </div>
                    <div class="price">
                        <span><%=p.getRenttype() %></span>
                        <span><%=p.getDeposit() %></span>
                        <%if(p.getRenttype().equals("월세")) {%>
                        	<span>/</span>
                        	<span><%=p.getMonthlyCharge() %></span>
                        <%} %>
                    </div>
                    <div>
                        <span>관리비</span>
                        <%if(p.getManagementCharge()==0) {%>
							<span>없음</span>
                        <%}else{%>
	                        <span><%=p.getManagementCharge()%></span>
	                        <span>만</span>
                        <%} %>
                    </div>
                    <div>
                        <span>주소</span>
                        <span><%=p.getAddress() %></span>
                    </div>
                    <div>
                        <span><%=p.getPropertyStructure() %></span>
                    </div>
                </div>
            </div>
 		<%}%>
        </div>
	 </section>
     
     <%}else {%>
     	<div id="noDiv">
     		<h1>등록된 매물이 없습니다.</h1>
     	</div>
     <%} %> 
     
     <script>
     	const fn_updatePage=(e)=>{
     		window.open("<%=request.getContextPath()%>/account/broker/updateProperty.bb","_blank");
     	}
     </script> 
</body>
</html>

<%@ include file="/views/common/footer.jsp" %>