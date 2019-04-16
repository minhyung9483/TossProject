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
			JOptionPane.showMessageDialog(clientMain.login.join,"��� ������ ID�Դϴ�!!" );
			System.out.println(TAG+":"+check);
			System.out.println(TAG+":"+clientMain.clientInfo.getCheckId());
		}else {
			JOptionPane.showMessageDialog(clientMain.login.join,"�ߺ��� ID�Դϴ�!!" );
			clientMain.login.join.t_id.setText("");
		}
	}
	
	public void setLoginBack(String check) {
		if(check.equals("true")) {
			clientMain.login.openHome();
			clientMain.login.home.balance.getMyAccount();
		}else {
			JOptionPane.showMessageDialog(clientMain.login,"���̵� ��й�ȣ�� Ȯ���� �ּ���!!" );
		}
	}

	
	
	/*
	//���� ���� ����
	public void setUserInfo(int table_info_id,String table_ip,int entry_list_id) {
		System.out.println(TAG+"userinfo����\n");
		clientMain.user.setTableNum(table_info_id);
		clientMain.user.setTableIp(table_ip);
		clientMain.user.setEntryListId(entry_list_id);
		System.out.println("��Ʈ�ѷ�����"+table_info_id);
		System.out.println("��Ʈ�ѷ�����"+table_ip);
		
	}
	
	public void setCheckIdBack(String check) {
		if(check.equals("true")) {
			JOptionPane.showMessageDialog(clientMain,"��� ������ ID�Դϴ�!!" );
			clientMain.joinMain.check=true;
		}else {
			JOptionPane.showMessageDialog(clientMain,"�ߺ��� ID�Դϴ�!!" );
		}
	}
	public void setCheckLoginBack(String check) {
		if(check.equals("true")) {
			JOptionPane.showMessageDialog(clientMain,"�α��� ����" );
			
			clientMain.loginMain.registMain = new RegistMain(clientMain,clientMain.user);
		}else {
			JOptionPane.showMessageDialog(clientMain,"�α��� ����" );
		}
	}
	
	
	public void setAskGame(String ask) {
		if(JOptionPane.showConfirmDialog(clientMain, "���ӿ� �����Ͻðڽ��ϱ�??")==JOptionPane.OK_OPTION) {
			String table_ip=clientMain.user.getTableIp();
			clientServer.sendPlayGame("playgame",table_ip);
		}
		
			
		
		
		
	}*/
}
