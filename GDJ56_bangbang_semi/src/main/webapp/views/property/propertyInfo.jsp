<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@
	page import ="com.property.model.vo.Property,
				java.util.List,
				com.property.model.vo.Files,
				com.broker.model.vo.Broker" 
 %>
<%
	Property property = (Property)request.getAttribute("property");
	List<Files> files = (List<Files>)request.getAttribute("files");
	List<String> option = (List<String>)request.getAttribute("option"); 
	Broker broker = (Broker)request.getAttribute("broker");
%>

<!-- css -->
<link href="<%=request.getContextPath() %>/css/property/propertyInfo.css" type="text/css" rel="stylesheet">

<!-- swiper -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>	

<!-- kakao Map api -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=622c2a9d3d39799df3c6db829e75db1d"></script>

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
                	<%if(property.getManagementCharge()!=0) {%>
                   	<span><%=property.getManagementCharge() %></span> 
                    	<span>만원</span>
                	<%} else {%>
                		<span>없음</span>
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
                <br><br>
                <div id="option" class ="flex">
                	<div><img src="<%=request.getContextPath()%>/images/YJ/옵션.png" class="icon"></div>
                	<div><span style="font-size: 25px;">옵션</span></div>
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
        <div id="optionTbl" style="margin-top: 10px">
	         <table>
	             <tr>
	             	<% for(String o : option) { 
	             		if(!o.equals("옵션없음")){%>
	             			<td><img src="<%=request.getContextPath()%>/images/YJ/<%=o %>.png" class="optionIcon"></td>
	             		<%}else{%>
	             			<td><h2>◼ 없음</h2></td>
	             		<%}
	             	}%>
	             </tr>
	             <tr>
	             	<% for(String o : option) {
	             		if(!o.equals("옵션없음")){%>
	             			<td style="font-size : 16px"><b><%=o %></b></td>
	             		<%} 
	             	}%>
	             </tr>
	         </table>
    	</div>
        <br>
        
        <br>
        <hr>
        
        <br>
        <div class="infoTitle">
            <span>🔳 위치 정보</span>
        </div>
        <div id="map" style="width:75%; height:600px;"></div>
        <br>
        <hr>
        
        <script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new kakao.maps.LatLng(<%=property.getLatitude() %>, <%=property.getLongitude() %>), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };
			
			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			// 마커가 표시될 위치입니다 
			var markerPosition  = new kakao.maps.LatLng(<%=property.getLatitude() %>, <%=property.getLongitude() %>); 

			//마커 이미지 설정
		    var imageSrc = "<%=request.getContextPath()%>/images/basicmarker.png";
		    var imageSize = new kakao.maps.Size(70,70);
		    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
		    
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition,
			    image: markerImage
			});

			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
			
		    
		</script>
        
        <br>
        <div class="infoTitle">
            <span>🔳 상세 정보</span>
        </div>
        <div id="description">
            <pre name="description" style="margin-left: 10px;">
            	<%if(property.getDetail()!=null){ %>
					<%=property.getDetail() %>
				<%} %>
            </pre>
        </div>
    </div>

    <div id="fixDiv">
        <div id="propertiInfo">
            <div id="propertiNo">
                <b><span>매물번호</span>
                <span><%=property.getPropertyNo() %></span></b>
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
                <%if(property.getManagementCharge()!=0) {%>
                  	<span><%=property.getManagementCharge() %></span> 
                   	<span>만원</span>
               	<%} else {%>
               		<span>없음</span>
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
                <span><b><%=broker.getOfficeName() %></b></span>
                <span><b>공인중개사사무소</b></span>
            </div>
            <br>
            <div>
                <span><b>위치: </b></span>
                <span><%=broker.getOfficeAddress() %></span>
            </div>
            <div>
                <span><b>중개등록번호: </b></span>
                <span><%=broker.getRegistrationNo() %></span>
            </div>
            <div>
                <span><b>대표번호: </b></span>
                <span><%=broker.getTelephone() %></span>
            </div>
            <div id="buttonDiv">
                <button onclick="fn_inquiry()">문의하기</button>
                <button onclick="fn_report()">허위매물신고</button>
            </div>
        </div>
    </div>
    <script>
    	const fn_inquiry=()=>{
    		
    	}
    	const fn_report=()=>{
    		
    	}
    </script>
</section>

<%@ include file="/views/common/footer.jsp" %>