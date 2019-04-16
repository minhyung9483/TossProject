package com.toss.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientMessageThread extends Thread{
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	boolean flag = true;
	
	public ClientMessageThread(ClientMain clientMain ,ClientServer clientServer, Socket socket) {
		this.clientMain=clientMain;
		this.clientServer = clientServer;
		this.socket = socket;
		try {
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listen() {
		String msg = null;
		try {
			msg = buffr.readLine();
			clientServer.dispatcher.dispatch(msg);
			//채팅방.area.append(msg+"\n"); #####채팅?
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(flag) {
			listen();
		}
	}
}
