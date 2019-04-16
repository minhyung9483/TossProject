package com.toss.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Join extends JFrame{
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Color dodgerblue=new Color(0,90,156);	
	 JTextField t_id;
	 JPasswordField t_pw;
	  JPasswordField t_pw2;
	  JTextField t_name;
	 JPanel p_ok,p_cancle,p_checkid;
	  JLabel l_pwcheck;
	   JLabel l_checkid;
	   JLabel l_cancle;
	   JLabel l_ok;
	   
	   String id,pw,pw2,name;
	
	public Join(ClientMain clientMain,ClientServer clientServer) {
		this.clientMain=clientMain;
		this.clientServer=clientServer;
		setSize(450,740);
		setLocationRelativeTo(null);
		
		JPanel p_wrapper = new JPanel();
		p_wrapper.setBackground(Color.WHITE);
		getContentPane().add(p_wrapper, BorderLayout.CENTER);
		p_wrapper.setLayout(null);
		
		JLabel l_sub = new JLabel("   회원가입");
		l_sub.setHorizontalAlignment(SwingConstants.LEFT);
		l_sub.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		l_sub.setBounds(0, 0, 100, 35);
		p_wrapper.add(l_sub);
		
		JLabel l_id = new JLabel("ID");
		l_id.setFont(new Font("Verdana", Font.PLAIN, 20));
		l_id.setHorizontalAlignment(SwingConstants.CENTER);
		l_id.setBounds(0, 170, 150, 35);
		p_wrapper.add(l_id);
		
		t_id = new JTextField();
		t_id.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		t_id.setHorizontalAlignment(SwingConstants.CENTER);
		t_id.setBounds(150, 170, 150, 35);
		p_wrapper.add(t_id);
		t_id.setColumns(10);
		
		 p_checkid = new JPanel();
		p_checkid.setBackground(Color.WHITE);
		p_checkid.setBounds(313, 170, 84, 35);
		p_wrapper.add(p_checkid);
		p_checkid.setLayout(new BorderLayout(0, 0));
		
		l_checkid = new JLabel("중복확인");
		l_checkid.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		l_checkid.setHorizontalAlignment(SwingConstants.CENTER);
		p_checkid.add(l_checkid, BorderLayout.CENTER);
		
		JLabel l_pw = new JLabel("Password");
		l_pw.setHorizontalAlignment(SwingConstants.CENTER);
		l_pw.setFont(new Font("Verdana", Font.PLAIN, 20));
		l_pw.setBounds(0, 270, 150, 35);
		p_wrapper.add(l_pw);
		
		t_pw = new JPasswordField();
		t_pw.setFont(new Font("Verdana", Font.PLAIN, 20));
		t_pw.setHorizontalAlignment(SwingConstants.CENTER);
		t_pw.setColumns(10);
		t_pw.setBounds(150, 270, 247, 35);
		p_wrapper.add(t_pw);
		
		JLabel l_pw2 = new JLabel("Password");
		l_pw2.setHorizontalAlignment(SwingConstants.CENTER);
		l_pw2.setFont(new Font("Verdana", Font.PLAIN, 20));
		l_pw2.setBounds(0, 320, 150, 35);
		p_wrapper.add(l_pw2);
		
		t_pw2 = new JPasswordField();
		t_pw2.setFont(new Font("Verdana", Font.PLAIN, 20));
		t_pw2.setHorizontalAlignment(SwingConstants.CENTER);
		t_pw2.setColumns(10);
		t_pw2.setBounds(150, 320, 247, 35);
		p_wrapper.add(t_pw2);
		
		JLabel l_name = new JLabel("Name");
		l_name.setHorizontalAlignment(SwingConstants.CENTER);
		l_name.setFont(new Font("Verdana", Font.PLAIN, 20));
		l_name.setBounds(0, 220, 150, 35);
		p_wrapper.add(l_name);
		
		t_name = new JTextField();
		t_name.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		t_name.setHorizontalAlignment(SwingConstants.CENTER);
		t_name.setColumns(10);
		t_name.setBounds(150, 220, 247, 35);
		p_wrapper.add(t_name);
		
		 p_cancle = new JPanel();
		p_cancle.setBackground(Color.WHITE);
		p_cancle.setBounds(109, 465, 84, 35);
		p_wrapper.add(p_cancle);
		p_cancle.setLayout(new BorderLayout(0, 0));
		
		l_cancle = new JLabel("취소");
		l_cancle.setHorizontalAlignment(SwingConstants.CENTER);
		l_cancle.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		p_cancle.add(l_cancle, BorderLayout.CENTER);
		
		 p_ok = new JPanel();
		p_ok.setBackground(Color.WHITE);
		p_ok.setBounds(240, 465, 84, 35);
		p_wrapper.add(p_ok);
		p_ok.setLayout(new BorderLayout(0, 0));
		
		l_ok = new JLabel("확인");
		l_ok.setHorizontalAlignment(SwingConstants.CENTER);
		l_ok.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		p_ok.add(l_ok, BorderLayout.CENTER);
		
		l_pwcheck = new JLabel("");
		l_pwcheck.setFont(new Font("배달의민족 한나는 열한살", Font.PLAIN, 15));
		l_pwcheck.setBounds(150, 370, 247, 35);
		p_wrapper.add(l_pwcheck);
		
		p_ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				join();
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
		p_cancle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				close();
			}
			
			public void mouseEntered(MouseEvent e) {
				p_cancle.setBackground(dodgerblue);
				l_cancle.setForeground(Color.WHITE);
			}
			
			public void mouseExited(MouseEvent e) {
				p_cancle.setBackground(Color.WHITE);
				l_cancle.setForeground(Color.BLACK);
			}
		});
		p_checkid.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkid();
			}
			
			public void mouseEntered(MouseEvent e) {
				p_checkid.setBackground(dodgerblue);
				l_checkid.setForeground(Color.WHITE);
			}
			
			public void mouseExited(MouseEvent e) {
				p_checkid.setBackground(Color.WHITE);
				l_checkid.setForeground(Color.BLACK);
			}
		});
		
		t_pw2.addKeyListener(new KeyAdapter() {
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				pw=t_pw.getText();
				pw2=t_pw2.getText();
				if(pw.equals(pw2)) {
					l_pwcheck.setForeground(Color.BLUE);
					l_pwcheck.setText("비밀번호가 일치합니다");
				}
				else {
					l_pwcheck.setForeground(Color.RED);
					l_pwcheck.setText("비밀번호가 일치하지 않습니다");
				}
			}
		});
		
		setVisible(true);
	}
	
	public void checkid() {
		id=t_id.getText();
		clientServer.sendCheckId("checkid",clientMain.clientInfo.getIp(), id);
	}
	
	public void join() {
		id=t_id.getText();
		pw=t_pw.getText();
		pw2=t_pw2.getText();
		name=t_name.getText();
		
		if(id.equals(clientMain.clientInfo.getCheckId())) {
			if(pw.equals(pw2)){
				clientServer.sendJoin("join",clientMain.clientInfo.getIp(), id,pw,name);
				JOptionPane.showMessageDialog(this, "회원가입이 완료되었습니다!!");
				close();
			}else {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다!!");
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "아이디 중복확인을 해주세요!!");
		}
	}
	
	public void close() {
		this.setVisible(false);
	}
}
