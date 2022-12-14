<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>
<link href="<%=request.getContextPath() %>/css/account/updateBrokerMainStyle.css" type="text/css" rel="stylesheet">

<!DOCTYPE html>
<section class="content">
    <div id="divOuter">
        <div id="button-Container">        
            <button class="btns" id="userInfo" onclick="location.replace('<%=request.getContextPath()%>/account/updateUser.bb')">일반 정보 수정</a></button>
            <button class="btns" id="brokerInfo" onclick="location.replace('<%=request.getContextPath()%>/account/broker/updateBroker.bb')">중개사 정보 수정</a></button>
        </div>
    </div>
    </section>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>