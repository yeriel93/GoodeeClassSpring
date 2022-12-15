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
        			<label><input type="radio" name="searchKeyword" value="B">일반회원</label>
        			<label><input type="radio" name="searchKeyword" value="C">중개사</label>
        			<input type="hidden" name="searchType" value="USER_LEVEL">
        			<button class="searchBtn">검색</button>
	        	</div>
	        	<input type="hidden" name="searchType" value="clear">
	        	<button  class="searchBtn">초기화</button>
	        </div>
	        <div id="numPerpage-container">
	        	페이지당 회원수 : 
	       		<select name="numPerpage" id="numPerpage">
	       			<option value="40">40</option>
	       			<option value="20">20</option>
	       			<option value="15">15</option>
	       			<option value="10">10</option>
	       			<option value="5">5</option>
	       		</select>	
        	</div>
			<div id="userContainer" class = "tableContainer">
				<table id="userTable">
					<tr>
						<th>회원번호</th>
						<th>중개사번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>이메일</th>
						<th>휴대폰번호</th>
						<th>생년월일</th>
						<th>회원등급</th>
						<th>등록일자</th>
						<th>수정일자</th>
					</tr>
				</table>
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
		numPerpage = "40";
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
		drawUserList(searchState, searchKeyword, cPage, numPerpage);
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
		drawUserList(searchState, searchKeyword, cPage, numPerpage);
	});
		
	
	const drawUserList = (searchType, searchKeyword, cPage, numPerpage) => {
		$.ajax({
			url:"<%=request.getContextPath()%>/admin/userList.bb",
			type:"get",
			data:{
				searchType:searchType,
				searchKeyword:searchKeyword,
				cPage:cPage,
				numPerpage:numPerpage
			},
			success:data=>{
				console.log("다녀왔읍니다");
			}
		});		
	}
	
	
</script>






























