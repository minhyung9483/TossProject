package com.toss.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Login extends JFrame{
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Join join;
	Home home;
	
	
	JTextField t_id;
	JPasswordField t_pw;
	Color dodgerblue=new Color(0,90,156);
	JPanel p_join,p_login;
	 JLabel ㅣ_sub;
	  JLabel l_join;
	  JLabel l_login;
	  
	  String id,pw;
	
	public Login(ClientMain clientMain,ClientServer clientServer) {
		this.clientMain=clientMain;
		this.clientServer=clientServer;
		
		setSize(450,740);
		setLocationRelativeTo(null);
		
		JPanel p_wrapper = new JPanel();
		p_wrapper.setBackground(Color.WHITE);
		getContentPane().add(p_wrapper, BorderLayout.CENTER);
		p_wrapper.setLayout(null);
		
		t_id = new JTextField();
		t_id.setHorizontalAlignment(SwingConstants.CENTER);
		t_id.setForeground(new Color(0, 0, 0));
		t_id.setBounds(92, 238, 250, 35);
		t_id.setForeground(dodgerblue);
		p_wrapper.add(t_id);
		t_id.setColumns(10);
		
		if (t_id.getText().length() == 0) {  
		    t_id.setText("ID");  
		    t_id.setForeground(new Color(150, 150, 150));  
		}  

		t_id.addFocusListener(new FocusAdapter() {  

		    @Override  
		    public void focusGained(FocusEvent e) {  
		        t_id.setText("");  
		        t_id.setForeground(new Color(50, 50, 50));  
		    }  

		    @Override  
		    public void focusLost(FocusEvent e) { 

		        if (t_id.getText().length() == 0) {  
		            t_id.setText("ID");  
		            t_id.setForeground(new Color(150, 150, 150));  
		        }  

		    }  
		});
		
		t_pw = new JPasswordField();
		t_pw.setHorizontalAlignment(SwingConstants.CENTER);
		t_pw.setColumns(10);
		t_pw.setBounds(92, 321, 250, 35);
		t_pw.setForeground(dodgerblue);
		p_wrapper.add(t_pw);
		
		if (t_pw.getText().length() == 0) {  
		    t_pw.setText("PASSWORD");  
		    t_pw.setForeground(new Color(150, 150, 150));  
		}  

		t_pw.addFocusListener(new FocusListener() {  

		    @Override  
		    public void focusGained(FocusEvent e) {  
		        t_pw.setText("");  
		        t_pw.setForeground(new Color(50, 50, 50));  
		    }  

		    @Override  
		    public void focusLost(FocusEvent e) { 

		        if (t_pw.getText().length() == 0) {  
		            t_pw.setText("PASSWORD");  
		            t_pw.setForeground(new Color(150, 150, 150));  
		        }  

		    }  
		});
		
		JLabel l_title = new JLabel("TOSS");
		l_title.setHorizontalAlignment(SwingConstants.CENTER);
		l_title.setFont(new Font("MD개성체", Font.PLAIN, 38));
		l_title.setForeground(Color.black);
		l_title.setBackground(Color.WHITE);
		l_title.setBounds(0, 109, 434, 35);
		p_wrapper.add(l_title);
		
		p_join= new JPanel();
		p_join.setForeground(Color.BLACK);
		p_join.setBounds(100, 467, 100, 35);
		p_join.setBackground(Color.white);
		p_wrapper.add(p_join);
		p_join.setLayout(new BorderLayout(0, 0));
		
		l_join = new JLabel("JOIN");
		l_join.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		l_join.setHorizontalAlignment(SwingConstants.CENTER);
		p_join.add(l_join, BorderLayout.CENTER);
		
		p_login = new JPanel();
		p_login.setForeground(Color.BLACK);
		p_login.setBackground(Color.white);
		p_login.setBounds(234, 467, 100, 35);
		p_wrapper.add(p_login);
		p_login.setLayout(new BorderLayout(0, 0));
		
		l_login = new JLabel("LOGIN");
		p_login.add(l_login, BorderLayout.CENTER);
		l_login.setHorizontalAlignment(SwingConstants.CENTER);
		l_login.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		
		ㅣ_sub = new JLabel("   로그인");
		ㅣ_sub.setBounds(0, 0, 100, 35);
		p_wrapper.add(ㅣ_sub);
		ㅣ_sub.setHorizontalAlignment(SwingConstants.LEFT);
		ㅣ_sub.setForeground(Color.BLACK);
		ㅣ_sub.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		ㅣ_sub.setBackground(Color.WHITE);
		
		p_join.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				join();
			}
			
			public void mouseEntered(MouseEvent e) {
				p_join.setBackground(dodgerblue);
				l_join.setForeground(Color.WHITE);
			}
			
			public void mouseExited(MouseEvent e) {
				p_join.setBackground(Color.WHITE);
				l_join.setForeground(Color.BLACK);
			}
		});
		p_login.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				login();
				
			}
			
			public void mouseEntered(MouseEvent e) {
				p_login.setBackground(dodgerblue);
				l_login.setForeground(Color.WHITE);
			}
			
			public void mouseExited(MouseEvent e) {
				p_login.setBackground(Color.WHITE);
				l_login.setForeground(Color.BLACK);
			}
		});
		
		
		
		l_title.setFocusable(true);
		setVisible(true);
		home=new Home(clientMain,clientServer);
	}
	
	public void join() {
		 join=new Join(clientMain,clientServer);
	}
	
	public void login() {
		id=t_id.getText();
		pw=t_pw.getText();
		clientServer.sendLogin("login",clientMain.clientInfo.getIp(),id,pw);
		
		//if(clientMain.clientInfo.getCheckLogin().equals("true")) {
		//	home=new Home(clientMain,clientServer);
		//	this.setVisible(false);			
		//}
		
	}
	
	public void openHome() {
		home.setVisible(true);
		this.setVisible(false);		
	}
	
	
	
}
