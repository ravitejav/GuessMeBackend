package com.guessme.enduserapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.guessme.enduserapp.models.Room;
import com.guessme.enduserapp.models.User;
import com.guessme.enduserapp.repository.RoomRepository;




@Service
public class RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	public List<Room> getAllRoomByUser(Integer userId) {
		return roomRepository.getRoomByUserId(userId);
	}
	
	public Room saveRoom(Room room) {
		System.out.println(room.getCreatedBy().getUserId());
		return roomRepository.save(room);
	}
	
	public Room getRoomById(Integer roomid) {
		Optional<Room> room =  roomRepository.findById(roomid);
		if(room.isPresent()) {
			Room fetchedRoom = room.get();
			List<User> users = new ArrayList<User>();
			fetchedRoom.getUsers().stream().forEach((User user) -> {
				user.setPassword("");
				users.add(user);
			});
			fetchedRoom.setUsers(users);
			return fetchedRoom;
		}
		return new Room();
	}
	
	public List<Room> getElgibleRoom(Integer userId) {
		List<Integer> roomIds = roomRepository.getElgibleRooms(userId);
		List<Room> rooms = new ArrayList<Room>();
		roomIds.stream().forEach(id -> rooms.add(roomRepository.findById(id).get()));
		return rooms;
	}
	

}
