package com.toss.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

public class Send extends JPanel {
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Home home;
	SendForm sendForm;
	Color dodgerblue = new Color(0, 90, 156);
	int send_money;
	JPanel p_1, p_2, p_3, p_4, p_5, p_6, p_7, p_8, p_9, p_0, p_erase, p_erase_all;
	JLabel l_1, l_2, l_3, l_4, l_5, l_6, l_7, l_8, l_9, l_0, l_erase, l_erase_all, l_send_money;
	 JPanel p_send;
	 JLabel l_send;

	public Send(ClientMain clientMain, ClientServer clientServer, Home home) {
		this.clientMain = clientMain;
		this.clientServer = clientServer;
		this.home = home;
		sendForm=new SendForm(clientMain, clientServer, home, this);

		setPreferredSize(new Dimension(434, 651));
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel p_up = new JPanel();
		p_up.setBackground(Color.WHITE);
		p_up.setBounds(0, 0, 434, 50);
		add(p_up);
		p_up.setLayout(null);

		JLabel ㅣ_sub = new JLabel("   송금");
		ㅣ_sub.setBounds(0, 0, 100, 35);
		p_up.add(ㅣ_sub);
		ㅣ_sub.setHorizontalAlignment(SwingConstants.LEFT);
		ㅣ_sub.setForeground(Color.BLACK);
		ㅣ_sub.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		ㅣ_sub.setBackground(Color.WHITE);
		
		p_send = new JPanel();
		p_send.setBackground(Color.WHITE);
		p_send.setBounds(364, 0, 70, 35);
		p_up.add(p_send);
		p_send.setLayout(new BorderLayout(0, 0));
		
		l_send = new JLabel("보내기");
		l_send.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		l_send.setHorizontalAlignment(SwingConstants.CENTER);
		p_send.add(l_send, BorderLayout.CENTER);

		JPanel p_send_money = new JPanel();
		p_send_money.setBackground(Color.WHITE);
		p_send_money.setBounds(0, 50, 434, 100);
		add(p_send_money);
		p_send_money.setLayout(new BorderLayout(0, 0));

		l_send_money = new JLabel(send_money + "원");
		l_send_money.setBackground(Color.WHITE);
		l_send_money.setFont(new Font("배달의민족 주아", Font.BOLD, 30));
		l_send_money.setHorizontalAlignment(SwingConstants.CENTER);
		p_send_money.add(l_send_money, BorderLayout.CENTER);

		JPanel p_keypad = new JPanel();
		p_keypad.setBackground(Color.WHITE);
		p_keypad.setBounds(0, 150, 434, 500);
		add(p_keypad);
		p_keypad.setLayout(null);

		JPanel p_123 = new JPanel();
		p_123.setBackground(Color.WHITE);
		p_123.setBounds(0, 0, 434, 125);
		p_keypad.add(p_123);
		p_123.setLayout(new GridLayout(1, 0, 0, 0));

		p_1 = new JPanel();
		p_1.setBackground(Color.WHITE);
		p_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_123.add(p_1);
		p_1.setLayout(new BorderLayout(0, 0));

		l_1 = new JLabel("1");
		l_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		l_1.setHorizontalAlignment(SwingConstants.CENTER);
		p_1.add(l_1, BorderLayout.CENTER);

		p_2 = new JPanel();
		p_2.setBackground(Color.WHITE);
		p_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_123.add(p_2);
		p_2.setLayout(new BorderLayout(0, 0));

		l_2 = new JLabel("2");
		l_2.setHorizontalAlignment(SwingConstants.CENTER);
		l_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_2.add(l_2, BorderLayout.CENTER);

		p_3 = new JPanel();
		p_3.setBackground(Color.WHITE);
		p_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_123.add(p_3);
		p_3.setLayout(new BorderLayout(0, 0));

		l_3 = new JLabel("3");
		l_3.setHorizontalAlignment(SwingConstants.CENTER);
		l_3.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_3.add(l_3, BorderLayout.CENTER);

		JPanel p_456 = new JPanel();
		p_456.setBackground(Color.WHITE);
		p_456.setBounds(0, 125, 434, 125);
		p_keypad.add(p_456);
		p_456.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel p_4 = new JPanel();
		p_4.setBackground(Color.WHITE);
		p_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_456.add(p_4);
		p_4.setLayout(new BorderLayout(0, 0));

		l_4 = new JLabel("4");
		l_4.setHorizontalAlignment(SwingConstants.CENTER);
		l_4.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_4.add(l_4, BorderLayout.CENTER);

		p_5 = new JPanel();
		p_5.setBackground(Color.WHITE);
		p_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_456.add(p_5);
		p_5.setLayout(new BorderLayout(0, 0));

		l_5 = new JLabel("5");
		l_5.setHorizontalAlignment(SwingConstants.CENTER);
		l_5.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_5.add(l_5, BorderLayout.CENTER);

		p_6 = new JPanel();
		p_6.setBackground(Color.WHITE);
		p_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_456.add(p_6);
		p_6.setLayout(new BorderLayout(0, 0));

		l_6 = new JLabel("6");
		l_6.setHorizontalAlignment(SwingConstants.CENTER);
		l_6.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_6.add(l_6, BorderLayout.CENTER);

		JPanel p_789 = new JPanel();
		p_789.setBackground(Color.WHITE);
		p_789.setBounds(0, 250, 434, 125);
		p_keypad.add(p_789);
		p_789.setLayout(new GridLayout(1, 0, 0, 0));

		p_7 = new JPanel();
		p_7.setBackground(Color.WHITE);
		p_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_789.add(p_7);
		p_7.setLayout(new BorderLayout(0, 0));

		l_7 = new JLabel("7");
		l_7.setHorizontalAlignment(SwingConstants.CENTER);
		l_7.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_7.add(l_7, BorderLayout.CENTER);

		p_8 = new JPanel();
		p_8.setBackground(Color.WHITE);
		p_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_789.add(p_8);
		p_8.setLayout(new BorderLayout(0, 0));

		l_8 = new JLabel("8");
		l_8.setHorizontalAlignment(SwingConstants.CENTER);
		l_8.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_8.add(l_8, BorderLayout.CENTER);

		p_9 = new JPanel();
		p_9.setBackground(Color.WHITE);
		p_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_789.add(p_9);
		p_9.setLayout(new BorderLayout(0, 0));

		l_9 = new JLabel("9");
		l_9.setHorizontalAlignment(SwingConstants.CENTER);
		l_9.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_9.add(l_9, BorderLayout.CENTER);

		JPanel p_0c = new JPanel();
		p_0c.setBackground(Color.WHITE);
		p_0c.setBounds(0, 375, 434, 125);
		p_keypad.add(p_0c);
		p_0c.setLayout(new GridLayout(0, 3, 0, 0));

		p_erase_all = new JPanel();
		p_erase_all.setBackground(Color.WHITE);
		p_erase_all.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_0c.add(p_erase_all);
		p_erase_all.setLayout(new BorderLayout(0, 0));

		l_erase_all = new JLabel("ac");
		l_erase_all.setHorizontalAlignment(SwingConstants.CENTER);
		l_erase_all.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_erase_all.add(l_erase_all, BorderLayout.CENTER);

		p_0 = new JPanel();
		p_0.setBackground(Color.WHITE);
		p_0.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_0c.add(p_0);
		p_0.setLayout(new BorderLayout(0, 0));

		l_0 = new JLabel("0");
		l_0.setHorizontalAlignment(SwingConstants.CENTER);
		l_0.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
		p_0.add(l_0, BorderLayout.CENTER);

		p_erase = new JPanel();
		p_erase.setBackground(Color.WHITE);
		p_erase.setBorder(new LineBorder(new Color(0, 0, 0)));
		p_0c.add(p_erase);
		p_erase.setLayout(new BorderLayout(0, 0));

		l_erase = new JLabel("←");
		l_erase.setHorizontalAlignment(SwingConstants.CENTER);
		l_erase.setFont(new Font("바탕", Font.BOLD, 25));
		p_erase.add(l_erase, BorderLayout.CENTER);

		p_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(1);
			}

			public void mouseEntered(MouseEvent e) {
				p_1.setBackground(dodgerblue);
				l_1.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_1.setBackground(Color.WHITE);
				l_1.setForeground(Color.BLACK);
			}
		});
		p_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(2);
			}

			public void mouseEntered(MouseEvent e) {
				p_2.setBackground(dodgerblue);
				l_2.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_2.setBackground(Color.WHITE);
				l_2.setForeground(Color.BLACK);
			}
		});
		p_3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(3);
			}

			public void mouseEntered(MouseEvent e) {
				p_3.setBackground(dodgerblue);
				l_3.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_3.setBackground(Color.WHITE);
				l_3.setForeground(Color.BLACK);
			}
		});
		p_4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(4);
			}

			public void mouseEntered(MouseEvent e) {
				p_4.setBackground(dodgerblue);
				l_4.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_4.setBackground(Color.WHITE);
				l_4.setForeground(Color.BLACK);
			}
		});
		p_5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(5);
			}

			public void mouseEntered(MouseEvent e) {
				p_5.setBackground(dodgerblue);
				l_5.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_5.setBackground(Color.WHITE);
				l_5.setForeground(Color.BLACK);
			}
		});
		p_6.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(6);
			}

			public void mouseEntered(MouseEvent e) {
				p_6.setBackground(dodgerblue);
				l_6.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_6.setBackground(Color.WHITE);
				l_6.setForeground(Color.BLACK);
			}
		});
		p_7.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(7);
			}

			public void mouseEntered(MouseEvent e) {
				p_7.setBackground(dodgerblue);
				l_7.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_7.setBackground(Color.WHITE);
				l_7.setForeground(Color.BLACK);
			}
		});
		p_8.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(8);
			}

			public void mouseEntered(MouseEvent e) {
				p_8.setBackground(dodgerblue);
				l_8.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_8.setBackground(Color.WHITE);
				l_8.setForeground(Color.BLACK);
			}
		});
		p_9.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(9);
			}

			public void mouseEntered(MouseEvent e) {
				p_9.setBackground(dodgerblue);
				l_9.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_9.setBackground(Color.WHITE);
				l_9.setForeground(Color.BLACK);
			}
		});
		p_0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				addMoney(0);
			}

			public void mouseEntered(MouseEvent e) {
				p_0.setBackground(dodgerblue);
				l_0.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_0.setBackground(Color.WHITE);
				l_0.setForeground(Color.BLACK);
			}
		});
		p_erase_all.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				eraseAll();
			}

			public void mouseEntered(MouseEvent e) {
				p_erase_all.setBackground(dodgerblue);
				l_erase_all.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_erase_all.setBackground(Color.WHITE);
				l_erase_all.setForeground(Color.BLACK);
			}
		});
		p_erase.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				eraseMoney();
			}

			public void mouseEntered(MouseEvent e) {
				p_erase.setBackground(dodgerblue);
				l_erase.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_erase.setBackground(Color.WHITE);
				l_erase.setForeground(Color.BLACK);
			}
		});
		p_send.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(home, "전송하시겠습니까?")==JOptionPane.OK_OPTION) {
					typeAccount();
				}
			}
			
			public void mouseEntered(MouseEvent e) {
				p_send.setBackground(dodgerblue);
				l_send.setForeground(Color.WHITE);
			}
			
			public void mouseExited(MouseEvent e) {
				p_send.setBackground(Color.WHITE);
				l_send.setForeground(Color.BLACK);
			}
		});

	}

	public void addMoney(int num) {
		if (send_money == 0) {
			String str = "";
			str += Integer.toString(num);
			send_money = Integer.valueOf((String) str);
			l_send_money.setText(str + "원");
		} else {
			String str = Integer.toString(send_money);
			str += Integer.toString(num);
			send_money = Integer.valueOf((String) str);
			l_send_money.setText(str + "원");
		}
	}

	public void eraseMoney() {
		if (send_money<10) {
			eraseAll();
		}
		else {
			String str = Integer.toString(send_money);
			char str2[] = new char[str.length() - 1];
			str.getChars(0, str.length() - 1, str2, 0);
			str = "";
			for (int i = 0; i < str2.length; i++) {
				str += str2[i];
			}
			send_money = Integer.valueOf((String) str);
			l_send_money.setText(str + "원");
		}
	}

	public void eraseAll() {
		String str = "0";
		send_money = Integer.valueOf((String) str);
		l_send_money.setText(str + "원");

	}
	
	public void typeAccount() {
		sendForm.l_current_balance.setText("현재 잔액 : " + clientMain.clientInfo.getAcountBalanceList().get(0));
		for (int i = 0; i < clientMain.clientInfo.getAcountNumList().size(); i++) {
			String info = (String) clientMain.clientInfo.getBankNameList().get(i);
			info += " " + clientMain.clientInfo.getAcountNumList().get(i);
			sendForm.ch_account.addItem(info);
		}
		sendForm.send_money=send_money;
		sendForm.setVisible(true);
		home.setVisible(false);
	}
}
