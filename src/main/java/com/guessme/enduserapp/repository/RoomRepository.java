package com.guessme.enduserapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.guessme.enduserapp.models.Room;
import com.guessme.enduserapp.queries.RoomQueries; 


public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	@Query(value = RoomQueries.GET_ROOM_BY_USERID)
	public List<Room> getRoomByUserId(Integer userId);

	@Query(value = RoomQueries.GET_ALL_MY_ROOM)
	public List<Room> getElgibleRooms(Integer userId);

}
