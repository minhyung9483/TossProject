package com.toss.client;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

public class CreateAccount extends JFrame {
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Home home;
	Balance balance;
	Color dodgerblue = new Color(0, 90, 156);
	JTextField t_account_num;
	JPanel p_ok, p_exit, p_refresh;
	JLabel l_ok, l_exit, l_refresh;
	Choice ch;
	int bank;
	String temp,accountNum;

	public CreateAccount(ClientMain clientMain, ClientServer clientServer, Home home, Balance balance) {
		this.clientMain = clientMain;
		this.clientServer = clientServer;
		this.home = home;
		this.balance = balance;

		setSize(450, 300);
		setLocationRelativeTo(null);
		setBackground(Color.WHITE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label = new JLabel("   계좌 생성");
		label.setBounds(0, 0, 100, 35);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		panel.add(label);

		t_account_num = new JTextField();
		t_account_num.setHorizontalAlignment(SwingConstants.CENTER);
		t_account_num.setColumns(10);
		t_account_num.setBounds(101, 108, 150, 30);
		panel.add(t_account_num);
		t_account_num.enable(false);

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

		l_ok = new JLabel("생성");
		l_ok.setHorizontalAlignment(SwingConstants.CENTER);
		l_ok.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		p_ok.add(l_ok, BorderLayout.CENTER);

		p_refresh = new JPanel();
		p_refresh.setForeground(Color.BLACK);
		p_refresh.setBackground(Color.WHITE);
		p_refresh.setBounds(263, 108, 75, 30);
		panel.add(p_refresh);
		p_refresh.setLayout(new BorderLayout(0, 0));

		l_refresh = new JLabel("새로고침");
		l_refresh.setHorizontalAlignment(SwingConstants.CENTER);
		l_refresh.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		p_refresh.add(l_refresh, BorderLayout.CENTER);

		ch = new Choice();
		ch.setBounds(101, 60, 237, 30);
		panel.add(ch);
		addBank();

		makeAccountNum();

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
		p_refresh.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				makeAccountNum();
			}

			public void mouseEntered(MouseEvent e) {
				p_refresh.setBackground(dodgerblue);
				l_refresh.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_refresh.setBackground(Color.WHITE);
				l_refresh.setForeground(Color.BLACK);
			}
		});

		ch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// gender_type_name = ch.getSelectedItem();
				// System.out.println("선택한 초이스의 이름은: " + gender_type_name);
				bank = ch.getSelectedIndex();
			}
		});

		setVisible(true);

	}

	public void exit() {
		setVisible(false);
		home.setVisible(true);
	}

	public void ok() {
		clientServer.sendCreateAccount("createaccount",clientMain.clientInfo.getIp(),accountNum,Integer.toString(bank),Integer.toString(clientMain.clientInfo.getMemberPri()));
		JOptionPane.showMessageDialog(clientMain.login.home, "계좌가 생성되었습니다");
		setVisible(false);
		home.setVisible(true);

	}

	public void addBank() {
		ch.addItem("은행을 선택하세요");
		ch.addItem("신한은행");
		ch.addItem("국민은행");
		ch.addItem("KEB하나은행");
		ch.addItem("우리은행");
		ch.addItem("농협은행");
		ch.addItem("기업은행");
		ch.addItem("SC제일은행");
		ch.addItem("카카오뱅크");
	}

	public void makeAccountNum() {
		temp="";
		accountNum="";
		for (int i = 0; i < 6; i++) {
			int x = (int) (Math.random() * 10);
			temp+=Integer.toString(x);
		}
		accountNum="110-400-"+temp;		
		t_account_num.setText(accountNum);
		clientServer.sendCheckAccount("checkaccount",clientMain.clientInfo.getIp(),accountNum);
		
	}
	
	
}
