package com.toss.client;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Choice;

public class SendForm extends JFrame{
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Home home;
	Send send;
	Color dodgerblue = new Color(0, 90, 156);
	
	Choice ch_account;
	 JTextField t_account;
	JPanel p_ok,p_exit;
	 JLabel l_ok,l_exit;
	 int send_money;
	 int choose_account;
	 int bank; //상대방 은행 고르기
	  JLabel l_current_balance;
	  private Choice ch_bank;
	
	public SendForm(ClientMain clientMain,ClientServer clientServer,Home home,Send send) {
		this.clientMain = clientMain;
		this.clientServer = clientServer;
		this.home = home;
		this.send=send;
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("   송금");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		label.setBounds(0, 0, 100, 35);
		panel.add(label);
		
		t_account = new JTextField();
		t_account.setHorizontalAlignment(SwingConstants.CENTER);
		t_account.setColumns(10);
		t_account.setBounds(195, 110, 150, 30);
		panel.add(t_account);
		
		t_account.addFocusListener(new FocusAdapter() {  

		    @Override  
		    public void focusGained(FocusEvent e) {  
		        t_account.setText("");  
		        t_account.setForeground(new Color(50, 50, 50));  
		    }  

		    @Override  
		    public void focusLost(FocusEvent e) { 

		        if (t_account.getText().length() == 0) {  
		            t_account.setText("받으실 분 계좌번호 입력");  
		            t_account.setForeground(new Color(150, 150, 150));  
		        }  

		    }  
		});
		
		 p_exit = new JPanel();
		p_exit.setForeground(Color.BLACK);
		p_exit.setBackground(Color.WHITE);
		p_exit.setBounds(101, 169, 100, 35);
		panel.add(p_exit);
		p_exit.setLayout(new BorderLayout(0, 0));
		
		 l_exit = new JLabel("취소");
		l_exit.setHorizontalAlignment(SwingConstants.CENTER);
		l_exit.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		p_exit.add(l_exit, BorderLayout.CENTER);
		
		 p_ok = new JPanel();
		p_ok.setForeground(Color.BLACK);
		p_ok.setBackground(Color.WHITE);
		p_ok.setBounds(238, 169, 100, 35);
		panel.add(p_ok);
		p_ok.setLayout(new BorderLayout(0, 0));
		
		 l_ok = new JLabel("확인");
		l_ok.setHorizontalAlignment(SwingConstants.CENTER);
		l_ok.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		p_ok.add(l_ok, BorderLayout.CENTER);
		
		p_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ok();
			}

			public void mouseEntered(MouseEvent e) {
				p_ok.setBackground(dodgerblue);
				l_ok.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_ok.setBackground(Color.WHITE);
				l_ok.setForeground(Color.BLACK);
			}
		});
		p_exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				exit();
			}
			
			public void mouseEntered(MouseEvent e) {
				p_exit.setBackground(dodgerblue);
				l_exit.setForeground(Color.WHITE);
			}
			
			public void mouseExited(MouseEvent e) {
				p_exit.setBackground(Color.WHITE);
				l_exit.setForeground(Color.BLACK);
			}
		});
		
		panel.setFocusable(true);
		
		ch_account = new Choice();
		ch_account.setBounds(95, 50, 250, 21);
		panel.add(ch_account);
		
		

		ch_account.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// gender_type_name = ch.getSelectedItem();
				// System.out.println("선택한 초이스의 이름은: " + gender_type_name);
				choose_account = ch_account.getSelectedIndex();
				l_current_balance.setText("현재 잔액 : " + clientMain.clientInfo.getAcountBalanceList().get(choose_account));
			}
		});
		
	
		
		l_current_balance = new JLabel("l");
		l_current_balance.setHorizontalAlignment(SwingConstants.CENTER);
		l_current_balance.setForeground(new Color(0, 90, 156));
		l_current_balance.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		l_current_balance.setBounds(145, 75, 150, 30);
		
		panel.add(l_current_balance);
		
		ch_bank = new Choice();
		ch_bank.setBounds(95, 114, 90, 30);
		panel.add(ch_bank);
		
		ch_bank.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// gender_type_name = ch.getSelectedItem();
				// System.out.println("선택한 초이스의 이름은: " + gender_type_name);
				bank = ch_bank.getSelectedIndex();
			}
		});
		addBank();
		
		setVisible(false);
	}
	
	public void addBank() {
		ch_bank.addItem("은행을 선택하세요");
		ch_bank.addItem("신한은행");
		ch_bank.addItem("국민은행");
		ch_bank.addItem("KEB하나은행");
		ch_bank.addItem("우리은행");
		ch_bank.addItem("농협은행");
		ch_bank.addItem("기업은행");
		ch_bank.addItem("SC제일은행");
		ch_bank.addItem("카카오뱅크");
	}
	
	public void exit() {
		refresh();
	}
	
	public void ok() {
		if (JOptionPane.showInputDialog("비밀번호를 입력하여 주세요").equals(clientMain.clientInfo.getPw())) {

			String receive_account =  t_account.getText();
			int balance = (Integer) clientMain.clientInfo.getAcountBalanceList().get(choose_account);

			//타입,아아피,내은행이름,내계좌번호,내잔액,보낼금액,내회원번호,내계좌인덱스,상대은행pri,상대계좌번호
			if (balance >= send_money) {
				clientServer.sendMoney("sendmoney", clientMain.clientInfo.getIp(),
						(String) clientMain.clientInfo.getBankNameList().get(choose_account),
						(String) clientMain.clientInfo.getAcountNumList().get(choose_account),
						Integer.toString(balance), Integer.toString(send_money),
						Integer.toString(clientMain.clientInfo.getMemberPri()), Integer.toString(choose_account), Integer.toString(bank),receive_account);
				//JOptionPane.showMessageDialog(this, "송금이 완료되었습니다.");
				refresh();
			}else {
				JOptionPane.showMessageDialog(this, "잔액이 부족합니다");
			}

		} else {
			JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다");
		}
		
		//int deposit_money=Integer.valueOf((String)t_money.getText());
		//int balance=(Integer)clientMain.clientInfo.getAcountBalanceList().get(choose_account);
		//clientServer.sendDeposit("senddeposit",clientMain.clientInfo.getIp(),(String)clientMain.clientInfo.getBankNameList().get(choose_account),(String)clientMain.clientInfo.getAcountNumList().get(choose_account),Integer.toString(balance),Integer.toString(deposit_money),Integer.toString(clientMain.clientInfo.getMemberPri()),Integer.toString(choose_account));
		
	

	}
	
	public void refresh() {
		
		setVisible(false);
		home.setVisible(true);
		send.send_money=0;
		send.l_send_money.setText(send.send_money+"원");
		send_money=0;
		choose_account=0;
		bank=0;
	}
	
	public void success() {
		JOptionPane.showMessageDialog(this, "송금이 완료되었습니다.");
	}
	
	public void fail() {
		JOptionPane.showMessageDialog(this, "존재하지 않는 계좌번호입니다");
	}
	
}
