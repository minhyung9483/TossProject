package com.toss.server;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/*
public class Main extends JFrame {
	JPanel container; // 화면교체시 컨테이너가 될 화면
	JPanel p_north;
	String[] menuTitle = { "회원", "메뉴", "테이블", "채팅","게임","주문","매출"};
	JMenu[] menu = new JMenu[menuTitle.length];
	JMenuBar bar;
	MemberMain memberMain;
	MenuMain menuMain;
	TableMain tableMain;
	ChatMain chatMain;
	ServerMain serverMain;
	
	String[] img_path= {"1.png","2.png","3.png","4.png","5.png","6.png","7.png"};
	ImageIcon[] img_icon= new ImageIcon[img_path.length];
	URL url;

	// 페이지 구성
	JPanel[] pages = new JPanel[menuTitle.length];
	private Connection con;
	ConnectionManager connectionManager;

	public Main() {
		connectionManager = new ConnectionManager();
		con = connectionManager.connect();
		serverMain = new ServerMain(this);
		container = new JPanel();
		container.setPreferredSize(new Dimension(1400,900));
		
		for(int i=0;i<img_icon.length;i++) {
			url = this.getClass().getClassLoader().getResource((i+1)+".png");
			img_icon[i]=new ImageIcon("res/"+img_path[i]);
		}

		bar=new JMenuBar();
		// 메뉴생성
		for (int i = 0; i < menuTitle.length; i++) {
			//메뉴이름
			menu[i] = new JMenu(menuTitle[i]);

			img_icon[i].setImage(img_icon[i].getImage().getScaledInstance(100, 100, Image.SCALE_REPLICATE));
			menu[i].setIcon(img_icon[i]);
			menu[i].setPreferredSize(new Dimension(200,100));
			menu[i].setFont(new Font("MD개성체", Font.BOLD, 20));

			bar.add(menu[i]);

		}
		setJMenuBar(bar);
		add(container);

		// create page
		pages[0] = new MemberMain(this);
		pages[1] = new MenuMain(this); //세원
		pages[2] = new TableMain(this);
		pages[3] = new ChatMain(this);
		pages[4] = new GameMain(this);
		pages[5] = new OrderMain(this);
		pages[6] = new MoneyMain(this);
		//더 붙여야함
		

		// add panel index 0
		container.add(pages[0]);
		container.add(pages[1]);
		container.add(pages[2]);
		container.add(pages[3]);
		container.add(pages[4]);
		container.add(pages[5]);
		container.add(pages[6]);

		// 각 메누마다 적절한 메서드 호출
		menu[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(0);
			}
		});
		menu[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(1);
			}
		});
		menu[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(2);
			}
		});
		menu[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(3);
			}
		});
		menu[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(4);
			}
		});
		menu[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(5);
			}
		});
		
		menu[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showPage(6);
			}
		});
		
		
		//윈도우 리스너 구현하기!!
		addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent e) {
	            connectionManager.closeDB(con);
	            System.exit(0);
	         }
	      });

		setSize(1400, 1000);
		setLocationRelativeTo(null);
		setVisible(false);
		pages[0].setVisible(true);
		pages[1].setVisible(false);
		pages[2].setVisible(false);
		pages[3].setVisible(false);
		pages[4].setVisible(false);
		pages[5].setVisible(false);
		pages[6].setVisible(false);

		new Login(this);
	}

	public void showPage(int page) {
		for (int i = 0; i < pages.length; i++) {
			if (i == page) {
				pages[i].setVisible(true);
				System.out.println(i);
			} else {
				pages[i].setVisible(false);
			}
		}
	}
	
	//getter!! -> 남이 내꺼를 가져가게 해줌
	public Connection getCon() {
		return con;
	}

}
 */
