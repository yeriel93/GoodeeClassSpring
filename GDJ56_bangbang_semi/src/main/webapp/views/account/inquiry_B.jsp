<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>
<%@ page import="java.util.List,com.account.model.vo.AlertList" %>
<%
	List<AlertList> userAlert=(List<AlertList>)request.getAttribute("userAlert");
%>
<style>
.feedback{
	background-color: rgb(7, 90, 42);
	color:white;
	border:none;
	height:28px;
	width:70px;
}

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
<link href="<%=request.getContextPath() %>/css/account/inquiry_BStyle.css" type="text/css" rel="stylesheet">
<section class="content">
     <br>

     <br><br>


        <div id="table-container">
            <table style="width: 100%; margin: auto;">
                <thead>
                    <tr>
                        <th>문의번호</th>
                        <th>매물번호</th>
                        <th>방사진</th>
                        <th>전/월세</th>
                        <th>보증금</th>
                        <th>월세</th>
                        <th>문의 고객명</th>
                        <th>고객 연락처</th>
                        <th>문의일자</th>
                        <th>문의현황</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                    <%if(userAlert.isEmpty()){ %>
                    	<tr>
                    		<td colspan="11">조회된 결과가 없습니다.</td>
                    	</tr>
                    <%} else{
                    	for(int i=0;i<userAlert.size();i++){%>                    
                    	<tr>
	                        <td id="alertNo"><%=userAlert.get(i).getAlertNo() %></td>
	                        <td><a id="propertyNo" href="<%=request.getContextPath() %>/property/propertyInfo.bb?propertyNo=<%=userAlert.get(i).getPropertyNo() %>"><%=userAlert.get(i).getPropertyNo() %></a></td>
	                        <td><img id="propertyImg" src="<%=request.getContextPath() %>/upload/property/<%=userAlert.get(i).getRenamedFilename() %>"></td> 
	                        <td><%=userAlert.get(i).getRenttype() %></td>
	                        <td class="deposit"><%=userAlert.get(i).getDeposit() %></td>
	                        <td class="monthlyCharge"><%=userAlert.get(i).getMonthlycharge() %></td>
	                        <td id="userCName"><%=userAlert.get(i).getName() %></td>
	                        <td><%=userAlert.get(i).getPhone() %></td>
	                        <td><%=userAlert.get(i).getAlertDate() %></td> 
	                        
	                        <%if(userAlert.get(i).getFeedbackCount()>0){ %>                      
		                        <td id="replyState" style="color: green;">
		                        답변 완료
		                        </td>
			                    <input type="hidden" id="userCNo" value="<%=userAlert.get(i).getSendUserNo()%>">
			                    <input type="hidden" id="userBNo" value="<%=userAlert.get(i).getReceiveUserNo()%>">
			                    <td><button class="feedback" disabled="false" style="background-color: gray">답변완료</button></td>                       
							<%}else{ %>
		                        <td id="replyState" sytle="">
		                        답변 대기
		                        </td>
			                    <input type="hidden" id="userCNo" value="<%=userAlert.get(i).getSendUserNo()%>">
			                    <input type="hidden" id="userBNo" value="<%=userAlert.get(i).getReceiveUserNo()%>">
			                    <td><button class="feedback">답변하기</button></td> 							
							<%} %>
                    	</tr>
                    <%}
                    }%>

                </tbody>
            </table>
            <div id="pageBar">
        		<%=request.getAttribute("pageBar") %>
        	</div>
            
        </div>

 </section>
 
 <script>    
    $(".feedback").click(e=>{
    	const alertNo=$(e.target).parent().parent().children().first().text();
    	const propertyNo=$(e.target).parent().parent().children().find('a').text();
    	const userCName=$(e.target).parent().parent().children().eq(6).text();
    	const userCNo=$(e.target).parent().parent().children().eq(10).val();
    	const userBNo=$(e.target).parent().parent().children().eq(11).val();
/*     	console.log(userCNo);
    	console.log(userBNo); */
    	
    	const url="<%=request.getContextPath()%>/account/feedback.bb?alertNo=" + alertNo+"&propertyNo="+propertyNo+"&userCName="+userCName+"&userCNo="+userCNo+"&userBNo="+userBNo;
    	window.open(url,"_blank","width=430,height=480");  	

        

    });
    
 	$(()=>{
 		<%if(!userAlert.isEmpty()){ %>
 				$("td.deposit").each((i,v)=>{
 					let parsePrice = changePrice($(v).text());
 					$(v).text(parsePrice);
 				})
 				
 				$("td.monthlyCharge").each((i,v)=>{
 					if($(v).text()==0){
 						$(v).text("없음");
 						
 					}else{
 						$(v).text($(v).text() + "만원");
 						
 					}
 				})
 				
 		
 		<%}%>
 	});
	const changePrice = (price) => {           
	     let parsePrice = "";
	     
	   if(Math.trunc(price/10000) > 0){
	      parsePrice += Math.trunc(price/10000) + "억";
	      price = price - Math.trunc(price/10000)*10000;
	   }
	   if(price > 0){
	      parsePrice += " " +price+"만";
	   }
	   parsePrice += "원";
	   
	   //console.log(parsePrice.replace(/ /g, ''));
	   return parsePrice;      
	}
 </script>



