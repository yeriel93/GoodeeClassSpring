<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@
	page import ="com.property.model.vo.Property,
				java.util.List,
				com.property.model.vo.Files" 
 %>
<%
	Property property = (Property)request.getAttribute("property");
	List<Files> files = (List<Files>)request.getAttribute("files");
	List<String> option = (List<String>)request.getAttribute("option"); 
%>

<!-- css -->
<link href="<%=request.getContextPath() %>/css/property/propertyInfo.css" type="text/css" rel="stylesheet">

<!-- swiper -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>	

<div id="roomWrap">
	<div class="swiper-container">
		<div class="swiper-wrapper">
		<%for(int i=0;i<files.size();i++) {%>
			<div class="swiper-slide">
				<img id="imgFiles" src="<%=request.getContextPath() %>/upload/property/<%=files.get(i).getRenameFilename() %>">
			</div>
		<%} %>
		</div>
		<!-- 네비게이션 -->
		<div class="swiper-button-next"></div>
		<div class="swiper-button-prev"></div>
		<!-- 페이징 -->
		<div class="swiper-pagination"></div>
	</div>
</div>
<script>
	new Swiper('.swiper-container', {
	
		slidesPerView : 3, // 동시에 보여줄 슬라이드 갯수
		spaceBetween : 0, // 슬라이드간 간격
	
	    freeMode : true, // 슬라이드 넘길 때 위치 고정 여부
	
		loop : true, // 무한 반복
	    autoHeight : true,  // 현재 활성 슬라이드높이 맞게 높이조정
	    watchOverflow : true, // 슬라이드가 1개 일 때 pager, button 숨김 여부 설정
	
	 	// 페이징
		pagination : { 
			el : '.swiper-pagination',
			clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
		},
		navigation : { // 네비게이션
			nextEl : '.swiper-button-next', // 다음 버튼 클래스명
			prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
		},
	});
</script>

<section class ="flex">
    <div id="roomInfoWrap">
        <div class="infoTitle">
            <span>🔳 거래 정보</span>
        </div>
        <br>
        <!-- <span>(단위:만원)</span> -->
        <div id="dealInfo" class ="flex">
            <div id="dealDiv" style=" width: 300px;">
                <div id="price">
                    <span><%=property.getRenttype() %></span>
                </div>
                <br>
                <div id="cost">
                    <span>관리비</span>
                </div>
                <br>
                <div id="costInclude">
                    <span>관리비 포함항목</span>
                </div>
                <br>
                <div id="checkDate">
                    <span>매물확인일</span>
                </div>
            </div>
            <div id="dealDataDiv" class="data">
                <div id="price">
                    <span><%=property.getDeposit()%></span>  
                    <%if(property.getRenttype().equals("월세")) {%>
                    <span>/</span>	
                    <span><%=property.getMonthlyCharge()%></span>
                    <%} %>
                </div>
                <br>
                <div id="cost">
                   	<span><%=property.getManagementCharge() %></span> 
                	<%if(property.getManagementCharge()!=0) {%>
                    	<span>만원</span>
                	<%} %>
                </div>
                <br>
                <div id="costInclude">
                	<%if(property.getElectric()=='Y') {%>
                    	<span>전기</span>
                    <%} %>
                	<%if(property.getGas()=='Y') {%>
                    	<span>가스</span>
                    <%} %>
                	<%if(property.getWater()=='Y') {%>
                    	<span>수도</span>
                    <%} %>
                    <%if(property.getElectric()=='N'&&property.getGas()=='N'&&property.getWater()=='N') {%>
                    	<span>없음</span>
                   	<%} %>
                </div>
                <br>
                <div id="checkDate">
                    <span><%=property.getEnrollDate() %></span>
                </div>
            </div>
        </div>
        <br>
        <hr>

        <br>
        <div class="infoTitle">
            <span>🔳 방 정보</span>
        </div>
        <br>
        <div id="roomInfo" class ="flex">
            <div id="roomDiv" style=" width: 300px;">
                <div id="floor" class ="flex">
                    <div><img src="<%=request.getContextPath()%>/images/YJ/층수.png" class="icon"></div>
                    <div><span>층수</span></div>
                </div>
                
                <div id="room" class ="flex">
                    <div><img src="<%=request.getContextPath()%>/images/YJ/방구조.png" class="icon"></div>
                    <div><span>방 구조</span></div>
                </div>
                
                <div id="area" class ="flex">
                    <div><img src="<%=request.getContextPath()%>/images/YJ/면적.png" class="icon"></div>
                    <div><span>면적</span></div>
                </div>
                
                <div id="expiryDate" class ="flex">
                    <div><img src="<%=request.getContextPath()%>/images/YJ/공실예정일.png" class="icon"></div>
                    <div><span>공실예정일</span></div>
                </div>
                
                <div id="parking" class ="flex">
                    <div><img src="<%=request.getContextPath()%>/images/YJ/주차.png" class="icon"></div>
                    <div><span>주차가능여부</span></div>
                </div>
                
                <div id="animal" class ="flex">
                    <div><img src="<%=request.getContextPath()%>/images/YJ/반려동물.png" class="icon"></div>
                    <div><span>반려동물가능여부</span></div>
                </div>
            </div>
            <div id="roomDataDiv" class="data">
                <div id="floor">
                    <span><%=property.getFloor() %></span>
                </div>
                
                <div id="room">
                    <span><%=property.getPropertyStructure() %></span>
                </div>
                
                <div id="area">
                    <span><%=property.getArea() %></span>
                    <span>㎡</span>
                    &nbsp;
                    <span>/</span>
                    &nbsp;
                    <span><%=Math.round(property.getArea()* 0.3025*10)/10.0 %></span>
 					<span>평</span>
                </div>
                
                <div id="expiryDate">
                	<%if(property.getVacancy()!=null && (property.getVacancy().equals("공실") || property.getVacancy().equals("협의입주"))) {%>
                    	<span><%=property.getVacancy() %></span>
                    <%}else {%>
                    	<span><%=property.getVacancyDate() %></span>
                    <%} %>
                </div>
                
                <div id="parking">
                	<%if(property.getPet()=='Y') {%>
                    	<span>가능</span>
                    <%}else { %>
                    	<span>불가능</span>
                    <%} %>
                </div>
                
                <div id="animal">
                    <%if(property.getParking()=='Y') {%>
                    	<span>가능</span>
                    <%}else { %>
                    	<span>불가능</span>
                    <%} %>
                </div>
                
            </div>
        </div>
        
        <div class ="flex">
            <div id="option" style="width: 360px;">
                <span style="font-size: 25px;">옵션</span>
            </div>
            <div id="optionTbl">
                <table>
                    <tr>
                    	<% for(String o : option) { %>
                    		<td><img src="<%=request.getContextPath()%>/images/YJ/<%=o %>.png" class="optionIcon"></td>
                    	<% } %>
                    </tr>
                    <tr>
                    	<% for(String o : option) { %>
                    		<td><%=o %></td>
                    	<% } %>
                    </tr>
                </table>
            </div>
        </div>
        <br>
        <hr>
        
        <br>
        <div class="infoTitle">
            <span>🔳 위치 정보</span>
        </div>
        <div id="map">
            <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAxMjdfMTEx%2FMDAxNjQzMjgyODE3MTUz.nH1kv0etoHik1cdjWmcIaHnn_m2-_ipJZ3t5grblnosg.InjGik8d1Uxr9kG1-jJXWM4IIya-rvo_44s-TuOHTpgg.JPEG.fwfw_%2FIMG_0856.jpg&type=sc960_832" alt="">
        </div>
        <br>
        <hr>
        
        <br>
        <div class="infoTitle">
            <span>🔳 상세 정보</span>
        </div>
        <div id="description">
            <pre name="description" style="margin-left: 10px;"> 
				<%=property.getDetail() %>
            </pre>
        </div>
    </div>

    <div id="fixDiv">
        <div id="propertiInfo">
            <div id="propertiNo">
                <span>매물번호</span>
                <span><%=property.getPropertyNo() %></span>
                <!-- <button id="button">찜하기</button> -->
            </div>
            <br>
            <div id="priceFix">
                <span><%=property.getRenttype() %></span>
                <span><%=property.getDeposit()%></span>  
                    <%if(property.getRenttype().equals("월세")) {%>
                <span>/</span>	
                <span><%=property.getMonthlyCharge()%></span>
                <%} %>
            </div>
            <div>
                <span>관리비</span>
                <span><%=property.getManagementCharge() %></span> 
              	<%if(property.getManagementCharge()!=0) {%>
                  	<span>만원</span>
              	<%} %>
            </div>
            <br>
            <div>
                <span><%=property.getPropertyStructure() %></span>
            </div>
            <div>
                <span>위치: </span>
                <span><%=property.getAddress() %></span>
            </div>
        </div>
        <hr style="width: 90%;">
        <div id="brokerInfo">
            <div>
                <span>구디 공인중개사사무소</span>
            </div>
            <br>
            <div>
                <span>부동산 위치: </span>
                <span>관악구 신사로 14길 14 1층</span>
            </div>
            <div>
                <span>중개등록번호</span>
                <span>11620-1010-12345</span>
            </div>
            <div>
                <span>대표번호</span>
                <span>010-1234-1234</span>
            </div>
            <div id="buttonDiv">
                <button>문의하기</button>
                <button>허위매물신고</button>
            </div>
        </div>
    </div>
    
</section>

<%@ include file="/views/common/footer.jsp" %>