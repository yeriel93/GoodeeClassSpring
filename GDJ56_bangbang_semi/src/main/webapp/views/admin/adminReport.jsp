<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/adminHeader.jsp" %>
<link href="<%=request.getContextPath()%>/css/admin/adminReportStyle.css" type="text/css" rel="stylesheet">
<section>
	<div id="listContainer">
		<h2>신고관리</h2>
		<div id="search-container">
        	검색타입 : 
        	<select id="searchType">
        		<option value="processingDate">처리상태</option>
        		<option value="userNo">신고회원번호</option>
        		<option value="propertyNo">신고당한매물번호</option>
        		<option value="brokerNo">중개인번호</option>
        	</select>
        	<div id="search-processingDate">
       			<label><input type="radio" name="searchKeyword" value="IS NULL" checked>대기</label> 
       			<label><input type="radio" name="searchKeyword" value="IS NOT NULL">처리완료</label>
       			<input type="hidden" name="searchType" value="PROCESSING_DATE">
       			<button class="searchBtn">검색</button>
        	</div>
        	<div id="search-userNo">
       			<input type="text" name="searchKeyword" size="30" 
       			placeholder="검색할 회원번호를 입력하세요" >
       			<input type="hidden" name="searchType" value="USER_NO">
       			<button class="searchBtn">검색</button>
        	</div>
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
        	<input type="hidden" name="searchType" value="clear">
        	<button  class="searchBtn">초기화</button>
        </div>
        <div id="numPerpage-container">
        	<span id="deleteBtnContainer">
        		<button class="deleteBtn">삭제</button>
        	</span>
        	페이지당 회원수 : 
       		<select name="numPerpage" id="numPerpage">
       			<option value="15">15</option>
       			<option value="10">10</option>
       			<option value="5">5</option>
       		</select>	
       	</div>
		<div id="reportContainer" class = "tableContainer">
			<table id="reportTable">
			
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
		searchKeyword = "";
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
		$("div#search-processingDate>label>input[name=searchKeyword]").first().prop("checked",true);
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
		if(searchState == 'PROPERTY_NO' || searchState == 'BROKER_NO' || searchState == "USER_NO") {
			searchKeyword = $(e.target).prev().prev().val();
		} else if(searchState == "PROCESSING_DATE") {
			searchKeyword = $("div#search-processingDate input[name=searchKeyword]:checked").val();
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
	
	//회원 번호 클릭했을 때
	$(document).on("click","a.userNoTag",(e)=>{
		console.log($(e.target).text()); // 중개사번호
		let url = "<%=request.getContextPath()%>/admin/user.bb?searchType=USER_NO&searchKeyword=" + $(e.target).text();
		window.open(url);
	});

	//매물 번호 클릭했을 때
	$(document).on("click","a.propertyNoTag",(e)=>{
		let url = "<%=request.getContextPath()%>/admin/property.bb?searchType=PROPERTY_NO&searchKeyword=" + $(e.target).text();
		window.open(url);
	});
	
	//중개사 번호 클릭했을 때
	$(document).on("click","a.brokerNoTag",(e)=>{
		let url = "<%=request.getContextPath()%>/admin/broker.bb?searchType=BROKER_NO&searchKeyword=" + $(e.target).text();
		window.open(url);
	});
	
	//전체선택 체크박스를 클릭했을 때
	$(document).on("change","input.deleteCheckAll",(e)=>{
		if($(e.target).prop("checked")==true){
			$("input.deleteCheck").prop("checked",true);		
		} else {
			$("input.deleteCheck").prop("checked",false);	
		}
	});
	
	//처리 버튼을 클릭했을 때
	$(document).on("click","button#processingBtn", (e)=>{
		let userNo = $(e.target).parent().parent().children().eq(1).children().eq(0).text();
		let propertyNo = $(e.target).parent().parent().children().eq(2).children().eq(0).text()
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/reportProcessing.bb",
			type:"get",
			data:{
				userNo:userNo,
				propertyNo:propertyNo
			},
			success:data=>{
				drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
			}
		})
	});
	
	//삭제 버튼을 눌렀을 때
	$(document).on("click","button.deleteBtn", (e)=>{
		let deleteCheckArray = $("input.deleteCheck:checked");
		//console.log(deleteCheckArray.length);
		let userNoArray = [];
		let propertyNoArray = [];
		
		if(deleteCheckArray.length>0){
			deleteCheckArray.each((i,v)=>{
				userNoArray.push(deleteCheckArray.eq(i).parent().parent().children().eq(1).children().first().text());
				propertyNoArray.push(deleteCheckArray.eq(i).parent().parent().children().eq(2).children().first().text());	
			});
			$.ajax({
				url:"<%=request.getContextPath()%>/admin/reportDelete.bb",
				type:"get",
				traditional:true,
				data:{
					userNoArray:userNoArray,
					propertyNoArray:propertyNoArray
				},
				success:data=>{
					alert(data);
					drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
				}
			})
		}
	})

	//신고리스트 그리는 함수
	const drawUserList = (asyncFlag, searchType, searchKeyword, cPage, numPerpage) => {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/reportList.bb",
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
				let reportArray = data[0];
				let brokerNoArray = data[1];
				let propertyReportCount = data[2];
				let brokerReportCount = data[3];
				let pageBar = data[4];
				
				$("table#reportTable").html("");
				let trHead = $("<tr>");
				let deleteCheckAll = $("<input>").attr({
					"class":"deleteCheckAll",
					"type":"checkbox"
				});
				trHead.append($("<th>").append(deleteCheckAll).append($("<div>").text("전체선택").css("font-size","10px")));
				trHead.append($("<th>").html('신고<br>회원번호'));
				trHead.append($("<th>").html('신고<br>매물번호'));
				trHead.append($("<th>").html('매물당<br>신고누적'));
				trHead.append($("<th>").html('매물등록<br>중개사번호'));
				trHead.append($("<th>").html('중개사총<br>신고누적'));
				trHead.append($("<th>").text('신고내용'));
				trHead.append($("<th>").text('신고등록일'));
				trHead.append($("<th>").text('처리일자'));
				trHead.append($("<th>").text('신고처리'));
				$("table#reportTable").append(trHead);
				
				for(let i = 0; i<reportArray.length; i++){
					let tr = $("<tr>");
					let deleteCheck = $("<input>").attr({
						"class":"deleteCheck",
						"type":"checkbox"
					});
					tr.append($("<td>").append(deleteCheck));
					tr.append($("<td>").html($("<a>").attr("href","javascript:void(0)").attr("class","userNoTag").text(reportArray[i].userNo)));
					tr.append($("<td>").html($("<a>").attr("href","javascript:void(0)").attr("class","propertyNoTag").text(reportArray[i].propertyNo)));
					tr.append($("<td>").text(propertyReportCount[reportArray[i].propertyNo]!=null?propertyReportCount[reportArray[i].propertyNo]:'0'));
					tr.append($("<td>").html($("<a>").attr("href","javascript:void(0)").attr("class","brokerNoTag").text(brokerNoArray[i])));
					tr.append($("<td>").text(brokerReportCount[brokerNoArray[i]]!=null?brokerReportCount[brokerNoArray[i]]:"0"));
					tr.append($("<td>").append($("<textarea>").attr({
						"rows":"3",
						"cols":"40",
						"resize":"none",
						"readonly":"true"
					}).text(reportArray[i].content)));
					tr.append($("<td>").text(reportArray[i].reportDate));
					tr.append($("<td>").text(reportArray[i].processingDate!=null?reportArray[i].processingDate:"-"));
					if(reportArray[i].processingDate!=null){
						tr.append($("<td>").text("처리완료"));
					} else {
						let processingBtn = $("<button>").attr("id","processingBtn").text("처리하기");
						tr.append($("<td>").append(processingBtn));
					}
					$("table#reportTable").append(tr);
				}  
				$("div#pageBar").html("");
				$("div#pageBar").html(pageBar);
			}
		});		
	}
</script>





</body>
</html>