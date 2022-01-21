package com.guessme.enduserapp.queries;

public final class RoomQueries {
	
	public static final String GET_ROOM_BY_USERID = "SELECT * FROM Room as r WHERE r.userid=?1";
	public static final String GET_ALL_MY_ROOM = "SELECT r.roomid FROM Room as r, room_users as ru where ru.room_roomid=r.roomid AND ru.users_userid=?1";

}
