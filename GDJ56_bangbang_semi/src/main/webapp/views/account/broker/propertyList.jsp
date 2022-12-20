<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, 
				com.property.model.vo.Property"%>
<%
	List<Property> propertys = (List<Property>)request.getAttribute("propertys");
	propertys.forEach(v->System.out.println(v));
%>
 
<%@ include file="/views/common/mypageMain.jsp"%>

<!-- css -->
<link href="<%=request.getContextPath() %>/css/account/broker/propertyList.css" type="text/css" rel="stylesheet">

	<br>
    <%if(propertys!=null && propertys.size()>0){ %>
    	
    <!-- 버튼 -->
    <div style="display: flex; justify-content: center;">
        <div id="buttonDiv" style="margin-right: 10px">
            <input type="button" value="전체선택" class="button" onclick="fn_allChecked()">
            <input type="button" value="전체해제" class="button" onclick="fn_allNoChecked()">
            <input type="button" value="삭제" class="button" onclick="fn_delete()">
        </div>
    </div>
	<script>
        const fn_allChecked=()=>{
            // console.log($("input[name=inputCheckbox]"));
            $("input[name=inputCheckbox]").prop("checked",true);
        }
        const fn_allNoChecked=()=>{
            // console.log($("input[name=inputCheckbox]"));
            $("input[name=inputCheckbox]").prop("checked",false);
        }
    </script>
    
    <section style="display:flex; justify-content:center;">
    <!-- 매물목록 -->
        <div id="container">
   		<%for(Property p : propertys){%>
            <div class="propertyWrap" >
                <input type="checkbox" name="inputCheckbox">
                <input type="button" value="수정" class="updateBtn" onclick="fn_updatePage(event)">
                <input type="number" name="propertyNo" id="propertyNo" value="<%=p.getPropertyNo()%>" hidden>
                
                <div class="showProperty" onclick="fn_showPropertyInfo(event)">
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
            </div>
 		<%}%>
        </div>
	 </section>
        
        <script>
        	//매물클릭했을때 상세페이지 이동
        	const fn_showPropertyInfo=(e)=>{
                console.log($(e.target).parents("div.showProperty"));
                console.log($(e.target).parents("div.showProperty").children("#showProperty"));
                let propertyNo = $(e.target).parents("div.showProperty").prev().val();
                console.log(propertyNo);
                <%-- window.open("<%=request.getContextPath()%>/property/propertyInfo.bb?propertyNo=" + propertyNo); --%>
        		
        	}
        	
            //수정버튼 클릭했을때
            let fn_updatePage=(e)=>{
                // console.log($(e.target).next().val())
                // console.log($("input[name=propertyNo]"));
                let proeprtyNo= $(e.target).next().val(); 
                window.open("<%=request.getContextPath()%>/account/broker/updateProperty.bb?propertyNo="+proeprtyNo,"_blank");
            }
            
            //삭제버튼 클릭했을때
            let fn_delete=()=>{
                const checkTag = $("input[name=inputCheckbox]:checked").next().next();
                // console.log(checkTag);
                if(checkTag.length==0){
                    alert("체크된 매물이 없습니다");
                }else{
                    let propertysNo = "(";
                    checkTag.each((i,v)=>{
                        // console.log($(v).val());
                        propertysNo += $(v).val()+","
                    });
                    //console.log(propertysNo.slice(0,-1));
                    var propertyNo = propertysNo.slice(0,-1)+")";
                    console.log(propertyNo);
                    window.open("<%=request.getContextPath()%>/account/broker/deleteProperty.bb?propertyNo="+propertyNo);
                }
            }
        </script> 
     
     <%}else {%>
     	<div id="noDiv">
     		<h1>등록된 매물이 없습니다.</h1>
     	</div>
     <%} %> 
       
</body>
</html>

<%@ include file="/views/common/footer.jsp" %>