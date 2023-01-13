<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<%@ include file="/views/common/adminHeader.jsp" %>
<link href="<%=request.getContextPath()%>/css/admin/adminPropertyStyle.css?ver=1" type="text/css" rel="stylesheet">
<section>
	<div id="listContainer">
		<h2>매물관리</h2>
		<div id="search-container">
        	검색타입 : 
        	<select id="searchType">
        		<option value="propertyNo">매물번호</option>
        		<option value="brokerNo">중개인번호</option>
        		<option value="hiding">숨김여부</option>
        	</select>
        	
        	<div id="search-propertyNo">
       			<input type="text" name="searchKeyword" size="30" 
       			placeholder="검색할 매물번호를 입력하세요" >
       			<input type="hidden" name="searchType" value="PROPERTY_NO">
       			<button class="searchBtn">검색</button>
        	</div>
        	<div id="search-brokerNo">
       			<input type="text" name="searchKeyword" size="30" 
       			placeholder="검색할 중개사번호를 입력하세요">
       			<input type="hidden" name="searchType" value="BROKER_NO">
       			<button class="searchBtn">검색</button>
        	</div>
        	<div id="search-hiding">
       			<label><input type="radio" name="searchKeyword" value="N">공개</label> 
       			<label><input type="radio" name="searchKeyword" value="Y">숨김</label>
       			<input type="hidden" name="searchType" value="HIDING">
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
		<div id="propertyContainer" class = "tableContainer">
			<table id="propertyTable">
			
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
			$("div#search-propertyNo").children().first().val("<%=searchKeyword%>");
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
		$("div#search-hiding>label>input[name=searchKeyword]").first().prop("checked",true);
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
		if(searchState == 'PROPERTY_NO' || searchState == 'BROKER_NO') {
			searchKeyword = $(e.target).prev().prev().val();
		} else if(searchState == "HIDING") {
			searchKeyword = $("div#search-hiding input[name=searchKeyword]:checked").val();
		} else {
			searchKeyword="";
		}
		cPage = 1;
		console.log(searchState,searchKeyword);
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
	
	//매물 번호 클릭했을 때
	$(document).on("click","a.propertyNoTag",(e)=>{
		let url = "<%=request.getContextPath()%>/property/propertyInfo.bb?propertyNo=" + $(e.target).text();
		window.open(url);
	});
	
	//중개사 번호 클릭했을 때
	$(document).on("click","a.brokerNoTag",(e)=>{
		let url = "<%=request.getContextPath()%>/admin/broker.bb?searchType=BROKER_NO&searchKeyword=" + $(e.target).text();
		window.open(url);
	});
	
	//숨김 or 공개 버튼을 눌렀을 때
	$(document).on("click","button.hidingBtn",e=>{
		let setHiding = $(e.target).attr("id")=="hidingBtnToY"?'Y':'N';
		let confirmMsg = setHiding=='Y'?"숨김상태로 전환하시겠습니까?":"공개상태로 전환하시겠습니까?";
		if(confirm(confirmMsg)){
			let propertyNo = $(e.target).parent().parent().children().first().text();
			$.ajax({
				url:"<%=request.getContextPath()%>/admin/propertyHiding.bb",
				type:"get",
				data:{
					propertyNo:propertyNo,
					setHiding:setHiding
				},
				success:data=>{
					alert(data);
					drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
				}
			});
		}
	});
	
	//매물리스트 그리는 함수
	const drawUserList = (asyncFlag, searchType, searchKeyword, cPage, numPerpage) => {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/propertyList.bb",
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
				//console.log(data);
				let propertyArray = data[0];
				let propertyReportCount = data[1];
				let pageBar = data[2];
				//console.log(propertyArray, propertyReportCount, pageBar);
				
				$("table#propertyTable").html("");
				let trHead = $("<tr>");
				trHead.append($("<th>").text('매물번호'));
				trHead.append($("<th>").text('중개인번호'));
				trHead.append($("<th>").text('주소'));
				trHead.append($("<th>").text('등록일자'));
				trHead.append($("<th>").text('수정일자'));
				trHead.append($("<th>").text('매물별신고누적'));
				trHead.append($("<th>").text('공개여부'));
				trHead.append($("<th>").text('숨김상태변경'));
				$("table#propertyTable").append(trHead);
				
				for(let i = 0; i<propertyArray.length; i++){
					let tr = $("<tr>");
					tr.append($("<td>").html($("<a>").attr("href","javascript:void(0)").attr("class","propertyNoTag").text(propertyArray[i].propertyNo)));
					tr.append($("<td>").html($("<a>").attr("href","javascript:void(0)").attr("class","brokerNoTag").text(propertyArray[i].brokerNo)));
					tr.append($("<td>").text(propertyArray[i].address));
					tr.append($("<td>").text(propertyArray[i].enrollDate));
					tr.append($("<td>").text(propertyArray[i].editDate!=null?propertyArray[i].editDate:"-"));
					tr.append($("<td>").text(propertyReportCount[propertyArray[i].propertyNo]!=null?propertyReportCount[propertyArray[i].propertyNo]:'0'));
					tr.append($("<td>").text(propertyArray[i].hiding=='N'?"공개":"숨김"));
					if(propertyArray[i].hiding=='N'){
						let hidingBtnToN = $("<button>").attr("class","hidingBtn").attr("id","hidingBtnToY").text("숨기기");
						tr.append($("<td>").append(hidingBtnToN));
					} else if(propertyArray[i].hiding=='Y'){
						let admissionBtnToY = $("<button>").attr("class","hidingBtn").attr("id","hidingBtnToN").text("공개하기");
						tr.append($("<td>").append(admissionBtnToY));
					} 
					$("table#propertyTable").append(tr);
				} 				
				$("div#pageBar").html("");
				$("div#pageBar").html(pageBar); 
			}
		});		
	}
	
	
	
	
</script>
















































</body>
</html>