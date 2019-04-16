package com.toss.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;

import com.toss.db.ConnectionManager;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class ServerMain extends JFrame {
	String TAG = this.getClass().getName();
	int port = 7777;
	Thread thread;
	ServerSocket server;
	ServerDispatcher dispatcher;
	Socket socket;
	ServerMessageThread messageThread;
	int count = 0;
	String ip;
	Vector<ServerMessageThread> list = new Vector();
	ArrayList<String> ip_list = new ArrayList();

	// DB연결
	private Connection con;
	ConnectionManager connectionManager;

	public ServerMain() {
		connectionManager = new ConnectionManager();
		con = connectionManager.connect();
		// this.main = main;
		System.out.println(TAG + " 서버 생성!!");
		dispatcher = new ServerDispatcher(this);
		thread = new Thread() {
			@Override
			public void run() {
				runServer();
			}
		};
		thread.start();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				connectionManager.closeDB(con);
				System.exit(0);
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("서버 가동중...");
		lblNewLabel.setFont(new Font("MD개성체", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		setSize(300, 100);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void runServer() {
		System.out.println(TAG + " runserver!!");
		try {
			server = new ServerSocket(port); // 서버 생성
			while (true) {
				Socket client = server.accept(); // 서버 가동( 접속자를 기다림 )

				ip = client.getInetAddress().getHostAddress();
				System.out.println(TAG + " " + ip + "님 접속!!");
				ServerMessageThread st = new ServerMessageThread(this, client, ip);
				st.start();
				list.add(st);
				ip_list.add(ip);
				sendIp("sendip", ip);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// getter!! -> 남이 내꺼를 가져가게 해줌
	public Connection getCon() {
		return con;
	}

	public void sendIp(String requestType, String ip) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"ip\":\"" + ip + "\"");
		sb.append("}"); // messageThread.send(sb.toString());

		for (int i = 0; i < list.size(); i++) {
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}

	public void sendCheckIdBack(String requestType, String ip, String idcheck,String member_id) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"idcheck\":\"" + idcheck + "\",");
		sb.append("   \"member_id\":\"" + member_id + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	
	public void sendLoginBack(String requestType, String ip, String check,String member_id,String member_pw) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"check\":\"" + check + "\",");
		sb.append("   \"member_id\":\"" + member_id + "\",");
		sb.append("   \"member_pw\":\"" + member_pw + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	public void sendLoginBack(String requestType, String ip, String check,String member_id,String member_pw,String member_pri) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"check\":\"" + check + "\",");
		sb.append("   \"member_id\":\"" + member_id + "\",");
		sb.append("   \"member_pw\":\"" + member_pw + "\",");
		sb.append("   \"member_pri\":\"" + member_pri + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	
	public void sendCheckAccountBack(String requestType, String ip, String check,String account_num) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"check\":\"" + check + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
		
	}

	public void sendGetAccountBack(String requestType, String ip, String bank_name,String account_num,String account_balance,String total) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"bank_name\":\"" + bank_name + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"account_balance\":\"" + account_balance + "\",");
		sb.append("   \"total\":\"" + total + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
		
	}
	
	public void sendCreateAccountBack(String requestType, String ip,String bank_name,String account_num,String account_balance) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"bank_name\":\"" + bank_name + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"account_balance\":\"" + account_balance + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
		
	}
	
	
	public void sendDepositBack(String requestType, String ip,String bank_name,String account_num,String account_balance,String index) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"bank_name\":\"" + bank_name + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"account_balance\":\"" + account_balance + "\",");
		sb.append("   \"index\":\"" + index + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
		
	}
	
	public void sendWithdrawBack(String requestType, String ip,String bank_name,String account_num,String account_balance,String index) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"bank_name\":\"" + bank_name + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"account_balance\":\"" + account_balance + "\",");
		sb.append("   \"index\":\"" + index + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
		
	}
	
	public void sendCheckSendMoneyBack(String requestType, String ip,String check) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"check\":\"" + check + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	
	//"sendreceivemoneyback",ip,bank_name_receiver,account_num_receiver,Integer.toString(sum)
	public void sendReceiveMoneyBack(String requestType, String ip,String bank_name,String account_num,String account_balance) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"bank_name\":\"" + bank_name + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"account_balance\":\"" + account_balance + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
				list.get(i).send(sb.toString());
		}
	}
	//serverMain.sendAccountHistoryBack("accounthistoryback",ip,account_num,type_name,receive_name,amount,left_balance,history_date,total);
	public void sendAccountHistoryBack(String requestType, String ip,String account_num,String type_name,String receive_name,String amount,String left_balance,String history_date,String total) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("   \"requestType\":\"" + requestType + "\",");
		sb.append("   \"account_num\":\"" + account_num + "\",");
		sb.append("   \"type_name\":\"" + type_name + "\",");
		sb.append("   \"receive_name\":\"" + receive_name + "\",");
		sb.append("   \"amount\":\"" + amount + "\",");
		sb.append("   \"left_balance\":\"" + left_balance + "\",");
		sb.append("   \"history_date\":\"" + history_date + "\",");
		sb.append("   \"total\":\"" + total + "\"");
		sb.append("}"); //
		//messageThread.send(sb.toString());
		
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("ip_list에 들어가있는 ip" + ip_list.get(i));
			if (ip_list.get(i).equals(ip)) {
				list.get(i).send(sb.toString());
			}
		}
	}
	

	public static void main(String[] args) {
		new ServerMain();
	}
}
