<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp" %>
<link href="<%=request.getContextPath() %>/css/property/insertProperty.css" type="text/css" rel="stylesheet">

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<!-- 주소검색 api -->
<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<!-- 주소 좌표값 변환 api -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=622c2a9d3d39799df3c6db829e75db1d&libraries=services"></script>

<body>
	<!-- 매물등록메뉴 -->
	<section id="all" style="display:flex;">
        <div id="wrap">
            <h2>방 내놓기</h2>

             <h4>🔳 기본정보</h4>
            <div id="address">
                <span class="redtext">주소*</span>
                &nbsp;
                <input type="text" id="sample5_address" name="address" style="background-color: lightgrey; border-width:1px; height: 19px; width: 400px;">
                <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색" ><br>
            </div>
            <br>
            <script>
                function sample5_execDaumPostcode() {
                    new daum.Postcode({
                        oncomplete: function(data) {
                            addr = data.address; // 최종 주소 변수

                            // 주소 정보를 해당 필드에 넣는다.
                            document.getElementById("sample5_address").value = addr;
                          
                			console.log(data);
                			console.log(addr);
                			console.log(data.jibunAddress);
                			
			                // 주소 좌표값(x,y)으로 변환
			                var geocoder = new kakao.maps.services.Geocoder();
								//console.log(geocoder);
			                
							var callback = function(result, status) {
			                    if (status === kakao.maps.services.Status.OK) {
			                        console.log(result);
			                        console.log(result[0].x);
			                        console.log(result[0].y);
			                    }
			                };
			                geocoder.addressSearch(data.jibunAddress, callback); 
                        }
                    }).open();
                }
            </script>
            <div id="floor" style="display: flex; align-text: center;">
                <span class="redtext" style="margin-right: 16px;">층수*</span>
                <select name="floorSelect" style="margin-right: 5px;">
                    <option value="지상">지상</option>
                    <option value="반지하">반지하</option>
                    <option value="옥탑">옥탑</option>
                </select>
                <input type="number" name="floorIn" min="1" placeholder="숫자를 입력해주세요">
                <span class="greytext">층</span>
            </div>
            <hr style="width: 95%;">

            <h4>🔳 거래정보</h4>
            <div id="price" style="display: flex; align-items: center;">
                <span class="redtext" style="margin-right: 5px;">거래종류*</span>
                &nbsp; 
                <button class="greenbutton" onclick="fn_priceY()">➕ 전세</button>
                &nbsp;
                <button class="greenbutton" onclick="fn_priceM()">➕ 월세</button>
                &nbsp;
            </div>
            <div id="priceDiv" style="margin-top: 10px;">
                <div id="yearPrice" style="display: none;">
                    <div class="p">전세</div>
            	    <input type="text" class="inputa" placeholder="전세금">
                    &nbsp; <p>만원</p>
                </div>
                <div id="monthlyPrice" style="display: none;">
                    <div class="p">월세</div>
            	    <input type="text" placeholder="보증금">&nbsp; <p>/</p> &nbsp; 
            	    <input type="text" placeholder="월세">
                    &nbsp; <p>만원</p>
                </div>
            </div>
            <script>
                const fn_priceY=()=>{
                    console.log($("#yearPrice"));
                    $("#yearPrice").css("display","flex");
                    $("#monthlyPrice").css("display","none");
                }
                const fn_priceM=()=>{
                    console.log($("#monthlyPrice"));
                    $("#yearPrice").css("display","none");
                    $("#monthlyPrice").css("display","flex");
                }
            </script>
            <br>
            <div id="cost" style="display: flex; text-align: center;">
                <span class="redtext" style="margin-right: 16px;">관리비*</span>
                &nbsp;
                <input type="radio" class="radio" name="costSelect" value="있음">있음
                <input type="number" name="costIn" min="1" placeholder="금액을 입력해주세요" style="margin-left: 5px;">
                <span class="greytext" style="margin-right: 16px;">만원</span>
                <input type="radio" class="radio" name="costSelect" value="없음">없음
            </div>
            <br>
            <script>
                $("input[name=costSelect]")[1].onclick=()=>{
                	$("input[name=costIn]").attr("disabled",true);
                    //console.log($("input[name=costIn]"));
                }
                $("input[name=costSelect]")[0].onclick=()=>{
                	$("input[name=costIn]").attr("disabled", false);
                    //console.log($("input[name=costIn]"));
                }
            </script>
            <div id="costInclude">
                <span>관리비 포함항목</span>
                &nbsp;
                <input type="checkbox" class="checkbox" name="costE" value="전기">전기
                <input type="checkbox" class="checkbox" name="costG" value="가스">가스
                <input type="checkbox" class="checkbox" name="costW" value="수도">수도
            </div>
            <hr style="width: 95%;">

            <h4>🔳 방 정보</h4>
            <div id="room">
                <span class="redtext">방 구조*</span>
                &nbsp;
                <input type="radio" name="roomSelect" class="radio" value="원룸(오픈형)">원룸(오픈형)
                <input type="radio" name="roomSelect" class="radio" value="원룸(분리형)">원룸(분리형)
                <input type="radio" name="roomSelect" class="radio" value="복층">복층
                <input type="radio" name="roomSelect" class="radio" value="투룸">투룸
            </div>
            <br>
            <div id="area" style="display: flex; align-text: center;">
                <span class="redtext" style="margin-right: 38px;">면적*</span>
                &nbsp;
                <input type="number" name="areaIn" step="0.01">
                <span class="greytext">㎡</span>
            </div>
            <br>
            
            <div id="expiryDate">
                <span class="redtext">공실예정일*</span>
                &nbsp;
                <input type="radio" name="edSelect" class="radio">날짜선택
                <input type="date" name="exdayIn">
                &nbsp;
                <input type="radio" value="공실" name="edSelect" class="radio">공실
                &nbsp;
                <input type="radio" value="협의입주" name="edSelect" class="radio">협의입주
            </div>
            <br>
            <script>
                $("input[name=edSelect]")[0].onclick=()=>{
                	$("input[name=exdayIn]").attr("disabled",false);
                    console.log($("input[name=exdayIn]"));
                }
                $("input[name=edSelect]")[1].onclick=()=>{
                	$("input[type=date]").attr("disabled",true);
                	console.log($("input[name=exdayIn]"));
                }
                $("input[name=edSelect]")[2].onclick=()=>{
                	$("input[name=costIn]").attr("disabled",true);
                	console.log($("input[name=exdayIn]"));
                }
            </script>
            <div id="option">
                <span style="margin-right: 30px;">옵션</span>
                &nbsp;
                <input type="checkbox" class="checkbox" name="cost" value="에어컨">에어컨
                <input type="checkbox" class="checkbox" name="cost" value="세탁기">세탁기
                <input type="checkbox" class="checkbox" name="cost" value="냉장고">냉장고
                <input type="checkbox" class="checkbox" name="cost" value="인덕션">인덕션
                <input type="checkbox" class="checkbox" name="cost" value="전자렌지">전자렌지
            </div>
            <hr style="width: 95%;">

            <h4>🔳 추가 정보</h4>
            <div id="animal">
                <span>반려동물가능여부</span>
                &nbsp;
                <input type="radio" value="가능" name="aniSelect" class="radio">가능
                <input type="radio" value="불가능" name="aniSelect" class="radio">불가능
            </div>
            <br>
            <div id="parking">
                <span style="margin-right: 30px;">주차가능여부</span>
                &nbsp;
                <input type="radio" value="가능" name="parkSelect" class="radio">가능
                <input type="radio" value="불가능" name="parkSelect" class="radio">불가능
            </div>
            <br>
            <div id="comment" style="display: flex; align-items: center;">
                <span>상세 설명</span>
                &nbsp;
                <textarea cols="50" rows="5" style="resize: none;" placeholder="2000자 이내로 작성해주세요"></textarea>
            </div>
            <hr style="width: 95%;">

            <h4>🔳 사진</h4>
            <div id="mainPhoto">
                <fieldset style="width: 200px;">
                    <legend>메인 사진</legend>
                    <img src="<%=request.getContextPath()%>/images/YJ/사진추가하기.png" id="mainPhoto"
                        name="mainPhoto" onclick="fn_upfile();" width="200px" height="200px">
                    <input type="file" name="upFile" style="display: none;">
                </fieldset>
                &nbsp; &nbsp;
            </div>
            <script>
	            const fn_upfile=()=>{
	                $("input[name=upFile]").click();
	            }
	            
                $("input[name=upFile]").change(e=>{
                    const reader = new FileReader();
                    reader.onload = e=>{
                        console.log(e.target.result);
                        $("img[name=mainPhoto]").attr("src",e.target.result);
                    }
                    console.dir(e.target);
                    reader.readAsDataURL(e.target.files[0]);
                });
            </script>
            <div id="photo">
                <fieldset style="width: 80%; display: flex; justify-content:space-between ;">
                    <legend>사진 추가</legend>
                    <img src="<%=request.getContextPath()%>/images/YJ/사진추가하기.png" 
                        name="photo1" onclick="fn_upfile1();" width="150px" height="150px">
                    <input type="file" name="upFile1" style="display: none;">
                    <img src="<%=request.getContextPath()%>/images/YJ/사진추가하기.png" 
                        name="photo2" onclick="fn_upfile2();" width="150px" height="150px">
                    <input type="file" name="upFile2" style="display: none;">
                    <img src="<%=request.getContextPath()%>/images/YJ/사진추가하기.png" 
                        name="photo3" onclick="fn_upfile3();" width="150px" height="150px">
                    <input type="file" name="upFile3" style="display: none;">
                    <img src="<%=request.getContextPath()%>/images/YJ/사진추가하기.png" 
                        name="photo4" onclick="fn_upfile4();" width="150px" height="150px">
                    <input type="file" name="upFile4" style="display: none;">
                </fieldset>
            </div>
            <br>
            <script>
	            const fn_upfile1=()=>{
	                $("input[name=upFile1]").click();
	            }
                $("input[name=upFile1]").change(e=>{
                    console.dir(e.target);
                    const reader = new FileReader();
                    reader.onload = e=>{
                        //console.log(e.target.result); 
                        $("img[name=photo1]").attr("src",e.target.result);
                    }
                    reader.readAsDataURL(e.target.files[0]);
                });
                const fn_upfile2=()=>{
	                $("input[name=upFile2]").click();
	            }
                $("input[name=upFile2]").change(e=>{
                    console.dir(e.target);
                    const reader = new FileReader();
                    reader.onload = e=>{
                        //console.log(e.target.result); 
                        $("img[name=photo2]").attr("src",e.target.result);
                    }
                    reader.readAsDataURL(e.target.files[0]);
                });
                const fn_upfile3=()=>{
	                $("input[name=upFile3]").click();
	            }
                $("input[name=upFile3]").change(e=>{
                    console.dir(e.target);
                    const reader = new FileReader();
                    reader.onload = e=>{
                        //console.log(e.target.result); 
                        $("img[name=photo3]").attr("src",e.target.result);
                    }
                    reader.readAsDataURL(e.target.files[0]);
                });
                const fn_upfile4=()=>{
	                $("input[name=upFile4]").click();
	            }
                $("input[name=upFile4]").change(e=>{
                    console.dir(e.target);
                    const reader = new FileReader();
                    reader.onload = e=>{
                        //console.log(e.target.result); 
                        $("img[name=photo4]").attr("src",e.target.result);
                    }
                    reader.readAsDataURL(e.target.files[0]);
                });
            </script>

            <div id="addroom" style="text-align:center">
                <button class="greenbutton" style="width: 300px; height: 30px;">등록하기</button>
            </div>
            <br>
        </div>


        <!-- 네비게이션 바 -->
        <div id="navi">
            <h4>기본정보</h4>
            <a href="#address" class="aTag">주소</a> <br>
            <a href="#floor" class="aTag">층수</a> <br>
            <hr>

            <h4>거래정보</h4>
            <a href="#price" class="aTag">거래종류(금액)</a> <br>
            <a href="#cost" class="aTag">관리비</a> <br>
            <a href="#costInclude" class="aTag">관리비 포함항목</a> <br>
            <hr>

            <h4>방 정보</h4>
            <a href="#room" class="aTag">방 구조</a> <br>
            <a href="#area" class="aTag">면적</a> <br>
            <a href="#option" class="aTag">옵션</a> <br>
            <a href="#expiryDate" class="aTag">공실예정일</a> <br>
            <hr>

            <h4>추가 정보</h4>
            <a href="#animal" class="aTag">반려동물가능여부</a> <br>
            <a href="#parking" class="aTag">주차가능여부</a> <br>
            <a href="#comment" class="aTag">상세 설명</a> <br>
            <hr> 

            <h4>사진</h4>
            <a href="#photo" class="aTag">방사진 등록</a> <br>
        </div>
    </section>
</body>
</html>