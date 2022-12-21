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
<form action="<%=request.getContextPath()%>/property/insertPropertyEnd.bb" method="post" 
	enctype="multipart/form-data" onsubmit="return fn_invalidate();">

	<section id="all" style="display:flex;">
        <div id="wrap">
            <h1 style="font-size:40px"><방 내놓기></h1>
            
        	<%if(loginBroker!=null) { %>
            	<input type="number" name="brokerNo" value="<%=loginBroker.getBrokerNo()%>" placeholder="중개인번호" hidden>
            <%} %>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
             <h2>🔳 기본정보</h2>
            <div id="address">
                <span class="redtext">주소*</span>
                &nbsp;
                <input type="text" id="sample5_address" name="address" hidden>
                <input type="button" id="addrBtn" onclick="sample5_execDaumPostcode()" class="greenbutton" value="주소 검색" style="width: 80px;">
                <span id="showAddr"></span>
                <br>
            </div>
            <br>
            <input type="text" name="addrX" hidden>
            <input type="text" name="addrY" hidden>
            <script>
                function sample5_execDaumPostcode() {
                    new daum.Postcode({
                        oncomplete: function(data) {
                            addr = data.address; // 최종 주소 변수

                            // 주소 정보를 해당 필드에 넣는다.
                            document.getElementById("sample5_address").value = data.jibunAddress;
                            document.getElementById("showAddr").innerText= data.jibunAddress;
                          
                			//console.log(data);
                			//console.log(addr);
                			//console.log(data.jibunAddress);
                			//console.log(document.getElementById("sample5_address").value);
                			
			                // 주소 좌표값(x,y)으로 변환
			                var geocoder = new kakao.maps.services.Geocoder();
								//console.log(geocoder);
			                
							var callback = function(result, status) {
			                    if (status === kakao.maps.services.Status.OK) {
			                       	console.log(result);
			                        //console.log(result[0].x);
			                        //console.log(result[0].y);
                                    $("input[name=addrX]").val(result[0].x);
                                    $("input[name=addrY]").val(result[0].y);
                    
					                //console.log($("input[name=addrX]").val());
					                //console.log($("input[name=addrY]").val());
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
                <input type="number" name="floorIn" min="1" placeholder="숫자를 입력해주세요" required>
                <span class="greytext">층</span>
            </div>
            <hr style="width: 95%;">
            <script>
                $("select[name=floorSelect]")[0].onchange=()=>{
                	const floor = $("select[name=floorSelect]");
                	if(floor.val()=="반지하" || floor.val()=="옥탑") {
                		$("input[name=floorIn]").attr("disabled",true);
                		$("input[name=floorIn]").val("");
                	}else {
                		$("input[name=floorIn]").attr("disabled",false);
                	}
                }
            </script>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
            <h2>🔳 거래정보</h2>
            <div id="price" style="display: flex; align-items: center;">
                <span class="redtext" style="margin-right: 5px;">거래종류*</span>
                &nbsp; &nbsp;
                <input type="button" class="greenbutton" id="insertPrice" onclick="fn_priceY()" value="+ 전세">
                &nbsp;&nbsp;
                <input type="button" class="greenbutton" onclick="fn_priceM()" value="+ 월세">
                &nbsp;
            </div>
            <div id="priceDiv" style="margin-top: 10px;">
                <div id="yearPrice" style="display: none;">
                    <div class="p">전세</div>
            	    <input type="number" class="inputa" name="yPrice" placeholder="전세금">
                    &nbsp; <p>만원</p>
                </div>
                <div id="monthlyPrice" style="display: none;">
                    <div class="p">월세</div>
            	    <input type="number" placeholder="보증금" name="mPrice">&nbsp; <p>/</p> &nbsp; 
            	    <input type="number" placeholder="월세" name="mPrice2">
                    &nbsp; <p>만원</p>
                </div>
            </div>
            <script>
	            const fn_priceY=()=>{
	                //console.log($("#yearPrice"));
	                $("#yearPrice").css("display","flex");
	                $("#monthlyPrice").css("display","none");
	                $("input[name=mPrice]").val("");
	                $("input[name=mPrice2]").val("");
	            }
	            const fn_priceM=()=>{
	                //console.log($("#monthlyPrice"));
	                $("#yearPrice").css("display","none");
	                $("#monthlyPrice").css("display","flex");
	                $("input[name=yPrice]").val("");
	            }
            </script>
            <br>
            <div id="cost" style="display: flex; text-align: center;">
                <span class="redtext" style="margin-right: 16px;">관리비*</span>
                &nbsp;
                <label><input type="radio" class="radio" name="costSelect" value="없음" checked>없음</label> &nbsp;&nbsp;&nbsp;
                <label><input type="radio" class="radio" name="costSelect" id="costYes" value="있음">있음</label>
                <input type="number" name="costIn" min="1" placeholder="금액을 입력해주세요" style="margin-left: 7px;" hidden>
                <span class="greytext" style="margin-right: 16px;" hidden>만원</span>
            </div>
            <br>
            <script>
                $("input[name=costSelect]")[0].onclick=()=>{
                	$("input[name=costIn]").hide();
                	$("input[name=costIn]").next().hide();
                	$("input[name=costIn]").val("");
                    //console.log($("input[name=costIn]"));
                }
                $("input[name=costSelect]")[1].onclick=()=>{
                	$("input[name=costIn]").show();
                	$("input[name=costIn]").next().show()
                    //console.log($("input[name=costIn]"));
                }
            </script>
            <div id="costInclude">
                <span>관리비 포함항목</span>
                &nbsp;
                <label><input type="checkbox" class="checkbox" name="costInclude" value="전기">전기</label>  &nbsp;
                <label><input type="checkbox" class="checkbox" name="costInclude" value="가스">가스</label>  &nbsp;
                <label><input type="checkbox" class="checkbox" name="costInclude" value="수도">수도</label>  &nbsp;
            </div>
            <br>
            <hr style="width: 95%;">
<!--------------------------------------------------------------------------------------------------------------------------------------------------->
            <h2>🔳 방 정보</h2>
            <div id="room">
                <span class="redtext">방 구조*</span>
                &nbsp;
                <label><input type="radio" name="roomSelect" class="radio" value="오픈형(원룸)" checked>오픈형(원룸) </label> &nbsp;
                <label><input type="radio" name="roomSelect" class="radio" value="분리형(원룸)">분리형(원룸) </label>  &nbsp;
                <label><input type="radio" name="roomSelect" class="radio" value="복층">복층 </label> &nbsp;
                <label><input type="radio" name="roomSelect" class="radio" value="투룸">투룸 </label> &nbsp;
            </div>
            <br>
            <div id="area" style="display: flex; align-text: center;">
                <span class="redtext" style="margin-right: 38px;">면적*</span>
                &nbsp;
                <input type="number" name="areaIn" step="0.01" onkeyup="changeNum(event)" required>
                <span class="greytext">㎡</span>
                <span style="margin-left: 12px; margin-right: 2px; color:rgb(7, 90, 42)">✔</span>                
                <span id="sqft" style="color:rgb(7, 90, 42)">0</span>
                <span style="color:rgb(7, 90, 42)">평</span>
            </div>
            <br>
           	<script>
           		const changeNum=(e)=>{
           			let sqft = Math.round($(e.target).val()* 0.3025*10)/10.0;
           			//console.log(sqft);
           			$("#sqft").text(sqft);
           		}
           	</script>
            
            <div id="expiryDate">
                <span class="redtext">공실예정일*</span>
                &nbsp;
                <label><input type="radio" name="edSelect" class="radio" value="choice">날짜선택 </label>
                <input type="date" name="exdayIn" disabled>
                &nbsp;
                <label><input type="radio" value="공실" name="edSelect" class="radio" checked>공실 </label>
                &nbsp;
                <label><input type="radio" value="협의입주" name="edSelect" class="radio">협의입주 </label>
            </div>
            <br>
            <script>
	            $("input[name=edSelect]")[0].onclick=()=>{
	            	$("input[name=exdayIn]").attr("disabled",false);
	                //console.log($("input[name=exdayIn]"));
	            }
	            $("input[name=edSelect]")[1].onclick=()=>{
	            	$("input[name=exdayIn]").attr("disabled",true);
	            	$("input[name=exdayIn]").val("");
	            	//console.log($("input[name=exdayIn]"));
	            }
	            $("input[name=edSelect]")[2].onclick=()=>{
	            	$("input[name=exdayIn]").attr("disabled",true);
	            	$("input[name=exdayIn]").val("");
	            	//console.log($("input[name=exdayIn]"));
	            }
            </script>
            <div id="option" style="display: flex;">
                <span style="margin-right: 30px;">옵션</span>
                &nbsp;
                <label><input type="radio" onclick="fn_no()" id="optionNo" class="radio" name="optionR" value="6" checked>없음 </label>&nbsp;&nbsp;
                <label><input type="radio" onclick="fn_yes()" id="optionY" class="radio" name="optionR" >있음 </label>&nbsp;&nbsp;
                
                <div id="optionAll" hidden>
                    <label><input type="checkbox" class="checkbox selectOpt" id="opt1" name="option" value="1" >에어컨 </label>&nbsp;
                    <label><input type="checkbox" class="checkbox selectOpt" id="opt2" name="option" value="2" >세탁기 </label>&nbsp;
                    <label><input type="checkbox" class="checkbox selectOpt" id="opt3" name="option" value="3" >냉장고 </label>&nbsp;
                    <label><input type="checkbox" class="checkbox selectOpt" id="opt4" name="option" value="4" >인덕션 </label>&nbsp;
                    <label><input type="checkbox" class="checkbox selectOpt" id="opt5" name="option" value="5" >전자렌지 </label>&nbsp;
                    <label><input type="checkbox" class="checkbox" name="option" value="6" id="optionNohidden" checked hidden></label>
                </div>
            </div>
            <br>
            <hr style="width: 95%;">
            <script>
                const fn_no=()=>{
                    $("#optionNohidden").prop("checked",true);
                    $("#optionAll").hide();
                    // const select = $(".selectOpt");
                    //5개 체크박스 전부 다 체크해제
                    $(".selectOpt").prop("checked",false);
                }
                const fn_yes=()=>{
                    $("#optionNohidden").prop("checked",false);
                    $("#optionAll").show();
                }
            </script>
<!--------------------------------------------------------------------------------------------------------------------------------------------------->	
            <h2>🔳 추가 정보</h2>
            <div id="animal">
                <span>반려동물가능여부</span>
                &nbsp;
                <label><input type="radio" value="Y" name="petSelect" class="radio">가능</label> &nbsp;
                <label><input type="radio" value="N" name="petSelect" class="radio" checked>불가능</label> &nbsp;
            </div>
            <br>
            <div id="parking">
                <span style="margin-right: 30px;">주차가능여부</span>
                &nbsp;&nbsp;
                <label><input type="radio" value="Y" name="parkSelect" class="radio">가능</label> &nbsp;
                <label><input type="radio" value="N" name="parkSelect" class="radio" checked>불가능</label> &nbsp;
            </div>
            <br>
            <div id="comment" style="display: flex; align-items: center;"> 
                <span style="margin-right: 40px">상세 설명</span>
                <textarea cols="100" rows="20" name="detail" style="resize: none;" placeholder="3000자 이내로 작성해주세요"></textarea>
            </div>
			<br>
            <hr style="width: 95%;">
<!--------------------------------------------------------------------------------------------------------------------------------------------------->			
            <h2>🔳 사진</h2>
            <div>
                <p>🔊 메인사진 등록은 <b style="color: red;">필수</b>입니다.</p>
                <p>🔊 사진은 최소 <b style="color: red;">3장</b>이상 등록해주세요.</p>
            </div> <br>
            <div id="mainPhoto">
                <fieldset style="width: 200px;">
                    <legend style="font-size: 20px; color: red;"><b>메인 사진</b></legend>
                    <img src="<%=request.getContextPath()%>/images/YJ/사진추가하기.png" id="mainPhoto"
                        name="mainPhoto" onclick="fn_upfile();" width="200px" height="200px">
                    <input type="file" name="mainFile" style="display: none;">
                </fieldset>
                &nbsp; &nbsp;
            </div>
            <script>
	            const fn_upfile=()=>{
	                $("input[name=mainFile]").click();
	            }
	            
                $("input[name=mainFile]").change(e=>{
                    const reader = new FileReader();
                    reader.onload = e=>{
                        //console.log(e.target.result);
                        $("img[name=mainPhoto]").attr("src",e.target.result);
                    }
                    //console.dir(e.target);
                    reader.readAsDataURL(e.target.files[0]);
                });
            </script>
            <div id="photo">
                <fieldset style="width: 80%; display: flex; justify-content:space-between ;">
                    <legend style="font-size: 20px;"><b>사진</b></legend>
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
            <br><br>
            <script>
	            const fn_upfile1=()=>{
	                $("input[name=upFile1]").click();
	            }
                $("input[name=upFile1]").change(e=>{
                    //console.dir(e.target);
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
                    //console.dir(e.target);
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
                    //console.dir(e.target);
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
                    //console.dir(e.target);
                    const reader = new FileReader();
                    reader.onload = e=>{
                        //console.log(e.target.result); 
                        $("img[name=photo4]").attr("src",e.target.result);
                    }
                    reader.readAsDataURL(e.target.files[0]);
                });
            </script>

            <div id="addroom" style="text-align:center">
                <input type="submit" class="greenbutton" id="insertbtn" value="등록하기">
            </div>
            <br>
        </div>

<!--------------------------------------------------------------------------------------------------------------------------------------------------->
        <!-- 네비게이션 바 -->
        <div id="navi">
            <h3>기본정보</h3>
            <a href="#address" class="aTag">주소</a> <br>
            <a href="#floor" class="aTag">층수</a> <br>
            <hr>

            <h3>거래정보</h3>
            <a href="#price" class="aTag">거래종류(금액)</a> <br>
            <a href="#cost" class="aTag">관리비</a> <br>
            <a href="#costInclude" class="aTag">관리비 포함항목</a> <br>
            <hr>

            <h3>방 정보</h3>
            <a href="#room" class="aTag">방 구조</a> <br>
            <a href="#area" class="aTag">면적</a> <br>
            <a href="#option" class="aTag">옵션</a> <br>
            <a href="#expiryDate" class="aTag">공실예정일</a> <br>
            <hr>

            <h3>추가 정보</h3>
            <a href="#animal" class="aTag">반려동물가능여부</a> <br>
            <a href="#parking" class="aTag">주차가능여부</a> <br>
            <a href="#comment" class="aTag">상세 설명</a> <br>
            <hr> 

            <h3>사진</h3>
            <a href="#photo" class="aTag">방사진 등록</a> <br>
        </div>
	</section>
</form>
 <!--------------------------------------------------------------------------------------------------------------------------------------------------->
    <script>
        const fn_invalidate=()=>{
    
            //주소입력
            const addr = $("#sample5_address").val().trim();
            if(addr.length==0){
                alert("주소를 입력하세요!");
                $("#addrBtn").focus();
                return false;
            }

            //전월세입력
            const yPrice=$("input[name=yPrice]").val().trim();
            const mPrice=$("input[name=mPrice]").val().trim();
            const mPrice2=$("input[name=mPrice2]").val().trim();
            if(yPrice.length==0 && mPrice.length==0 && mPrice2.length==0) {
                alert("금액을 입력하세요!");
                $("#insertPrice").focus();
    			return false;
            }
            
            //관리비 입력
            const costIn = $("input[name=costIn]").val().trim();
            if($("#costYes").prop("checked")==true){
	            if(costIn.length==0) {
	                alert("관리비를 입력하세요!");
	    			return false;
	            }
            }
            
          //메인사진 넣기
            let count=0;
            const mainFile = $("input[name=mainFile]").val().trim();
            if(mainFile.length==0){
                alert("메인사진을 등록해주세요!");
                console.log(count);
                return false;
            } else{
            	count++;
            }
            //최소3장이상 사진등록
            const photo1 = $("input[name=upFile1]").val().trim();
            const photo2 = $("input[name=upFile2]").val().trim();
            const photo3 = $("input[name=upFile3]").val().trim();
            const photo4 = $("input[name=upFile4]").val().trim();
            if(photo1.length>0) count++;
            if(photo2.length>0) count++;
            if(photo3.length>0) count++;
            if(photo4.length>0) count++;
            
            if(count<3){
                alert("메인사진 포함 사진을 최소 3장이상 등록해주세요!");
                return false;
            }
            
            //옵션 선택
            if($("#optionY").prop("checked")==true && (
                $("#opt1").prop("checked")==false && $("#opt2").prop("checked")==false && $("#opt3").prop("checked")==false
                && $("#opt4").prop("checked")==false && $("#opt5").prop("checked")==false)){
                    alert("옵션을 체크해주세요!");
                    $("#optionY").focus();
                    return false;
            }
        }
    </script>
    
<%@ include file="/views/common/footer.jsp" %>