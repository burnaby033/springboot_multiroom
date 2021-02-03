package com.dms.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatRoom {
     String id;
     Set<WebSocketSession> sessions = new HashSet<>();

    public ChatRoom(String room_id) { 
    	this.id = room_id;
	}
   
    public void handleMessage(WebSocketSession session, ChatMessage chatMessage, ObjectMapper objectMapper) throws JsonProcessingException {
        if (chatMessage.getType().equals("JOIN")) 
            join(session); 
        else
        	send(chatMessage, objectMapper);
    }

    private void join(WebSocketSession session) {
        sessions.add(session);
    }
    
    private <T> void send(T messageObject, ObjectMapper objectMapper) throws JsonProcessingException {
        TextMessage message = new TextMessage(objectMapper.writeValueAsString(messageObject));
  
        sessions.parallelStream().forEach(session -> {
			try {
				session.sendMessage(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
    }
    
	public void remove(WebSocketSession target) {
	  String targetId = target.getId();
	  sessions.removeIf(session -> session.getId().equals(targetId));
	 }

	public String getId() {
		return id;
	}

	public Set<WebSocketSession> getSessions() {
		return sessions;
	}
 
}
