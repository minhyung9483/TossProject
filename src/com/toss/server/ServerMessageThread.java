package com.toss.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerMessageThread extends Thread{
	String TAG = this.getClass().getName();
	ServerMain serverMain;
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	boolean flag = true;
	String ip;
	
	public ServerMessageThread(ServerMain serverMain,Socket client,String ip) {
		this.serverMain = serverMain;
		this.client = client;
		this.ip=ip;
		
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void listen() {
		try {
			String msg = buffr.readLine(); // client의 말 듣기
			// 서버에 접속한 모든 아바타의 write를 호출하자!!
			for (int i = 0; i < serverMain.list.size(); i++) {
				serverMain.list.get(i).send(msg);// client에 전송!!
				//MessageThread st = serverMain.list.get(i);
				//st.send(msg);
			}
			serverMain.dispatcher.dispatch(msg);
		} catch (IOException e) {
			System.out.println(TAG+":"+ip+" 클라이언트가 나갔습니다.");
			flag = false;
			serverMain.list.remove(this);
			serverMain.ip_list.remove(ip);
			System.out.println((TAG+" 현재 "+serverMain.list.size()+"명 접속중"));
			//e.printStackTrace();
		}
	}
	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
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
