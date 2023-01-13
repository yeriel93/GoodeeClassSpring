<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅창</title>

<script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.1.min.js"></script>

</head>
<body>
	<h3>spring chatting</h3>
	<div id="chattingcontainer"></div>
	<input id="msg">
	<button id="sendBtn">전송</button>
	
	<script>
		const websocket = new WebSocket("ws://localhost:9090/spring/chatting");
		
		websocket.onopen=(data)=>{
			console.log(data);
			websocket.send(JSON.stringify(new Message("open",'${loginMember.userId}',"","","","")));
		}
		
		class Message{
			constructor(type,sender,reciever, msg, room){
				this.type = type;
				this.sender = sender;
				this.reciever = reciever;
				this.msg = msg;
				this.room = room;
			}
			
		}
	</script>
		
</body>
</html>