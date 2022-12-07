package com.larturi.users.models;

public class User {
	
	private String username;
	private String password;
	private String nick;
	
	public User() {}
	
	public User(String username, String password, String nick) {
		this.username = username;
		this.password = password;
		this.nick = nick;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNick() {
		return nick;
	}
	
	public void setNick(String nick) {
		this.nick = nick;
	}


}