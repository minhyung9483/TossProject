package com.toss.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import com.toss.client.ClientMain;

public class ClientServer {
	String TAG = this.getClass().getName();
	Socket socket;
	int port = 7777;
	ClientMessageThread messageThread;
	String getSql;
	ClientDispatcher dispatcher;
	ClientMain clientMain;

	public ClientServer(ClientMain clientMain) {
		this.clientMain = clientMain;
		dispatcher = new ClientDispatcher(clientMain, this);
		connect();
	}

	public void connect() {
		String ip = "192.168.0.14"; // #######나중에 관리자용 컴퓨터 ip 받아오기( 선생님 컴퓨터 )
		try {
			socket = new Socket(ip, port);

			// 위아래 자리 바꿔야할수도

			messageThread = new ClientMessageThread(clientMain, this, socket);
			messageThread.start();
			System.out.println(TAG + " client 연결 성공!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendEntry(String requestType, String user_list_id, String gender_type_id, String entry_list_total) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"user_list_id\":\"" + user_list_id + "\",");
		sb.append("   \"gender_type_id\":\"" + gender_type_id + "\",");
		sb.append(" \"entry_list_total\":\"" + entry_list_total + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

	public void sendCheckId(String requestType, String ip, String member_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"member_id\":\"" + member_id + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

	public void sendJoin(String requestType, String ip, String member_id, String member_pw, String member_name) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"member_id\":\"" + member_id + "\",");
		sb.append("   \"member_pw\":\"" + member_pw + "\",");
		sb.append("   \"member_name\":\"" + member_name + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

	public void sendLogin(String requestType, String ip, String member_id, String member_pw) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"member_id\":\"" + member_id + "\",");
		sb.append("   \"member_pw\":\"" + member_pw + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

	public void sendCheckAccount(String requestType, String ip, String account_num) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

	public void sendCreateAccount(String requestType, String ip, String account_num, String bank, String member_pri) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"bank\":\"" + bank + "\",");
		sb.append("   \"member_pri\":\"" + member_pri + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

	public void sendGetAccount(String requestType, String ip, String member_pri) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"member_pri\":\"" + member_pri + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

	public void sendDeposit(String requestType, String ip, String bank_name, String account_num,String account_balance, String deposit_money, String member_pri,String index) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"bank_name\":\"" + bank_name + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"account_balance\":\"" + account_balance + "\",");
		sb.append("   \"deposit_money\":\"" + deposit_money + "\",");
		sb.append("   \"member_pri\":\"" + member_pri + "\",");
		sb.append("   \"index\":\"" + index + "\"");
		sb.append("}");
		messageThread.send(sb.toString());

	}
	
	public void sendWithdraw(String requestType, String ip, String bank_name, String account_num,String account_balance, String withdraw_money, String member_pri,String index) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"bank_name\":\"" + bank_name + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"account_balance\":\"" + account_balance + "\",");
		sb.append("   \"withdraw_money\":\"" + withdraw_money + "\",");
		sb.append("   \"member_pri\":\"" + member_pri + "\",");
		sb.append("   \"index\":\"" + index + "\"");
		sb.append("}");
		messageThread.send(sb.toString());

	}
	
	//타입v,아아피v,내은행이름v,내계좌번호v,내잔액v,보낼금액v,내회원번호v,내계좌인덱스v,상대은행pri,상대계좌번호
	public void sendMoney(String requestType, String ip, String bank_name, String account_num,String account_balance, String amount, String member_pri,String index,String bank_pri_receiver,String account_num_receiver) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"bank_name\":\"" + bank_name + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"account_balance\":\"" + account_balance + "\",");
		sb.append("   \"amount\":\"" + amount + "\",");
		sb.append("   \"member_pri\":\"" + member_pri + "\",");
		sb.append("   \"index\":\"" + index + "\",");
		sb.append("   \"bank_pri_receiver\":\"" + bank_pri_receiver + "\",");
		sb.append("   \"account_num_receiver\":\"" + account_num_receiver + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
		
	}
	
	public void sendAccountHistory(String requestType, String ip,String account_num) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\"");
		sb.append("}");
		messageThread.send(sb.toString());
	}

}
