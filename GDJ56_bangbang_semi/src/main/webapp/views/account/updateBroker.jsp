<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain.jsp"%>

<link href="<%=request.getContextPath() %>/css/account/updateBrokerStyle.css" type="text/css" rel="stylesheet">
<style>
/* 상단바 아래 컨텐츠 */
#divOuter{
    /* border: 1px solid red; */
    align-content: center;
    display: flex;    
    width: 100%;
    justify-content: center;


}
#updatebroker-Container{
    /* border: 1px solid #075A2A; */
    width: fit-content;
    height: fit-content;
    justify-content: center;
    
}
#updatebroker-Container>div>hr{
    width:310px;
    margin-left: 0px;
}
#updatebroker-Container>div>h1{
    margin-bottom: 10px;
    padding-top: 30px;
}
#brokerInfo-Container>#updatebrokerForm>input{
    width: 295px;
    height: 32px;
    font-size: 15px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(255, 255, 255);

}

#updateBrokerBtn{
    width:310px;
    height:40px;
    margin-top: 30px;
    margin-bottom: 10px;
    background-color: #075A2A;
    color: white;
    border-color: #075a2a8c;
    border-radius: 5px;
    border:none;
}

p#info{
    width:310px;
    height:190px;

}

#brokerInfo-Container{
    margin-top: 180px;
}

.content{
    margin-top: 35px;
    display: flex;
    width: 100%;
}


input::placeholder {  
  font-size: 13px;
}



</style>
<section class="content">

    <div id="divOuter">
        <div id="updatebroker-Container">
            <h1>중개사무소 정보수정</h1>
            <hr>
            
            <div id="info-Container">
                <p id="info">
                    1. 국가공간정보포털의 부동산중개업 정보에 등록된 대표공인중개사만 중개사 등록이 가능합니다. <br>
                    2. 관리자가 등록신청서를 확인하고 연락드립니다. <br>
                    3. 마지막으로 가입에 필요한 서류를 보내주시면 담당 관리자가 승인해드립니다. <br>
                    <strong>4. 중개사 정보수정 신청 후에는 당분간 중개활동을 하지 못할 수 있습니다.</strong><br><br>
                    📑 <strong>가입에 필요한 필수서류</strong><br>✔️ 사업자등록증  ✔️ 중개업개설등록증

                    <br><br>📢 <i><u>서류가 미비하거나 허위일 경우,<br> 입력된 정보와 서류내용이 일치하지 않는 경우 승인이 거절될 수 있습니다. </u></i>
                </p>
            </div>
            
            <div id="brokerInfo-Container">
                <form id="updatebrokerForm" action="<%=request.getContextPath()%>/account/broker/updateBrokerEnd.bb" method="method" onsubmit="return fn_submitChk();">
                    <hr>
                    <h3>중개사무소명</h3>                    
                    <input type="text" name="officeName" id="officeName" value="<%=loginBroker.getOfficeName() %>" placeholder="(필수) 중개사무소명을 입력해주세요" required>
                    <h3>중개사무소 주소</h3>
                    <input type="text" name="officeAddress" id="officeAddress" value="<%=loginBroker.getOfficeAddress() %>" placeholder="(필수) 중개사무소 주소를 입력해주세요" required>
                    <h3>중개등록번호</h3>
                    <input type="text" name="registrationNo" id="registrationNo" value="<%=loginBroker.getRegistrationNo() %>" placeholder="(필수) 중개등록번호를 입력해주세요" required>
                    <h3>대표 전화번호</h3>
                    <input type="text" name="telephone" id="telephone" value="<%=loginBroker.getTelephone() %>" placeholder="(필수) 대표 전화번호를 입력해주세요" required>
                    <input type="hidden" name="userNo" id="userNo" value="<%=loginUser.getUserNo()%>">
                    <input type="hidden" name="brokerNo" id="brokerNo" value="<%=loginBroker.getBrokerNo()%>">
                    <input type="hidden" name="admissionState" id="admissionState" value="<%=loginBroker.getAdmissionState()%>">

                    <br>
                    <button id="updateBrokerBtn">수정신청</button>
                </form>
                
                <br>
            </div>
 
        </div>             
  
    </div>

</section>
<script>
    /* const getUserNo=$("#userNo").val();
    console.log(getUserNo); */
    
    const fn_submitChk=()=>{
        confirm("중개사 정보수정 신청 후에는 당분간 중개활동을 하지 못할 수 있습니다. 모든 항목을 정확하게 입력하셨습니까?");        

    }

    //중개등록번호 정규표현식 : ^\d{5}-\d{4}-\d{5}$

</script>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>