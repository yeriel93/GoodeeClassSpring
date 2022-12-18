<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<%@ include file="/views/common/adminHeader.jsp" %>
<link href="<%=request.getContextPath()%>/css/admin/adminBrokerStyle.css?ver=1" type="text/css" rel="stylesheet">
<section>
	<div id="listContainer">
		<h2>중개사관리</h2>
		<div id="search-container">
        	검색타입 : 
        	<select id="searchType">
        		<option value="brokerNo">중개사번호</option>
        		<option value="officeName">상호명</option>
        		<option value="registrationNo">중개등록번호</option>
        		<option value="admissionState">승인여부</option>
        	</select>
        	
        	<div id="search-brokerNo">
       			<input type="text" name="searchKeyword" size="30" 
       			placeholder="검색할 중개사번호를 입력하세요">
       			<input type="hidden" name="searchType" value="BROKER_NO">
       			<button class="searchBtn">검색</button>
        	</div>
        	<div id="search-officeName">
       			<input type="text" name="searchKeyword" size="30" 
       			placeholder="검색할 상호명을 입력하세요" >
       			<input type="hidden" name="searchType" value="OFFICE_NAME">
       			<button class="searchBtn">검색</button>
        	</div>
        	<div id="search-registrationNo">
       			<input type="text" name="searchKeyword" size="30" 
       			placeholder="검색할 중개등록번호를 입력하세요">
       			<input type="hidden" name="searchType" value="REGISTRATION_NO">
       			<button class="searchBtn">검색</button>
        	</div>
        	<div id="search-admissionState">
       			<label><input type="radio" name="searchKeyword" value="N">승인대기</label> 
       			<label><input type="radio" name="searchKeyword" value="Y">승인완료</label>
       			<input type="hidden" name="searchType" value="ADMISSION_STATE">
       			<button class="searchBtn">검색</button>
        	</div>
        	<input type="hidden" name="searchType" value="clear">
        	<button  class="searchBtn">초기화</button>
        </div>
        <div id="numPerpage-container">
        	페이지당 회원수 : 
       		<select name="numPerpage" id="numPerpage">
       			<option value="15">15</option>
       			<option value="10">10</option>
       			<option value="5">5</option>
       		</select>	
       	</div>
		<div id="brokerContainer" class = "tableContainer">
			<table id="brokerTable">
			
			</table>
		</div>
		<div id="pageBarContainer">
			<div id=pageBar></div>
		</div>
	</div>
</section>
<script>
	let searchState;
	let searchKeyword;
	let cPage;
	let numPerpage;
	//페이지가 열릴 때
	$(()=>{
		searchState = "clear";
		<%if(searchType != null){%>
			searchState = "<%=searchType%>";
		<%}%>
		searchKeyword = "";
		<%if(searchKeyword != null){ %>
			searchKeyword = "<%=searchKeyword%>";
			$("div#search-brokerNo").children().first().val("<%=searchKeyword%>");
		<%}%>
		cPage = "1";
		numPerpage = "15";
		
		drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
	});
	
	//검색타입을 변경했을 때
	$("select#searchType").change(e=>{
		const type = $(e.target).val();
		$("div#search-container>div").hide();
		$("div#search-"+type).css("display","inline-block");
		$("input[name=searchKeyword][type=text]").val("");
		$("div#search-admissionState>label>input[name=searchKeyword]").first().prop("checked",true);
		
	});
	
	//페이지당 회원수를 변경했을 때
	$("select#numPerpage").change(e=>{
		numPerpage = $(e.target).val();
		cPage = 1;
		drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
	});
	
	//검색 버튼을 눌렀을 때
	$("button.searchBtn").click(e=>{
		searchState = $(e.target).prev().val();
		if(searchState == 'OFFICE_NAME' || searchState == 'BROKER_NO' || searchState == 'REGISTRATION_NO') {
			searchKeyword = $(e.target).prev().prev().val();
		} else if(searchState == "ADMISSION_STATE") {
			searchKeyword = $("div#search-admissionState input[name=searchKeyword]:checked").val();
		} else {
			searchKeyword="";
		}
		cPage = 1;
		drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
	});
	
	//페이지바 클릭했을 때
	$(document).on("click","a.pageBarTag",(e)=>{
		//console.log(!isNaN(Number($(e.target).text().trim())));
		if(!isNaN(Number($(e.target).text().trim()))){
			cPage = Number($(e.target).text().trim());
			drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
		} else if($(e.target).text().trim().includes("다음")){
			cPage = cPage + 1;
			drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
		} else if($(e.target).text().trim().includes("이전")) {
			cPage = cPage - 1;
			drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
		}
	}); 
	
	//회원 번호 클릭했을 때
	$(document).on("click","a.userNoTag",(e)=>{
		//console.log($(e.target).text()); // 중개사번호
		let url = "<%=request.getContextPath()%>/admin/user.bb?searchType=USER_NO&searchKeyword=" + $(e.target).text();
		window.open(url);
	});
	
	//승인하기 버튼을 클릭했을 때
	$(document).on("click","button.admissionBtn",e=>{
		let setAdmission = $(e.target).attr("id")=="admissionBtnToY"?'Y':'N';
		let confirmMsg = setAdmission=='Y'?"승인하시겠습니까?":"승인해제하시겠습니까?";
		if(confirm(confirmMsg)){
			let brokerNo = $(e.target).parent().parent().children().first().text();
			$.ajax({
				url:"<%=request.getContextPath()%>/admin/brokerAdmission.bb",
				type:"get",
				data:{
					brokerNo:brokerNo,
					setAdmission:setAdmission
				},
				success:data=>{
					alert(data);
					drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
				}
			})
			
		} else {
			console.log("거부");
		}
	})
	
	//유저리스트 그리는 함수
	const drawUserList = (asyncFlag, searchType, searchKeyword, cPage, numPerpage) => {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/brokerList.bb",
			type:"get",
			data:{
				searchType:searchType,
				searchKeyword:searchKeyword,
				cPage:cPage,
				numPerpage:numPerpage
			},
			//true : 비동기식, false : 동기식
			async:asyncFlag,
			success:data=>{	
				let brokerArray = data[0];
				let userIdArray = data[1];
				let pageBar = data[2];
				
				$("table#brokerTable").html("");
				let trHead = $("<tr>");
				//trHead.append($("<th>").text('전체선택'));
				trHead.append($("<th>").text('중개사번호'));
				trHead.append($("<th>").text('회원번호'));
				trHead.append($("<th>").text('아이디'));
				trHead.append($("<th>").text('중개등록번호'));
				trHead.append($("<th>").text('상호명'));
				trHead.append($("<th>").text('업체주소'));
				trHead.append($("<th>").text('대표번호'));
				trHead.append($("<th>").text('매물등록갯수'));
				trHead.append($("<th>").text('신고누적횟수'));
				trHead.append($("<th>").text('이용제한기한'));
				trHead.append($("<th>").text('등록일자'));
				trHead.append($("<th>").text('수정일자'));
				trHead.append($("<th>").text('승인상태'));
				trHead.append($("<th>").text('승인변경'));
				$("table#brokerTable").append(trHead);
				
				for(let i = 0; i<brokerArray.length; i++){
					let tr = $("<tr>");
					tr.append($("<td>").text(brokerArray[i].brokerNo));
					tr.append($("<td>").html($("<a>").attr("href","javascript:void(0)").attr("class","userNoTag").text(brokerArray[i].userNo)));
					tr.append($("<td>").text(userIdArray[i]));
					tr.append($("<td>").text(brokerArray[i].registrationNo));
					tr.append($("<td>").text(brokerArray[i].officeName));
					tr.append($("<td>").text(brokerArray[i].officeAddress));
					tr.append($("<td>").text(brokerArray[i].telephone));
					tr.append($("<td>").text(brokerArray[i].propertyCount));
					tr.append($("<td>").text(brokerArray[i].reportCount!=null?brokerArray[i].reportCount:"0"));
					tr.append($("<td>").text(brokerArray[i].restrictionDate!=null?brokerArray[i].restrictionDate:"-"));
					tr.append($("<td>").text(brokerArray[i].enrollDate));
					tr.append($("<td>").text(brokerArray[i].editDate!=null?brokerArray[i].editDate:"-"));
					tr.append($("<td>").text(brokerArray[i].admissionState=='Y'?"승인완료":"승인대기"));
					if(brokerArray[i].admissionState=='N'){
						let admissionBtnToY = $("<button>").attr("class","admissionBtn").attr("id","admissionBtnToY").text("승인하기");
						tr.append($("<td>").append(admissionBtnToY));
					} else if(brokerArray[i].admissionState=='Y'){
						let admissionBtnToN = $("<button>").attr("class","admissionBtn").attr("id","admissionBtnToN").text("승인해제");
						tr.append($("<td>").append(admissionBtnToN));
					}
					$("table#brokerTable").append(tr);
				}
				$("div#pageBar").html("");
				$("div#pageBar").html(pageBar);
			}
		});		
	}
	
	
	
	
</script>







</body>
</html>