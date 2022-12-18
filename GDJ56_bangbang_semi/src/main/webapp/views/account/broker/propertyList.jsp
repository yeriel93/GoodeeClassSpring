<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>

<!-- css -->
<link href="<%=request.getContextPath() %>/css/account/broker/propertyList.css" type="text/css" rel="stylesheet">

	<br>
    <!-- 버튼 -->
    <div style="display: flex; justify-content: center;">
        <div id="buttonDiv" style="margin-right: 10px">
            <input type="button" value="전체선택" class="button" onclick="">
            <input type="button" value=" 삭제 " class="button" onclick="">
        </div>
    </div>
    
    <!-- 매물목록 -->
    <section style="display:flex; justify-content:center;">
        <div id="container">
            <div class="propertyWrap">
                <input type="checkbox">
                <input type="button" value="수정" class="updateBtn" style="float: right">

                <div class="imgDiv">
                    <img src="./매물사진/서울특별시 관악구 봉천동 1529-14/property2_91.PNG" alt="">
                </div>
                
                <div class="infoDiv">
                    <div>
                        <span>매물번호</span>
                        <span>300056</span>
                    </div>
                    <div class="price">
                        <span>월세</span>
                        <span>500</span>
                        <span>/</span>
                        <span>40</span>
                    </div>
                    <div>
                        <span>관리비</span>
                        <span>7</span>
                        <span>만</span>
                    </div>
                    <div>
                        <span>주소</span>
                        <span>서울시 봉천동 379-21</span>
                    </div>
                    <div>
                        <span>원룸(오픈형)</span>
                    </div>
                </div>
            </div>
            <div class="propertyWrap"></div>
            <div class="propertyWrap"></div>
            <div class="propertyWrap"></div>
            <div class="propertyWrap"></div>
            <div class="propertyWrap"></div>
        </div>
     
     <script>
     	const fn_update=()=>{
     		window.open("<%=request.getContextPath()%>/account/broker/updateProperty.bb","_blank");
     	}
     </script> 
 </section>
</body>
</html>

<%@ include file="/views/common/footer.jsp" %>