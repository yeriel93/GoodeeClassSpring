<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>

<link href="<%=request.getContextPath() %>/css/account/updateBrokerStyle.css" type="text/css" rel="stylesheet">

<section class="content">

    <div id="divOuter">
        <div id="enrollbroker-Container">
            <h1>중개사무소 정보수정</h1>
            <hr>
            
            <div id="info-Container">
                <p id="info">
                    1. 국가공간정보포털의 부동산중개업 정보에 등록된 대표공인중개사만 회원가입이 가능합니다. <br>
                    2. 관리자가 등록신청서를 확인하고 연락드립니다. <br>
                    3. 마지막으로 가입에 필요한 서류를 보내주시면 담당 관리자가 승인해드립니다. <br>
                    (가입에 필요한 서류 : 사업자등록증, 중개업개설등록증)<br>
                    <strong>4. 중개사 정보수정 신청 후에는 당분간 중개활동을 하지 못할 수 있습니다.</strong>
                </p>
             </div>

            <div id="brokerInfo-Container">
                <hr>
                <h3>중개사무소명</h3>
                <input type="text" id="brokerName" required>
                <h3>중개사무소 주소</h3>
                <input type="text" id="brokerAddr" required>
                <h3>중개등록번호</h3>
                <input type="text" id="brokerNo" required>
                <h3>대표 전화번호</h3>
                <input type="text" id="brokerPhone" required>
                <br>
                <button id="enrollBrokerBtn">수정신청</button>
                
                <br>
            </div>
 
        </div>             
  
    </div>

</section>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>