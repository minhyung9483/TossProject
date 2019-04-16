package com.toss.client;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClientController {
	ClientMain clientMain;
	ClientServer clientServer;
	ClientDispatcher dispatcher;
	String TAG = this.getClass().getName();
	
	public ClientController(ClientMain clientMain,ClientServer clientServer,ClientDispatcher dispatcher) {
		this.clientMain = clientMain;
		this.clientServer=clientServer;
		this.dispatcher=dispatcher;
		
		// TODO Auto-generated constructor stub
	}
	
	public void setCheckIdBack(String check) {
		if(check.equals("true")) {
			JOptionPane.showMessageDialog(clientMain.login.join,"사용 가능한 ID입니다!!" );
			System.out.println(TAG+":"+check);
			System.out.println(TAG+":"+clientMain.clientInfo.getCheckId());
		}else {
			JOptionPane.showMessageDialog(clientMain.login.join,"중복된 ID입니다!!" );
			clientMain.login.join.t_id.setText("");
		}
	}
	
	public void setLoginBack(String check) {
		if(check.equals("true")) {
			clientMain.login.openHome();
			clientMain.login.home.balance.getMyAccount();
		}else {
			JOptionPane.showMessageDialog(clientMain.login,"아이디나 비밀번호를 확인해 주세요!!" );
		}
	}

	
	
	/*
	//유저 정보 세팅
	public void setUserInfo(int table_info_id,String table_ip,int entry_list_id) {
		System.out.println(TAG+"userinfo생성\n");
		clientMain.user.setTableNum(table_info_id);
		clientMain.user.setTableIp(table_ip);
		clientMain.user.setEntryListId(entry_list_id);
		System.out.println("컨트롤러에서"+table_info_id);
		System.out.println("컨트롤러에서"+table_ip);
		
	}
	
	public void setCheckIdBack(String check) {
		if(check.equals("true")) {
			JOptionPane.showMessageDialog(clientMain,"사용 가능한 ID입니다!!" );
			clientMain.joinMain.check=true;
		}else {
			JOptionPane.showMessageDialog(clientMain,"중복된 ID입니다!!" );
		}
	}
	public void setCheckLoginBack(String check) {
		if(check.equals("true")) {
			JOptionPane.showMessageDialog(clientMain,"로그인 성공" );
			
			clientMain.loginMain.registMain = new RegistMain(clientMain,clientMain.user);
		}else {
			JOptionPane.showMessageDialog(clientMain,"로그인 실패" );
		}
	}
	
	
	public void setAskGame(String ask) {
		if(JOptionPane.showConfirmDialog(clientMain, "게임에 참여하시겠습니까??")==JOptionPane.OK_OPTION) {
			String table_ip=clientMain.user.getTableIp();
			clientServer.sendPlayGame("playgame",table_ip);
		}
		
			
		
		
		
	}*/
}
