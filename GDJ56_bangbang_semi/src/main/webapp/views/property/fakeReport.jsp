<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>허위 매물 신고</title>
<link href="<%=request.getContextPath() %>/css/property/fakeReportStyle.css" type="text/css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Poor+Story&display=swap" rel="stylesheet">

</head>
<body>

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
    <button class="btn-open-popup">허위매물신고</button>
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