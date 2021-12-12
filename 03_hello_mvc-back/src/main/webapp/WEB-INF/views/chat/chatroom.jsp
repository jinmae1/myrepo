<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>  
<script>
const host = location.host;
const ws = new WebSocket(`ws://\${host}<%= request.getContextPath() %>/chat/websocket`);
ws.onopen = (e) => {
	console.log("open", e);
};
ws.onmessage = (e) => {
	console.log("message", e);
	const {data} = e;
	console.log("data", data);
};
ws.onerror = (e) => {
	console.log("error", e);
};
ws.onclose = (e) => {
	console.log("close", e);
};
</script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/chat.css" />
	<section id="chat-container">	
		<h2>채팅</h2>
		<button type="button" id="btn-userList">현재사용자확인</button>
		<div id="msg-container">
			<ul></ul>
		</div>
		
		<div id="msg-editor" class="editor">
			<textarea name="" id="msg" cols="30" rows="2"></textarea>
			<button type="button" id="send">Send</button>
		</div>
		
		<hr style="margin:20px 0" />

		<!-- dm관련 섹션 -->		
		<div id="dm-container" >
			<label for="dm-client">DM</label>
			<select class="custom-select" id="dm-client">
				<option value="" disabled selected>접속자 목록</option>
			</select>
		</div>
		<div id="dm-editor" class="editor">
			<textarea id="dm-msg" cols="30" rows="2"></textarea>
			<button type="button" id="dm-send">Send</button>
		</div>
	</section>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>  
