<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<!-- css -->
<link href="<%=request.getContextPath() %>/css/property/propertyInfo.css" type="text/css" rel="stylesheet">

<div id="roomWrap">
    <img src="image/화면 캡처 2022-11-25 171552.png">
    <img src="image/화면 캡처 2022-11-25 171626.png">
    <img src="image/화면 캡처 2022-11-25 171636.png">
    <img src="image/화면 캡처 2022-11-25 171649.png">
    <img src="image/화면 캡처 2022-11-25 171719.png">
</div>
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
                    <span>월세</span>
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
                    <span>500/40</span>
                </div>
                <br>
                <div id="cost">
                    <span>10만원</span>
                </div>
                <br>
                <div id="costInclude">
                    <span>가스,수도</span>
                </div>
                <br>
                <div id="checkDate">
                    <span>2022-11-26</span>
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
                    <span>2층</span>
                </div>
                
                <div id="room">
                    <span>원룸(분리형)</span>
                </div>
                
                <div id="area">
                    <span>19㎡</span>
                </div>
                
                <div id="expiryDate">
                    <span>협의입주</span>
                </div>
                
                <div id="parking">
                    <span>가능</span>
                </div>
                
                <div id="animal">
                    <span>불가능</span>
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
                        <td><img src="<%=request.getContextPath()%>/images/YJ/에어컨.png" class="optionIcon"></td>
                        <td><img src="<%=request.getContextPath()%>/images/YJ/세탁기.png" class="optionIcon" style="width: 60px; height:60px"></td>
                        <td><img src="<%=request.getContextPath()%>/images/YJ/냉장고.png" class="optionIcon"></td>
                        <td><img src="<%=request.getContextPath()%>/images/YJ/인덕션.png" class="optionIcon"></td>
                        <td><img src="<%=request.getContextPath()%>/images/YJ/전자렌지.png" class="optionIcon"></td>
                    </tr>
                    <tr>
                        <td>에어컨</td>
                        <td>세탁기</td>
                        <td>냉장고</td>
                        <td>인덕션</td>
                        <td>전자렌지</td>
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
            <!-- <textarea name="description" id="description"> -->
신대방역 역세권, 주방분리형 풀옵션 원룸입니다.

★ 특징 ★
✔ 2호선 신대방역 역세권!
✔ 주방분리형으로 공간활용에 좋습니다.
✔ 보라매공원, 도림천과 가까워 살기 좋습니다.
✔ 냉장고가 커서 반찬 및     배달음식 보관에 용이합니다.
✔ 편의점, 카페, 식당, 재래시장, 병원 등 편의시설 다수

                <!-- </textarea> -->
            </pre>
        </div>
    </div>

    <div id="fixDiv">
        <div id="propertiInfo">
            <div id="propertiNo">
                <span>매물번호</span>
                <span>31856399</span>
                <!-- <button id="button">찜하기</button> -->
            </div>
            <br>
            <div id="priceFix">
                <span>월세</span>
                <span>500/40</span>
            </div>
            <div>
                <span>관리비</span>
                <span>10만원</span>
            </div>
            <br>
            <div>
                <span>원룸(분리형)</span>
            </div>
            <div>
                <span>위치: </span>
                <span>서울특별시 관악구 조원로 13길 35</span>
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

<%-- <%@ include file="/views/common/footer.jsp" %> --%>