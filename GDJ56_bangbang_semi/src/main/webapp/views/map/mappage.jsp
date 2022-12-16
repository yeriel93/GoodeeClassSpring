<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.centercoordinate.model.vo.CenterCoordinate" %>
<%
	CenterCoordinate cc = (CenterCoordinate)request.getAttribute("cc");
%>
<%@ include file ="/views/common/header.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/map/mappage.css">
    <nav>
        
        <div id="searchContainer">
            <div id="rentType" class="option buttonContainer" style="margin-left:10px;">
                <button class="optionButton" style="font-size:17px;font-weight:bolder;">월세,전세</button>
                <div class="optionMenu" style="border:1px solid green">
                    <div style="font-size:18px;font-weight:bolder;margin-bottom:20px;margin-top:10px;">
                        <p>거래유형</p>
                        <p></p>
                    </div>
                    <div><label for=""><input class="renttype" type="checkbox" value="월세" checked> 월세</label></div>
                    <div><label for=""><input class="renttype" type="checkbox" value="전세" checked> 전세</label></div>
                    <div class="twoSpace">
                        <div><p>보증금</p></div>
                        <div><p id="depositText">5억원</p></div>
                    </div>    
                    <div>
                        <input id="depositRange" type="range" min="500" max="50000" step="500" style="width:200px;">
                    </div>
                    <div class="twoSpace">
                        <div><p>월세</p></div>
                        <div><p id="monthlyChargeText">300만원</p></div>
                    </div>    
                    <div>
                        <input id="monthlyChargeRange" type="range" min="10" max="300" step="10" style="width:200px;">
                    </div>
                    <br>
                    <div class="buttonContainer">
                        <button style="width:100px;" id="initializeRenttype">초기화</button>
                        &nbsp;
                        <button style="width:100px;" class="applyCheck">적용</button>
                    </div>
                </div>
            </div>
            <div id="roomStructure" class="option buttonContainer" style="margin-left:170px;">
                <button class="optionButton"style="font-size:17px;font-weight:bolder;">방 구조</button>
                <div class="optionMenu" style="border: 1px solid green">
                    <div style="font-size:18px;font-weight:bolder;margin-bottom:20px;margin-top:10px;">
                        <p>방 구조</p>
                        <p></p>
                    </div>
                    <div><label for=""><input class="propertyStructure" type="checkbox" value="오픈형(원룸)" checked> 오픈형(원룸)</label></div>
                    <div><label for=""><input class="propertyStructure" type="checkbox" value="분리형(원룸)" checked> 분리형(원룸)</label></div>
                    <div><label for=""><input class="propertyStructure" type="checkbox" value="복층" checked> 복층</label></div>
                    <div><label for=""><input class="propertyStructure" type="checkbox" value="투룸" checked> 투룸</label></div>
                    <br>
                    <div class="buttonContainer">
                        <button style="width:100px;" id="initializePropertyStructure">초기화</button>
                        &nbsp;
                        <button style="width:100px;" class="applyCheck">적용</button>
                    </div>
                </div>
            </div>
            <div id="applianceOption" class="option buttonContainer" style="margin-left:330px;">
                <button class="optionButton" style="font-size:17px;font-weight:bolder;">가전옵션</button>
                <div class="optionMenu" style="border: 1px solid green">
                    <div style="font-size:18px;font-weight:bolder;margin-bottom:20px;margin-top:10px;">
                        <p>가전옵션</p>
                        <p></p>
                    </div>
                    <div><label for=""><input class="applianceAny" type="checkbox" value="전체" checked> 무관</label><span style="font-size:10px;margin-top:5px;">중복선택이 가능합니다.</span></div>
                    <div><label for=""><input class="applianceOption" type="checkbox" value="1"> 에어컨 포함</label></div>
                    <div><label for=""><input class="applianceOption" type="checkbox" value="2"> 세탁기 포함</label></div>
                    <div><label for=""><input class="applianceOption" type="checkbox" value="3"> 냉장고 포함</label></div>
                    <div><label for=""><input class="applianceOption" type="checkbox" value="4"> 인덕션 포함</label></div>
                    <div><label for=""><input class="applianceOption" type="checkbox" value="5"> 전자레인지 포함</label></div>
                    <br>
                    <div class="buttonContainer">
                        <button style="width:100px;" id="initializeApplianceOption">초기화</button>
                        &nbsp;
                        <button style="width:100px;" class="applyCheck">적용</button>
                    </div>
                </div>
            </div>
        </div>
        <div id="gudongContainer"><span style="font-size:20px;">서울시</span>&nbsp;
            <select name="gu" onchange="getGu();" style="width:130px;height:30px;font-size:20px;font-weight:bolder;">
            </select>
            <select name="dong" style="width:130px;height:30px;font-size:20px;font-weight:bolder;">
            </select>
        </div>
    </nav>
	
    <div id="optionContianer"></div>




    
    <section>
        <!-- 지도를 표시할 div 입니다 -->
        <div id="map"></div>

        <!-- 매물 목록을 표시할 div -->
        <div id="listContainer">
        	<div id=propertyWrap></div>
        </div>
        
        
        <!-- 복사용 div -->
		<div class="propertyContainer" style=" display:none">
	         <div class="propertyImgContainer">
	             <img src="<%=request.getContextPath()%>/upload/property/property1.jpg">
	         </div>
	         <div class="propertyDetailContainer">
	             <div><h3>원룸(오픈형)</h3></div>
	             <div>월세 1000/50</div>
	             <div>관리비 5만</div>
	             <div>서울시 관악구 신림동</div>
	         </div>
	         <input type="hidden">
	     </div>    
    </section>
    
    
	<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@여기부터 script@@@@@@@@@@@@@@@@@@@@@@@-->    
	<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@여기부터 script@@@@@@@@@@@@@@@@@@@@@@@-->    
	<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@여기부터 script@@@@@@@@@@@@@@@@@@@@@@@-->    
	<!-- @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@여기부터 script@@@@@@@@@@@@@@@@@@@@@@@-->    	
    <script>
    	//script 전역변수 선언부
        //구 select-option 들어갈 배열
        const gu = ["강남구","강동구","강북구","강서구","관악구","광진구","구로구","금천구","노원구","도봉구","동대문구","동작구","마포구","서대문구","서초구","성동구","성북구","송파구","양천구","영등포구", "용산구","은평구", "종로구","중구","중랑구"];
        
        //실행될 때
        $(()=>{	
        	drawGu();
            $("[name=gu]").val("<%=cc.getGu()%>");
            getGuLoad();
            //kakao.maps.load로 옮김!!
            //$("input#depositRange").val("40000"); 
            //$("input#monthlyChargeRange").val("500");
       	})
		
        //구 select-option에 option value값 포함해서 채우기
        const drawGu = () => {
            for(let i=0;i<gu.length;i++){
                const guOption = $("<option>").val(gu[i]).text(gu[i]);
                $("[name=gu]").append(guOption);
            }	
        }
        
        
        let hideFlag = true;
        //옵션 버튼 클릭 이벤트
        $(()=>{
            $(".optionButton").click((e)=>{
                //console.log($(e.target).next());
                $(".optionMenu").hide();
                if(hideFlag){
                    $(e.target).next().show();
                    hideFlag = false;
                } else {
                    hideFlag = true;
                }
            }); 			
        });
	
        //동적쿼리를 읽어먹질 못해서 그냥 복붙해서 코드 한줄 추가 -> 나중에 고치자
        const getGuLoad = () =>{
    		let gu = $("[name=gu]").first().val();
    		<%-- location.replace('<%=request.getContextPath()%>/changedong.do?gu='+gu); --%>
    		
    		/* ajax 추가 */
    		$.ajax({
    			url:"<%=request.getContextPath()%>/changedong.bb",
    			type:"get",
    			data:{gu:gu},
    			success:data=>{
    				$("select[name=dong]").html("");
    				data.forEach(v=>{
    					const option = $("<option>").val(v.dong).text(v.dong);
    					$("select[name=dong]").append(option);
    				})
    				$("select[name=dong]").val("<%=cc.getDong()%>");
    			}
    		})
    	}
        //구를 변경하면 해당하는 구의 동을 출력해주는 함수
        const getGu = () => {
    		let gu = $("[name=gu]").first().val();
    		<%-- location.replace('<%=request.getContextPath()%>/changedong.do?gu='+gu); --%>
    		
    		/* ajax 추가 */
    		$.ajax({
    			url:"<%=request.getContextPath()%>/changedong.bb",
    			type:"get",
    			data:{gu:gu},
    			success:data=>{
    				$("select[name=dong]").html("");
    				data.forEach(v=>{
    					const option = $("<option>").val(v.dong).text(v.dong);
    					$("select[name=dong]").append(option);
    				})
    			}
    		})
    	}
        
        //동 변경시 중심좌표 이동
        $("select[name=dong]").change(e=>{
        	let gu = $("select[name=gu]").first().val();
        	let dong = $("select[name=dong]").first().val();
        	$.ajax({
    			url:"<%=request.getContextPath()%>/searchAddressAjax.bb",
    			type:"get",
    			data:{gu:gu,dong:dong},
    			success:data=>{
    			    var moveLatLon = new kakao.maps.LatLng(data.latitude, data.longitude);
    			    map.setCenter(moveLatLon);
    			}
    		})
        })
		
        //조건검색 div 전세, 월세 최소 한개 선택
        $("input.renttype").change(e=>{
        	if($("input.renttype").first().prop("checked")==false &&
        		$("input.renttype").last().prop("checked")==false){
        		$(e.target).prop("checked",true);
        	}	
        });
        
        //조건검색 div 방구조 최소 한개 선택
        $("input.propertyStructure").change(e=>{
        	if($("input.propertyStructure").eq(0).prop("checked")==false &&
        		$("input.propertyStructure").eq(1).prop("checked")==false &&
        		$("input.propertyStructure").eq(2).prop("checked")==false &&
        		$("input.propertyStructure").eq(3).prop("checked")==false){
        		$(e.target).prop("checked",true);
        	}	 
        });
        
        //조건검색 div 가전옵션 선택 조건주기 => 무관 선택시 전체 해제, 가전제품 선택시 무관 해제, 가전제품 모두 해제시 무관 on
        $("input.applianceAny").change(e=>{
        	if($(e.target).prop("checked")==true){
        		$("input.applianceOption").prop("checked",false);
        	} else {
        		$("input.applianceOption").prop("checked",true);
        	}
        })
        
        //조건검색 div 가전옵션 선택 조건주기
        $("input.applianceOption").change(e=>{
        	//가전제품 하나라도 선택시 무관 off
        	let flag = false;
        	$("input.applianceOption").each((i,v)=>{
        		if($(v).prop("checked")==true){
        			flag=true;
        		}
        	})
        	if(flag){
        		$("input.applianceAny").prop("checked",false);
        	}
        	//모두 false일시 무관 on
        	if($("input.applianceOption").eq(0).prop("checked")==false &&
        		$("input.applianceOption").eq(1).prop("checked")==false &&
        		$("input.applianceOption").eq(2).prop("checked")==false &&
        		$("input.applianceOption").eq(3).prop("checked")==false &&
        		$("input.applianceOption").eq(4).prop("checked")==false ){
        			$("input.applianceAny").prop("checked",true);
        		}
        });
        
        
        //조건검색 div deposit,monthlyCharge range를 변경했을때 -> 위 text 변경
        const changePrice = (price) => {        	
	       	let parsePrice = "";
	       	
        	if(Math.trunc(price/10000) > 0){
        		parsePrice += Math.trunc(price/10000) + "억";
        		price = price - Math.trunc(price/10000)*10000;
        	}
        	if(price > 0){
        		parsePrice += " " +price+"만";
        	}
        	parsePrice += "원";
        	
        	//console.log(parsePrice.replace(/ /g, ''));
        	return parsePrice;   	
        }
        $("input#depositRange").change(e=>{
      	  	$("p#depositText").text(changePrice(Number($("input#depositRange").val())));
        });
     	$("input#monthlyChargeRange").change(e=>{
    	  	$("p#monthlyChargeText").text(changePrice(Number($("input#monthlyChargeRange").val())));
      	});
      
      	//조건검색 renttype 초기화 버튼 눌렀을 때
      	$("button#initializeRenttype").click(e=>{
      		$("input.renttype").prop("checked",true);
      		$("input#depositRange").val(50000);      		
      		$("input#monthlyChargeRange").val(300);
      		$("input#depositRange").change();
      		$("input#monthlyChargeRange").change();
      		searchProperty();
        	$("div#propertyWrap").html("");
    	   	$("#listContainer").scrollTop(0);
    	   	cPage = 1;
    		searchPropertyListLoad(cPage);
    		$(".optionMenu").hide();
            hideFlag = true;
      	});
      	
      	//조건검색 roomStructure 초기화 버튼 눌렀을 때
      	$("button#initializePropertyStructure").click(e=>{
      		$("input.propertyStructure").prop("checked",true);
      		searchProperty();
        	$("div#propertyWrap").html("");
    	   	$("#listContainer").scrollTop(0);
    	   	cPage = 1;
    		searchPropertyListLoad(cPage);
    		$(".optionMenu").hide();
            hideFlag = true;
      	});
      	
      	//조건검색 applianceOption 초기화 버튼 눌렀을 때
      	$("button#initializeApplianceOption").click(e=>{
      		$("input.applianceAny").prop("checked",true);
      		$("input.applianceOption").prop("checked",false);
      		searchProperty();
        	$("div#propertyWrap").html("");
    	   	$("#listContainer").scrollTop(0);
    	   	cPage = 1;
    		searchPropertyListLoad(cPage);
    		$(".optionMenu").hide();
            hideFlag = true;
      	});
      	
      	//조건검색 적용 버튼을 눌렀을 때
      	$("button.applyCheck").click(e=>{
      		searchProperty();
        	$("div#propertyWrap").html("");
    	   	$("#listContainer").scrollTop(0);
    	   	cPage = 1;
    		searchPropertyListLoad(cPage);
    		$(".optionMenu").hide();
            hideFlag = true;
      	});
      	
      	
    </script>
    
    
    <!-- 여기부턴 카카오맵 사용 코딩 -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=622c2a9d3d39799df3c6db829e75db1d&libraries=clusterer"></script>
    <script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = { 
            center: new kakao.maps.LatLng(<%=cc.getLatitude()%>, <%=cc.getLongitude()%>), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    var map = new kakao.maps.Map(mapContainer, mapOption);
    
    //마커 이미지 설정
    var imageSrc = "<%=request.getContextPath()%>/images/basicmarker.png";
    var imageSize = new kakao.maps.Size(45,45);
    //var imageOption = {offset: new kakao.maps.Point(27, 69)};
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    
    var selectedImageSrc = "<%=request.getContextPath()%>/images/selectedmarker.png";
    var selectImageSize = new kakao.maps.Size(50, 50);
    var selectedMarkerImage = new kakao.maps.MarkerImage(selectedImageSrc, selectImageSize);
    
    //마커배열
    let markerArray = [];
    //property 배열
    let propertyArray = [];
    
	 // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 7 // 클러스터 할 최소 지도 레벨 
	});
    
    //ajax 방식으로 좌표 내에 있는 매물 받아오는 함수
    const searchProperty = () => {
    	$.ajax({
    		url:"<%=request.getContextPath()%>/map/searchMapProperty.bb",
    		type:"get",
    		traditional:true, // 배열 넘기기
    		data:{
    			longitudes:[map.getBounds().ha, map.getBounds().oa],
    			latitudes:[map.getBounds().qa, map.getBounds().pa],
    			renttypes:[$("input.renttype").first().prop("checked"), $("input.renttype").last().prop("checked")],
    			deposit:$("input#depositRange").val(),
    			monthlyCharge:$("input#monthlyChargeRange").val(),
    			propertyStructures:[$("input.propertyStructure").eq(0).prop("checked"),
    								$("input.propertyStructure").eq(1).prop("checked"),
    								$("input.propertyStructure").eq(2).prop("checked"),
    								$("input.propertyStructure").eq(3).prop("checked")],
    			applianceAny:$("input.applianceAny").prop("checked"),
    			applianceOptions:[$("input.applianceOption").eq(0).prop("checked"),
    								$("input.applianceOption").eq(1).prop("checked"),
    								$("input.applianceOption").eq(2).prop("checked"),
    								$("input.applianceOption").eq(3).prop("checked"),
    								$("input.applianceOption").eq(4).prop("checked")]
    		},
    		//ajax 를 비동기식 -> 동기식으로 변경
    		//async:false, // -> 성능이 너무 떨어져서 매물리스트는 비동식으로 페이징처리 하자.
    		success:data=>{
    			//마커는 계속 초기화 되어야 하므로 마커배열을 초기화해준다.
    			markerArray = [];
    			//추가된 모든 마커를 삭제한다.
    			clusterer.clear();
    			data.forEach(v=>{
    				//마커 찍기
    				let marker = new kakao.maps.Marker({
    					position:new kakao.maps.LatLng(v.latitude, v.longitude),
    					image:markerImage			
    				})
    				//만든 마커를 마커배열에 담는다.
    				markerArray.push(marker);			
    			})
    			clusterer.addMarkers(markerArray);
    			//마커는 계속 초기화 되어야 하므로 마커배열을 초기화해준다.
    			//markerArray = [];
      		}
    	});
    }
    
    let cPage = 1;
    const searchPropertyListLoad = () => {
    	$.ajax({
    		url:"<%=request.getContextPath()%>/map/searchMapPropertyList.bb",
    		type:"get",
    		traditional:true, // 배열 넘기기
    		data:{
    			cPage:cPage,
    			longitudes:[map.getBounds().ha, map.getBounds().oa],
    			latitudes:[map.getBounds().qa, map.getBounds().pa],
    			renttypes:[$("input.renttype").first().prop("checked"), $("input.renttype").last().prop("checked")],
    			deposit:$("input#depositRange").val(),
    			monthlyCharge:$("input#monthlyChargeRange").val(),
    			propertyStructures:[$("input.propertyStructure").eq(0).prop("checked"),
    								$("input.propertyStructure").eq(1).prop("checked"),
    								$("input.propertyStructure").eq(2).prop("checked"),
    								$("input.propertyStructure").eq(3).prop("checked")],
    			applianceAny:$("input.applianceAny").prop("checked"),
    			applianceOptions:[$("input.applianceOption").eq(0).prop("checked"),
    								$("input.applianceOption").eq(1).prop("checked"),
    								$("input.applianceOption").eq(2).prop("checked"),
    								$("input.applianceOption").eq(3).prop("checked"),
    								$("input.applianceOption").eq(4).prop("checked")]
    		},
    		//ajax 를 비동기식 -> 동기식으로 변경
    		//async:false, //-> 성능이 너무 떨어져서 매물리스트는 비동식으로 페이징처리 하자.
    		success:data=>{
    			let num = 10;
    			if(data != null && data.length < 10) num = data.length;
    			for(let i = 0; i < num; i++){
    				const div1 = $("<div>").attr("class","propertyImgContainer").append($("<img>").attr("src",'<%=request.getContextPath()%>/upload/property/'+data[i].thumbnail));
    				const div2 = $("<div>").attr("class","propertyDetailContainer");
    				const innerDiv1 = $("<div>").append($("<h3>").text(data[i].propertyStructure));
    				
    				//let tempTxt = data[i].renttype + " " + changePrice(data[i].deposit).replace(/ /g, '').substring(0,changePrice(data[i].deposit).length-2);
    				//2022-12-16 수정중.. 수정완료..
    				let tempTxt = data[i].renttype + " " + changePrice(data[i].deposit).replace(/ /g, '').substring(0,changePrice(data[i].deposit).replace(/ /g, '').length-1);
    				if(data[i].monthlyCharge > 0) tempTxt += "/"+data[i].monthlyCharge+"만";
    				div2.append(innerDiv1).append($("<div>").text(tempTxt));
    				let tempManage = "관리비 ";
    				if(data[i].managementCharge > 0){
    					tempManage += data[i].managementCharge + "만";
    				} else {
    					tempManage += "없음";
    				}
    				div2.append($("<div>").text(tempManage));
    				let tempAddress = data[i].address.substring(0, data[i].address.lastIndexOf('동 ')+1);
    				div2.append($("<div>").text(tempAddress));
    				
    				const inputPropertyNo = $("<input>").attr("type","hidden").attr("id","propertyNo").val(data[i].propertyNo);
    				const inputLatitude = $("<input>").attr("type","hidden").attr("id","latitude").val(data[i].latitude);
    				const inputLongitude = $("<input>").attr("type","hidden").attr("id","longitude").val(data[i].longitude);
    				//console.log(data[i].propertyNo, data[i].latitude, data[i].longitude);
    				$("div#propertyWrap").append($("<div>").attr("class","propertyContainer").attr("display","flex").append(inputPropertyNo).append(inputLatitude).append(inputLongitude).append(div1).append(div2));								
    			}
    		}
    	});
    }
    

    //매물 리스트를 스크롤 했을 때
    $('#listContainer').scroll(function () {
    	   let dh = $('#propertyWrap').height();
    	   let wt = $('#listContainer').scrollTop();
    	   let wh = $('#listContainer').height();
    	   //스크롤이 끝에 닿았을 때
    	   if (dh == (wt+wh)) {
    	    	$.ajax({
    	    		url:"<%=request.getContextPath()%>/map/searchMapPropertyList.bb",
    	    		type:"get",
    	    		traditional:true, // 배열 넘기기
    	    		data:{
    	    			cPage:++cPage,
    	    			longitudes:[map.getBounds().ha, map.getBounds().oa],
    	    			latitudes:[map.getBounds().qa, map.getBounds().pa],
    	    			renttypes:[$("input.renttype").first().prop("checked"), $("input.renttype").last().prop("checked")],
    	    			deposit:$("input#depositRange").val(),
    	    			monthlyCharge:$("input#monthlyChargeRange").val(),
    	    			propertyStructures:[$("input.propertyStructure").eq(0).prop("checked"),
    	    								$("input.propertyStructure").eq(1).prop("checked"),
    	    								$("input.propertyStructure").eq(2).prop("checked"),
    	    								$("input.propertyStructure").eq(3).prop("checked")],
    	    			applianceAny:$("input.applianceAny").prop("checked"),
    	    			applianceOptions:[$("input.applianceOption").eq(0).prop("checked"),
    	    								$("input.applianceOption").eq(1).prop("checked"),
    	    								$("input.applianceOption").eq(2).prop("checked"),
    	    								$("input.applianceOption").eq(3).prop("checked"),
    	    								$("input.applianceOption").eq(4).prop("checked")]
    	    		},
    	    		success:data=>{
    	    			let num = 10;
    	    			if(data != null && data.length < 10) num = data.length;
    	    			for(let i = 0; i < num; i++){
    	    				const div1 = $("<div>").attr("class","propertyImgContainer").append($("<img>").attr("src",'<%=request.getContextPath()%>/upload/property/'+data[i].thumbnail));
    	    				const div2 = $("<div>").attr("class","propertyDetailContainer");
    	    				const innerDiv1 = $("<div>").append($("<h3>").text(data[i].propertyStructure));
    	    				//let tempTxt = data[i].renttype + " " + changePrice(data[i].deposit).replace(/ /g, '').substring(0,changePrice(data[i].deposit).length-2);
    	    				//2022-12-16 수정중.. 수정완료..
    	    				let tempTxt = data[i].renttype + " " + changePrice(data[i].deposit).replace(/ /g, '').substring(0,changePrice(data[i].deposit).replace(/ /g, '').length-1);
    	    				if(data[i].monthlyCharge > 0) tempTxt += "/"+data[i].monthlyCharge+"만";
    	    				div2.append(innerDiv1).append($("<div>").text(tempTxt));
    	    				let tempManage = "관리비 ";
    	    				if(data[i].managementCharge > 0){
    	    					tempManage += data[i].managementCharge + "만";
    	    				} else {
    	    					tempManage += "없음";
    	    				}
    	    				div2.append($("<div>").text(tempManage));
    	    				let tempAddress = data[i].address.substring(0, data[i].address.lastIndexOf('동 ')+1);
    	    				div2.append($("<div>").text(tempAddress));
    	    				
    	    				//스크롤 기능 테스트 추가
    	    				//페이지 스크롤 기능 테스트로 추가
    	    				
    	    				const inputPropertyNo = $("<input>").attr("type","hidden").attr("id","propertyNo").val(data[i].propertyNo);
    	    				const inputLatitude = $("<input>").attr("type","hidden").attr("id","latitude").val(data[i].latitude);
    	    				const inputLongitude = $("<input>").attr("type","hidden").attr("id","longitude").val(data[i].longitude);
    	    				$("div#propertyWrap").append($("<div>").attr("class","propertyContainer").attr("display","flex").append(inputPropertyNo).append(inputLatitude).append(inputLongitude).append(div1).append(div2));	    	    			}
    	    		}
    	    		
    	    	})
    	   }		
	});
   	
    //매물을 클릭했을 때 -> propertyNo를 넘기면서 propertyInfo servlet을 호출한다.
    $(document).on("click","div.propertyContainer", (e)=>{
    	const propertyNo = $(e.target).parents("div.propertyContainer").children().first().val();
    	let url = "<%=request.getContextPath()%>/property/propertyInfo.bb?propertyNo=" + propertyNo;
    	window.open(url);
    });
    
    //매물에 마우스를 올렸을 때 -> 마커이미지를 변경한다.
    $(document).on("mouseenter","div.propertyContainer",e=>{
    	let latitude = "";
    	let longitude = "";
    	if($(e.target).attr("class") == "propertyContainer"){
    		latitude = $(e.target).children().eq(1).val();
    		longitude = $(e.target).children().eq(2).val();
    	} else {
	    	latitude = $(e.target).parents("div.propertyContainer").children().eq(1).val();
	    	longitude = $(e.target).parents("div.propertyContainer").children().eq(2).val();
   		}
    	//데이터를 끝부분을 잘라먹고 넣어서 알맞게 파싱함..
    	//기존 마커 삭제 -> filter 이용
    	markerArray = markerArray.filter((v,i)=>{
    		let stringLng = v.getPosition().getLng().toString();
    		let stringLat = v.getPosition().getLat().toString();
    		return !(stringLng.indexOf(longitude.substring(0,longitude.length-2)) == 0 && stringLat.indexOf(latitude.substring(0,latitude.length-2)) == 0);
    	})
    	
    	//markerArray에 selected 마커 추가
    	var newMarker = new kakao.maps.Marker({
    	    position: new kakao.maps.LatLng(latitude,longitude),
    		image:selectedMarkerImage
    	});
    	markerArray.push(newMarker);
    	
    	//clusterer 초기화 후 markerArray 입력 
    	clusterer.clear();
    	clusterer.addMarkers(markerArray);
    	
		
    	
    	
    });
    
    //매물에서 마우스가 벗어났을 때
    $(document).on("mouseleave","div.propertyContainer",e=>{
    	let latitude = "";
    	let longitude = "";
    	if($(e.target).attr("class") == "propertyContainer"){
    		latitude = $(e.target).children().eq(1).val();
    		longitude = $(e.target).children().eq(2).val();
    	} else {
	    	latitude = $(e.target).parents("div.propertyContainer").children().eq(1).val();
	    	longitude = $(e.target).parents("div.propertyContainer").children().eq(2).val();
   		}
    	
    	//기존 마커 삭제 -> filter 이용
    	markerArray = markerArray.filter((v,i)=>{
    		let stringLng = v.getPosition().getLng().toString();
    		let stringLat = v.getPosition().getLat().toString();
    		return !(stringLng.indexOf(longitude.substring(0,longitude.length-2)) == 0 && stringLat.indexOf(latitude.substring(0,latitude.length-2)) == 0);
    	})
    	//selected 마커 markerArray에 추가
    	var newMarker = new kakao.maps.Marker({
    	    position: new kakao.maps.LatLng(latitude,longitude),
    		image:markerImage
    	});
    	markerArray.push(newMarker);
    	//클러스터 지운 후 markerArray 다시 추가
    	clusterer.clear();
    	clusterer.addMarkers(markerArray);
    })
       
    //카카오맵이 load 되었을 때
    kakao.maps.load(function(){
    	$("input#depositRange").val("50000"); 
        $("input#monthlyChargeRange").val("300");
        //테스트중
    	searchProperty();
    	//console.log(propertyArray); //잘돌아감
    	//$("div#listContainer").html("");
    	$("div#propertyWrap").html("");
    	$("#listContainer").scrollTop(0);
    	cPage = 1;
    	searchPropertyListLoad(cPage);
    })
    
    
 	// 지도 시점 변화 완료 이벤트를 등록한다
	kakao.maps.event.addListener(map, 'idle', function(){
		searchProperty();
    	//console.log(propertyArray); //잘돌아감
    	//$("div#listContainer").html("");
    	$("div#propertyWrap").html("");
	   	$("#listContainer").scrollTop(0);
	   	cPage = 1;
		searchPropertyListLoad(cPage);
	});
    
    
    
    
    
    
    </script>

    </body>
</html>