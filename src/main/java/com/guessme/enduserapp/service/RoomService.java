package com.guessme.enduserapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.guessme.enduserapp.models.Room;
import com.guessme.enduserapp.repository.RoomRepository;




@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	public List<Room> getAllRoomByUser(Integer userId) {
		return roomRepository.getRoomByUserId(userId);
	}
	
	public Room saveRoom(Room room) {
		return roomRepository.save(room);
	}
	
	public Room getRoomById(Integer roomid) {
		Optional<Room> room =  roomRepository.findById(roomid);
		if(room.isPresent()) {
			return room.get();
		}
		return null;
	}
	
	public List<Room> getElgibleRoom(Integer userId) {
		return roomRepository.getElgibleRooms(userId);
	}

}
