package com.dms.chat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.socket.WebSocketSession;

public class ChatRoomRepository {

    Map<String, ChatRoom> chatRoomMap = new HashMap<String, ChatRoom>(); 
    /*home 컨트롤러에서 모델로 사용하기위해서 public static을 붙였습니다.*/
    public static Collection<ChatRoom> chatRooms;
    
    public ChatRoomRepository() {
	    for(int i=0;i<2;i++) {  
	    	String uuid = UUID.randomUUID().toString();
	    	ChatRoom chatRoom = new ChatRoom(uuid);
	    	chatRoomMap.put(chatRoom.getId(), chatRoom);
	    	System.out.println("chatRoom 클래스를 복제하고 있습니다.");
	    	System.out.println("chatRoom -> "+chatRoom);
  		 }        	
	     chatRooms = chatRoomMap.values();
    }
        
	public ChatRoom getChatRoom(String id) {
        return chatRoomMap.get(id);
    }
    
    public Map<String, ChatRoom> getChatRooms() {
        return chatRoomMap;
    }
    	
	public void remove(WebSocketSession session) {
		this.chatRooms.parallelStream().forEach(chatRoom -> chatRoom.remove(session));
	} 
	
}
