package com.toss.client;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
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
import java.awt.Choice;
import javax.swing.JTextField;

public class Withdraw extends JFrame {
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Home home;
	AtmMain atm;
	Color dodgerblue = new Color(0, 90, 156);

	Choice ch_account;
	JTextField t_money;
	JPanel p_ok, p_exit;
	JLabel l_ok, l_exit;
	JLabel label;
	int choose_account;
	JLabel l_current_balance;

	public Withdraw(ClientMain clientMain, ClientServer clientServer, Home home, AtmMain atm) {
		this.clientMain = clientMain;
		this.clientServer = clientServer;
		this.home = home;
		this.atm = atm;

		setSize(450, 300);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		label = new JLabel("   출금");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		label.setBounds(0, 0, 100, 35);
		panel.add(label);

		ch_account = new Choice();
		ch_account.setBounds(95, 50, 250, 21);
		panel.add(ch_account);

		for (int i = 0; i < clientMain.clientInfo.getAcountNumList().size(); i++) {
			String info = (String) clientMain.clientInfo.getBankNameList().get(i);
			info += " " + clientMain.clientInfo.getAcountNumList().get(i);
			ch_account.addItem(info);
		}

		ch_account.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// gender_type_name = ch.getSelectedItem();
				// System.out.println("선택한 초이스의 이름은: " + gender_type_name);
				choose_account = ch_account.getSelectedIndex();
				l_current_balance
						.setText("현재 잔액 : " + clientMain.clientInfo.getAcountBalanceList().get(choose_account));
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

		l_current_balance = new JLabel("");
		l_current_balance.setForeground(dodgerblue);
		l_current_balance.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		l_current_balance.setHorizontalAlignment(SwingConstants.CENTER);
		l_current_balance.setBounds(145, 75, 150, 30);
		l_current_balance.setText("현재 잔액 : " + clientMain.clientInfo.getAcountBalanceList().get(choose_account));
		panel.add(l_current_balance);

		setVisible(true);

	}

	public void exit() {
		setVisible(false);
		home.setVisible(true);
	}

	public void ok() {
		if (JOptionPane.showInputDialog("비밀번호를 입력하여 주세요").equals(clientMain.clientInfo.getPw())) {

			int withdraw_money = Integer.valueOf((String) t_money.getText());
			int balance = (Integer) clientMain.clientInfo.getAcountBalanceList().get(choose_account);

			if (balance >= withdraw_money) {
				clientServer.sendWithdraw("sendwithdraw", clientMain.clientInfo.getIp(),
						(String) clientMain.clientInfo.getBankNameList().get(choose_account),
						(String) clientMain.clientInfo.getAcountNumList().get(choose_account),
						Integer.toString(balance), Integer.toString(withdraw_money),
						Integer.toString(clientMain.clientInfo.getMemberPri()), Integer.toString(choose_account));
				JOptionPane.showMessageDialog(this, "출금이 완료되었습니다.");
				setVisible(false);
				home.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(this, "잔액이 부족합니다");
			}

		} else {
			JOptionPane.showMessageDialog(this, "비밀번호가 틀렸습니다");
		}

	}
}
