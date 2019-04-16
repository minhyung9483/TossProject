package com.toss.client;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Home extends JFrame {
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Send send;
	Balance balance;
	AtmMain atm;
	String[] mainTitle = { "SEND", "BALANCE","ATM" };
	JPanel[] pages = new JPanel[mainTitle.length];
	Color dodgerblue = new Color(0, 90, 156);
	JPanel container;
	JPanel p_send, p_balance;
	 JLabel l_send;
	 JLabel l_balance;
	  JPanel p_atm;
	  JLabel l_atm;

	public Home(ClientMain clientMain, ClientServer clientServer) {
		getContentPane().setBackground(Color.WHITE);
		this.clientMain = clientMain;
		this.clientServer = clientServer;

		setSize(450, 740);
		setLocationRelativeTo(null);
		container = new JPanel();
		container.setBackground(Color.WHITE);

		send = new Send(clientMain, clientServer, this);
		pages[0] = send;
		balance = new Balance(clientMain, clientServer, this);
		pages[1] = balance;
		atm = new AtmMain(clientMain, clientServer, this);
		pages[2] = atm;

		container.add(pages[0]);
		container.add(pages[1]);
		container.add(pages[2]);
		getContentPane().add(container, BorderLayout.CENTER);
		container.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel p_tab = new JPanel();
		getContentPane().add(p_tab, BorderLayout.SOUTH);
		p_tab.setLayout(new GridLayout(1, 0, 0, 0));
		p_tab.setPreferredSize(new Dimension(450, 50));

		p_send = new JPanel();
		p_tab.add(p_send);
		p_send.setBackground(dodgerblue);
		p_send.setLayout(new BorderLayout(0, 0));
		
		l_send = new JLabel("송금");
		l_send.setFont(new Font("배달의민족 주아", Font.BOLD, 18));
		l_send.setHorizontalAlignment(SwingConstants.CENTER);
		l_send.setForeground(Color.WHITE);
		p_send.add(l_send);

		p_balance = new JPanel();
		p_tab.add(p_balance);
		p_balance.setBackground(Color.white);
		p_balance.setLayout(new BorderLayout(0, 0));
		
		l_balance = new JLabel("잔액조회");
		l_balance.setFont(new Font("배달의민족 주아", Font.BOLD, 18));
		l_balance.setHorizontalAlignment(SwingConstants.CENTER);
		p_balance.add(l_balance, BorderLayout.CENTER);
		
		p_atm = new JPanel();
		p_atm.setBackground(Color.WHITE);
		p_tab.add(p_atm);
		p_atm.setLayout(new BorderLayout(0, 0));
		
		l_atm = new JLabel("ATM");
		l_atm.setHorizontalAlignment(SwingConstants.CENTER);
		l_atm.setFont(new Font("배달의민족 주아", Font.BOLD, 18));
		p_atm.add(l_atm, BorderLayout.CENTER);

		p_send.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// user.getGuest().addGuest();
				// openRegist();
				showPage(0);
				p_send.setBackground(dodgerblue);
				p_balance.setBackground(Color.white);
				p_atm.setBackground(Color.white);
				l_send.setForeground(Color.WHITE);
				l_balance.setForeground(Color.BLACK);
				l_atm.setForeground(Color.BLACK);
				
			}

			public void mouseEntered(MouseEvent e) {
			//	p_send.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
			//	p_send.setBackground(Color.BLUE);
			}
		});

		p_balance.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// user.getGuest().addGuest();
				// openRegist();
				showPage(1);
				p_balance.setBackground(dodgerblue);
				p_send.setBackground(Color.white);
				p_atm.setBackground(Color.white);
				l_balance.setForeground(Color.WHITE);
				l_send.setForeground(Color.BLACK);
				l_atm.setForeground(Color.BLACK);
			}

			public void mouseEntered(MouseEvent e) {
			//	p_balance.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
			//	p_balance.setBackground(Color.BLUE);
			}
		});
		p_atm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// user.getGuest().addGuest();
				// openRegist();
				showPage(2);
				p_atm.setBackground(dodgerblue);
				p_send.setBackground(Color.white);
				p_balance.setBackground(Color.white);
				l_atm.setForeground(Color.WHITE);
				l_send.setForeground(Color.BLACK);
				l_balance.setForeground(Color.BLACK);
			}
			
			public void mouseEntered(MouseEvent e) {
				//	p_balance.setBackground(Color.DARK_GRAY);
			}
			
			public void mouseExited(MouseEvent e) {
				//	p_balance.setBackground(Color.BLUE);
			}
		});

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		pages[0].setVisible(true);// send
		pages[1].setVisible(false);// balance창
		pages[2].setVisible(false);

		setVisible(false);
	}

	public void showPage(int page) {
		for (int i = 0; i < pages.length; i++) {
			if (i == page) {
				pages[i].setVisible(true);
			} else {
				pages[i].setVisible(false);
			}

		}
	}

}
