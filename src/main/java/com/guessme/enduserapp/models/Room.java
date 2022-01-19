package com.guessme.enduserapp.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Room {
	
	@Id
	@Column(name = "roomid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roomId;
	
	private Date lastUpdate;
	
	private Date createdDate;
	
	private String roomname;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private User createdBy;

	@ManyToMany
	@JoinColumn(name = "roomid")
	private List<User> users;
	
	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	
	public Room() {
		
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@PrePersist
	public void setCreatedDate() {
		this.createdDate = new Date();
		this.lastUpdate = new Date();
	}
	
	@PreUpdate
	public void updateModifiedDate() {
		this.lastUpdate = new Date();
	}
	

}
