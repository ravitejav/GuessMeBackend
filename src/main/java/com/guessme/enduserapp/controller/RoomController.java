package com.guessme.enduserapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guessme.enduserapp.models.Room;
import com.guessme.enduserapp.service.RoomService;


@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	@RequestMapping(value = "/saveRoom", method = RequestMethod.POST)
	public Room createRoom(@RequestBody Room room) {
		return roomService.saveRoom(room);
	}
	
	@RequestMapping(value = "/elgibleRooms/{userId}", method = RequestMethod.GET)
	public List<Room> getAvailableRooms(@PathVariable String userId) {
		return roomService.getElgibleRoom(Integer.parseInt(userId));
	}
	
	@RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
	public Room getRoomDataById(@PathVariable String roomId) {
		return roomService.getRoomById(Integer.parseInt(roomId));
	}
	
	@RequestMapping(value = "/getRoomByUser/{userId}", method = RequestMethod.GET)
	public List<Room> allRoomByUser(@PathVariable String userId) {
		return roomService.getAllRoomByUser(Integer.parseInt(userId));
	}

}
