package com.toss.client;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class AtmMain extends JPanel{
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Home home;
	Deposit deposit;
	Withdraw withdraw;
	
	Color dodgerblue = new Color(0, 90, 156);
	JPanel p_in,p_out;
	JLabel l_in,l_out;

	
	public AtmMain(ClientMain clientMain, ClientServer clientServer, Home home) {
		this.clientMain = clientMain;
		this.clientServer = clientServer;
		this.home = home;
		
		setPreferredSize(new Dimension(434, 651));
		setBackground(Color.PINK);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 434, 50);
		add(panel);
		
		JLabel l_sub = new JLabel("   ATM");
		l_sub.setHorizontalAlignment(SwingConstants.LEFT);
		l_sub.setForeground(Color.BLACK);
		l_sub.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		l_sub.setBackground(Color.WHITE);
		l_sub.setBounds(0, 0, 100, 35);
		panel.add(l_sub);
		
		 p_in = new JPanel();
		p_in.setBackground(Color.WHITE);
		p_in.setBounds(0, 50, 434, 300);
		add(p_in);
		p_in.setLayout(new BorderLayout(0, 0));
		
		 l_in = new JLabel("입금");
		l_in.setFont(new Font("배달의민족 주아", Font.BOLD, 50));
		l_in.setHorizontalAlignment(SwingConstants.CENTER);
		p_in.add(l_in, BorderLayout.CENTER);
		
		 p_out = new JPanel();
		p_out.setBackground(Color.WHITE);
		p_out.setBounds(0, 350, 434, 300);
		add(p_out);
		p_out.setLayout(new BorderLayout(0, 0));
		
		 l_out = new JLabel("출금");
		l_out.setHorizontalAlignment(SwingConstants.CENTER);
		l_out.setFont(new Font("배달의민족 주아", Font.BOLD, 50));
		p_out.add(l_out, BorderLayout.CENTER);
		
		
		p_in.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				deposit();
			}

			public void mouseEntered(MouseEvent e) {
				p_in.setBackground(dodgerblue);
				l_in.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				p_in.setBackground(Color.WHITE);
				l_in.setForeground(Color.BLACK);
			}
		});
		p_out.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				withdraw();
			}
			
			public void mouseEntered(MouseEvent e) {
				p_out.setBackground(dodgerblue);
				l_out.setForeground(Color.WHITE);
			}
			
			public void mouseExited(MouseEvent e) {
				p_out.setBackground(Color.WHITE);
				l_out.setForeground(Color.BLACK);
			}
		});
	}
	
	public void deposit() {
		deposit=new Deposit(clientMain,clientServer,home,this);
		home.setVisible(false);
	}
	
	public void withdraw() {
		withdraw=new Withdraw(clientMain,clientServer,home,this);
		home.setVisible(false);

	}

}
