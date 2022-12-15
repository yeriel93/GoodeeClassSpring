<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/adminHeader.jsp" %>
<link href="<%=request.getContextPath()%>/css/admin/adminUserStyle.css" type="text/css" rel="stylesheet">
<section>
		<div id="listContainer">
			<h2>회원관리</h2>
			<div id="search-container">
	        	검색타입 : 
	        	<select id="searchType">
	        		<option value="userId" >아이디</option>
	        		<option value="userName" >회원이름</option>
	        		<option value="userLevel" >회원등급</option>
	        	</select>
	        	<div id="search-userId">
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 아이디를 입력하세요" >
        			<input type="hidden" name="searchType" value="ID">
        			<button class="searchBtn">검색</button>
	        	</div>
	        	<div id="search-userName">
        			<input type="text" name="searchKeyword" size="25" 
        			placeholder="검색할 이름을 입력하세요">
        			<input type="hidden" name="searchType" value="NAME">
        			<button class="searchBtn">검색</button>
	        	</div>
	        	<div id="search-userLevel">
        			<label><input type="radio" name="searchKeyword" value="A">관리자</label>
        			<label><input type="radio" name="searchKeyword" value="B">중개사</label>
        			<label><input type="radio" name="searchKeyword" value="C">일반회원</label>
        			<input type="hidden" name="searchType" value="USER_LEVEL">
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
			<div id="userContainer" class = "tableContainer">
				<table id="userTable">
				
				</table>
			</div>
			<div id="pageBarContainer">
				<div id=pageBar></div>
			</div>
		</div>
	</section>
</body>
</html>

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
		$("div#search-userLevel>label>input[name=searchKeyword]").first().prop("checked",true);
		
	});
	
	//페이지당 회원수를 변경했을 때
	$("select#numPerpage").change(e=>{
		numPerpage = $(e.target).val();
		drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
	});
	
	//검색 버튼을 눌렀을 때
	$("button.searchBtn").click(e=>{
		searchState = $(e.target).prev().val();
		if(searchState == 'ID' || searchState == 'NAME') {
			searchKeyword = $(e.target).prev().prev().val();
		} else if(searchState == "USER_LEVEL") {
			searchKeyword = $("div#search-userLevel input[name=searchKeyword]:checked").val();
		} else {
			searchKeyword="";
		}
		cPage = 1;
		drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
	});
	
	//페이지바 클릭했을 때
	$(document).on("click","a",(e)=>{
		console.log(!isNaN(Number($(e.target).text().trim())));
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
	})
		
	
	//유저리스트 그리는 함수
	const drawUserList = (asyncFlag, searchType, searchKeyword, cPage, numPerpage) => {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/userList.bb",
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
				let userArray = data[0];
				let brokerNoArray = data[1];
				let pageBar = data[2];
				
				$("table#userTable").html("");
				let trHead = $("<tr>");
				trHead.append($("<th>").text('회원번호'));
				trHead.append($("<th>").text('아이디'));
				trHead.append($("<th>").text('회원등급'));
				trHead.append($("<th>").text('중개사번호'));
				trHead.append($("<th>").text('이름'));
				trHead.append($("<th>").text('이메일'));
				trHead.append($("<th>").text('휴대폰번호'));
				trHead.append($("<th>").text('생년월일'));
				trHead.append($("<th>").text('등록일자'));
				trHead.append($("<th>").text('수정일자'));
				$("table#userTable").append(trHead);
				
				for(let i = 0; i<userArray.length; i++){
					let tr = $("<tr>");
					tr.append($("<td>").text(userArray[i].userNo));
					tr.append($("<td>").text(userArray[i].id));
					tr.append($("<td>").text(userArray[i].userLevel=='A'?'관리자':(userArray[i].userLevel=='B'?'중개사':(userArray[i].userLevel=='C'?'일반회원':''))));
					tr.append($("<td>").text(brokerNoArray[i]!='0'?brokerNoArray[i]:'-'));
					tr.append($("<td>").text(userArray[i].name));
					tr.append($("<td>").text(userArray[i].email));
					tr.append($("<td>").text(userArray[i].phone));
					tr.append($("<td>").text(userArray[i].birthday));
					tr.append($("<td>").text(userArray[i].enrollDate));
					tr.append($("<td>").text(userArray[i].editDate!=null?userArray[i].editDate:'-'));
					$("table#userTable").append(tr);
				}
				$("div#pageBar").html("");
				$("div#pageBar").html(pageBar);
			}
		});		
	}
</script>






























