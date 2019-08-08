package com.swatch.maven;

import java.util.HashMap;

public class Users {
	private String email,pswd;
	static HashMap<String,String> user=new HashMap<String,String>();
	Users(String email,String pswd){
		this.email=email;
		this.pswd=pswd;
	}
	boolean create() {
		if(user.containsKey(email))
			return false;
		else {
		user.put(email, pswd);
		return true;
		}
	}
	String getEmail() {
		return this.email;
	}
}
