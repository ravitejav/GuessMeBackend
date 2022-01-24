package com.guessme.enduserapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class RoomUpdatesHandler {
	
	@Autowired
	MessageSendingOperations<String> messageSendingOperations;
	
	@MessageMapping("/room/{roomId}/imageUpdates")
	public void roomImageUpdates(@DestinationVariable String roomId, @Payload String payloadRec) {
		messageSendingOperations.convertAndSend("/topic/room/" + roomId + "/imageUpdates", payloadRec);
	}
	
	@MessageMapping("/room/{roomId}/messageUpdates")
	public void roomMessageUpdates(@DestinationVariable String roomId, @Payload String payloadRec) {
		messageSendingOperations.convertAndSend("/topic/room/" + roomId + "/messageUpdates", payloadRec);
	}
	
	@MessageMapping("/room/{roomId}/scoreUpdates")
	public void roomScoreUpdates(@DestinationVariable String roomId, @Payload String payloadRec) {
		messageSendingOperations.convertAndSend("/topic/room/" + roomId + "/scoreUpdates", payloadRec);
	}

}
