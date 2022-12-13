<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/mypageMain_B.jsp"%>

<link href="<%=request.getContextPath() %>/css/account/inquiry_BStyle.css" type="text/css" rel="stylesheet">
<section class="content">
     <br>

     <br><br>

     <form action="">
        <div id="table-container">
            <table style="width: 1850px; margin: auto;">
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
                    <tr>
                        <td>123456</td>
                        <td><a href="">매물번호1234</a></td>
                        <td>방 사진</td>
                        <td>전세</td>
                        <td>4000</td>
                        <td>33</td>
                        <td>임연지</td>
                        <td>01011112222</td>
                        <td>2022-10-16</td>

                        <!-- if~ -->
                        <!-- <td id="replyState" style="color:green">
                            답변 대기
                        </td> -->
                        <td id="replyState" style="color:gray">
                            답변 완료
                        </td>

                        <!-- if~ -->
                        <!-- <td><button>답변하기</button></td> -->
                        <td></td>

                    </tr>
                    <tr>
                        <td>123456</td>
                        <td><a href="">매물번호1234</a></td>
                        <td>방 사진</td>
                        <td>전세</td>
                        <td>4000</td>
                        <td>33</td>
                        <td>이병도</td>
                        <td>01022223333</td>
                        <td>2022-10-16</td>
                        <td id="replyState" style="color:green">
                        답변 대기
                        </td>
                        <!-- <td id="replyState" style="color:green">
                        답변 대기
                        </td> -->
                        <td><button id="reply">답변하기</button></td>
                    </tr>

                </tbody>
            </table>
        </div>
     </form> 
 </section>





<!-- modal -->
<form action="">    
    <div class="modal">
        <div class="modal_body">
            <h1>허위매물신고</h1>
            <p>
                <hr>
                허위매물로 피해를 입으신 경우, 신고를 하실 수 있습니다.<br>
                허위매물 신고시, 아래 항목을 정확히 기재해주시고,<br>
                신고하신 내용은 확인 후 조치하겠습니다.<br>
                허위매물임이 확인된 경우에는 중개업소에 패널티가 부여되며,<br>
                신고내용이 정확하지 않거나, 허위신고일 경우 서비스 이용에 제한이 있으니 유의해주세요.
            </p>
            <hr>
            <!-- <select name="report">
                <option value="매물">매물신고</option>
                <option value="업주">부동산신고</option>
                <option value="직원">중개인신고</option>
            </select> -->
            <br><br>
            <label>
                <textarea name="content" placeholder="200자 이내로 작성해주세요"></textarea><br>
            </label>
            <hr>
            <input type="checkbox" name="agree">이와 같은 내용으로 신고합니다
            <input type="submit" value="확인">
        </div>
    </div>
</form>

<button class="btn-open-popup">허위매물신고 모달 테스트용</button>

<script>
    const body = document.querySelector('body');
    const modal = document.querySelector('.modal');
    const btnOpenPopup = document.querySelector('.btn-open-popup');

    btnOpenPopup.addEventListener('click', () => {
        modal.classList.toggle('show');

        if (modal.classList.contains('show')) {
            body.style.overflow = 'hidden';
        }
    });
    modal.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.classList.toggle('show');

            if (!modal.classList.contains('show')) {
                body.style.overflow = 'auto';
            }
         }
    });
</script>


</body>
</html>


</section>
