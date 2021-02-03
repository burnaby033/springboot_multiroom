<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대화방</title>
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script>
var roomId = "${roomId}";
var sock = new SockJS("/ws/multiRoom");

/*onopen 함수는 페이지가 로드되면 자동실행됨*/
sock.onopen = function () {
    sock.send( JSON.stringify({chatRoomId: roomId, type: "JOIN"}) );
}																			
/*onmessage 함수는 메시지가 오면 자동실행됨*/	
    sock.onmessage = function (e) {
        var content = JSON.parse(e.data);
        var message = content.message;
        var type = content.type;
    	var chatLog = document.getElementById("chatLog");     
        if(type == "SEND")
    		chatLog.innerHTML = chatLog.innerHTML + "<p>" + message + "</p>";
}
    
function send(){
	var textarea = document.getElementById("textarea");
	var myMessage = textarea.value;
	sock.send( JSON.stringify({chatRoomId: roomId, type: "SEND", message: myMessage}) );
}    
</script>    
</head>
<body>
<h1>대화방</h1>
<div id="chatLog"></div>
<textarea id="textarea"></textarea>
<input type="button" value="전송" onclick="send()">
</body>
</html>