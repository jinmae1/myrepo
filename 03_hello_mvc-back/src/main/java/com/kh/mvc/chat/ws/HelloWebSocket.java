package com.kh.mvc.chat.ws;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/chat/websocket", configurator = HelloWebSocketConfig.class)
public class HelloWebSocket {
	
	public static Map<String, Session> clients = new HashMap<>();
	
	public void clientsLog() {
		System.out.println("현재 접속자 수(" + clients.size() + ") + " + clients.keySet());
	}
	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		System.out.println("[open] ");
		Map<String, Object> userProp = config.getUserProperties();
		String memberId = (String) userProp.get("memberId");
		clients.put(memberId, session);
		clientsLog();
		
		Map<String, Object> msg = new HashMap<>();
		msg.put("type", "welcome");
		msg.put("msg", "님이 입장했습니다.");
		msg.put("sender", memberId);
		msg.put("time", System.currentTimeMillis());
	}
	@OnMessage
	public void onMessage(String msg, Session session) {
		System.out.println("[message] ");
	}
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	@OnClose
	public void onClose(Session session) {
		System.out.println("[close] ");
	}

}
