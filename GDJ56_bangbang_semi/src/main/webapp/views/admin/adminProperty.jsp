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
			$("div#search-userNo").children().first().val("<%=searchKeyword%>");
		<%}%>
		cPage = "1";
		numPerpage = "15";
		
		//drawUserList(true, searchState, searchKeyword, cPage, numPerpage);
	});
	
	//검색타입을 변경했을 때
	$("select#searchType").change(e=>{
		const type = $(e.target).val();
		$("div#search-container>div").hide();
		$("div#search-"+type).css("display","inline-block");
		$("input[name=searchKeyword][type=text]").val("");
		$("div#search-userLevel>label>input[name=searchKeyword]").first().prop("checked",true);
		
	});
	
	
</script>
















































</body>
</html>