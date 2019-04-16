package com.toss.client;

public class ClientMain {
	ClientServer clientServer;
	ClientInfo clientInfo;
	Login login;
	String TAG = this.getClass().getName();
	
	public ClientMain() {
		clientInfo=new ClientInfo(this);
		clientServer=new ClientServer(this);
		login=new Login(this,clientServer);
	}
	
	
	
	public static void main(String[] args) {
		new ClientMain();
	}
	
}
