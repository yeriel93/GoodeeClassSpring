<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>
<link href="<%=request.getContextPath() %>/css/account/withdrawalStyle.css" type="text/css" rel="stylesheet">

<section class="content">
    <div id="divOuter">
        <div id="withdrawal-Container">
            <h1>회원탈퇴 안내</h1>
            <hr>
            <div id="info-Container">
                <p id="info">
                    지금까지 방방(방 구해줘 방!)의 서비스를 이용해주셔서 감사합니다. <br><br>
                    1. 회원탈퇴 시 해당 아이디는 즉시 탈퇴처리됩니다. <br>
                    2. 회원정보, 알림, 매물 문의 및 조회내역 등 모든 정보가 삭제되고 복구할 수 없습니다.
                </p>
             </div>
            <div id="withdrawalBtn-Container">
                <hr>
                <form id="withdrawal" action="<%=request.getContextPath()%>/account/withdrawalEnd.bb">
                	<p><input type="checkbox" name="withdrawalAgree" value="Y" required> 위 내용을 숙지하였으며, 동의합니다.</p>
                	<input type="hidden" name="userNo" value="<%=loginUser.getUserNo()%>">
                	<button id="withdrawalBtn" >탈퇴하기</button>
                </form>
                <br>
            </div>
 
        </div>             
  
    </div>
</section>
<%@ include file="/views/common/footer.jsp"%>