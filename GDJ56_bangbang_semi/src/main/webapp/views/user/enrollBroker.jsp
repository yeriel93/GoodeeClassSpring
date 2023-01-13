<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link href="<%=request.getContextPath() %>/css/user/enrollBrokerStyle.css" type="text/css" rel="stylesheet">

<style>
#enrollbroker-Container>#enrollbrokerFrm>div>input{
    width: 300px;
    height: 32px;
    font-size: 15px;
    border: 0;
    border-radius: 15px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(255, 255, 255);

}
#enrollBrokerBtn{
    width:310px;
    height:40px;
    margin-top: 30px;
    margin-bottom: 10px;
    background-color: #075A2A;
    color: white;
    border-color: #075a2a8c;
    border-radius: 5px;
    border:none;
    font-size:15px;
}

</style>

<section class="content">

    <div id="divOuter">
        <div id="enrollbroker-Container">
            <form id="enrollbrokerFrm" action="<%=request.getContextPath()%>/user/enrollBrokerEnd.bb" method="post" onsubmit="return fn_submitChk();">

                <h1>중개사무소 등록</h1>
                <hr>
                <div id="info-Container">
                    <p id="info">
                        1. 국가공간정보포털의 부동산중개업 정보에 등록된 대표공인중개사만 회원가입이 가능합니다. <br>
                        2. 관리자가 등록신청서를 확인하고 연락드립니다. <br>
                        3. 마지막으로 가입에 필요한 서류를 보내주시면 담당 관리자가 승인해드립니다. <br><br>
                        📑 <strong>가입에 필요한 필수서류</strong><br>✔️ 사업자등록증  ✔️ 중개업개설등록증

                        <br><br>📢 <i><u>서류가 미비하거나 허위일 경우,<br> 입력된 정보와 서류내용이 일치하지 않는 경우 승인이 거절될 수 있습니다. </u></i>
                    </p>
                </div>
                <div id="brokerInfo-Container">
                    <hr>
                    <h3>중개사무소명</h3>
                    <input type="text" name="officeName" id="officeName" placeholder="중개사무소명을 입력해주세요" required>
                    <h3>중개사무소 주소</h3>
                    <input type="text" name="officeAddress" id="officeAddress" placeholder="중개사무소 주소를 입력해주세요" required>
                    <h3>중개등록번호</h3>
                    <input type="text" name="registrationNo" id="registrationNo" placeholder="중개등록번호를 입력해주세요" required>
                    <h3>대표 전화번호</h3>
                    <input type="text" name="telephone" id="telephone" placeholder="대표 전화번호를 입력해주세요" required>
                    <input type="hidden" name="userNo" id="userNo" value="<%=loginUser.getUserNo()%>">
                    <br>
                    <button id="enrollBrokerBtn">등록신청</button>
                    <br>
                </form>
            </div>
 
        </div>             
  
    </div>

</section>

<script>
    /* const getUserNo=$("#userNo").val();
    console.log(getUserNo); */
    
    const fn_submitChk=()=>{
        confirm("제출하면 수정하실 수 없습니다. 모든 항목을 정확하게 입력하셨습니까?");        

    }

</script>

<%@ include file="/views/common/footer.jsp"%>