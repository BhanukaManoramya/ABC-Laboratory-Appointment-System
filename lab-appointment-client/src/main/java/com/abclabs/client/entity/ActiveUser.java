package com.abclabs.client.entity;

public class ActiveUser {
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "ActiveUser [username=" + username + "]";
	}
	
}
