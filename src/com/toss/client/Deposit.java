package com.toss.client;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Deposit extends JFrame{
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Home home;
	AtmMain atm;
	Color dodgerblue = new Color(0, 90, 156);
	
	 Choice ch_account;
	 JTextField t_money;
	 JPanel p_ok,p_exit;
	 JLabel l_ok,l_exit;
	  JLabel label;
	  int choose_account;

	public Deposit(ClientMain clientMain, ClientServer clientServer, Home home,AtmMain atm) {
		this.clientMain = clientMain;
		this.clientServer = clientServer;
		this.home = home;
		this.atm=atm;
		
		setSize(450, 300);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		ch_account = new Choice();
		ch_account.setBounds(95, 60, 250, 21);
		panel.add(ch_account);
		
		for(int i=0;i<clientMain.clientInfo.getAcountNumList().size();i++) {
			String info=(String) clientMain.clientInfo.getBankNameList().get(i);
			info += " "+clientMain.clientInfo.getAcountNumList().get(i);
			ch_account.addItem(info);
		}
		
		ch_account.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// gender_type_name = ch.getSelectedItem();
				// System.out.println("선택한 초이스의 이름은: " + gender_type_name);
				choose_account = ch_account.getSelectedIndex();
			}
		});

		
		t_money = new JTextField();
		t_money.setHorizontalAlignment(SwingConstants.CENTER);
		t_money.setColumns(10);
		t_money.setBounds(145, 110, 150, 30);
		panel.add(t_money);
		
		if (t_money.getText().length() == 0) {  
		    t_money.setText("금액");  
		    t_money.setForeground(new Color(150, 150, 150));  
		}  

		t_money.addFocusListener(new FocusAdapter() {  

		    @Override  
		    public void focusGained(FocusEvent e) {  
		        t_money.setText("");  
		        t_money.setForeground(new Color(50, 50, 50));  
		    }  

		    @Override  
		    public void focusLost(FocusEvent e) { 

		        if (t_money.getText().length() == 0) {  
		            t_money.setText("금액");  
		            t_money.setForeground(new Color(150, 150, 150));  
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
		
		label = new JLabel("   입금");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		label.setBounds(0, 0, 100, 35);
		panel.add(label);

		setVisible(true);
	}
	
	public void exit() {
		setVisible(false);
		home.setVisible(true);
	}
	
	public void ok() {
		int deposit_money=Integer.valueOf((String)t_money.getText());
		int balance=(Integer)clientMain.clientInfo.getAcountBalanceList().get(choose_account);
		clientServer.sendDeposit("senddeposit",clientMain.clientInfo.getIp(),(String)clientMain.clientInfo.getBankNameList().get(choose_account),(String)clientMain.clientInfo.getAcountNumList().get(choose_account),Integer.toString(balance),Integer.toString(deposit_money),Integer.toString(clientMain.clientInfo.getMemberPri()),Integer.toString(choose_account));
		JOptionPane.showMessageDialog(this, "입금이 완료되었습니다.");
		setVisible(false);
		home.setVisible(true);

	}
}
