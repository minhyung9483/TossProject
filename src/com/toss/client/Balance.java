package com.toss.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class Balance extends JPanel {
	String TAG = this.getClass().getName();
	ClientMain clientMain;
	ClientServer clientServer;
	Home home;
	CreateAccount createAccount;
	Color dodgerblue = new Color(0, 90, 156);
	int total_balance;
	JPanel p_create;
	JLabel l_create;
	JTree tree;
	JLabel l_total;
	DefaultMutableTreeNode root;
	String account_num;
	String search_account_num;
	ArrayList <DefaultMutableTreeNode>treeList=new ArrayList();

	public Balance(ClientMain clientMain, ClientServer clientServer, Home home) {
		this.clientMain = clientMain;
		this.clientServer = clientServer;
		this.home = home;

		setPreferredSize(new Dimension(434, 651));
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel p_up = new JPanel();
		p_up.setBounds(0, 0, 434, 50);
		p_up.setLayout(null);
		p_up.setBackground(Color.WHITE);
		add(p_up);

		JLabel l_sub = new JLabel("   조회");
		l_sub.setHorizontalAlignment(SwingConstants.LEFT);
		l_sub.setForeground(Color.BLACK);
		l_sub.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		l_sub.setBackground(Color.WHITE);
		l_sub.setBounds(0, 0, 100, 35);
		p_up.add(l_sub);
		
				JPanel p_create_1 = new JPanel();
				p_create_1.setBounds(334, 0, 100, 35);
				p_up.add(p_create_1);
				p_create_1.setBackground(Color.WHITE);
				p_create_1.setLayout(new BorderLayout(0, 0));
				
						JLabel l_create_1 = new JLabel("계좌생성");
						l_create_1.setHorizontalAlignment(SwingConstants.CENTER);
						l_create_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
						p_create_1.add(l_create_1, BorderLayout.CENTER);
						
								p_create_1.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										if (JOptionPane.showConfirmDialog(home, "신규 계좌를 개설하시겠습니까?") == JOptionPane.OK_OPTION) {
											create();
										}
									}
						
									public void mouseEntered(MouseEvent e) {
										p_create_1.setBackground(dodgerblue);
										l_create_1.setForeground(Color.WHITE);
									}
						
									public void mouseExited(MouseEvent e) {
										p_create_1.setBackground(Color.WHITE);
										l_create_1.setForeground(Color.BLACK);
									}
								});

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 50, 434, 100);
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel l_totalname = new JLabel("  잔액");
		panel.add(l_totalname);
		l_totalname.setHorizontalAlignment(SwingConstants.CENTER);
		l_totalname.setFont(new Font("배달의민족 주아", Font.BOLD, 30));
		l_totalname.setBackground(Color.WHITE);

		l_total = new JLabel(total_balance + "원");
		l_total.setFont(new Font("배달의민족 주아", Font.BOLD, 30));
		l_total.setBackground(Color.WHITE);
		panel.add(l_total);

		JPanel p_tree = new JPanel();
		p_tree.setBackground(Color.WHITE);
		p_tree.setBounds(0, 150, 434, 450);
		add(p_tree);

		root = new DefaultMutableTreeNode("내 계좌");
		tree = new JTree(root);
		tree.setBorder(new LineBorder(new Color(0, 90, 156), 4));
		tree.setBounds(55, 44, 324, 362);
		tree.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		
		
		
		JPanel p_down = new JPanel();
		p_down.setBackground(Color.WHITE);
		p_down.setBounds(0, 600, 434, 50);
		add(p_down);
		p_down.setLayout(null);
		p_tree.setLayout(null);

		//getMyAccount();
		
		p_tree.add(tree);
		
tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				String str=e.getNewLeadSelectionPath().toString();
				String[] str2=str.split(",");
				if(str2.length==3) {
				
				str=str2[1];
				str=str.substring(str.length()-15, str.length()-1);
				search_account_num=str;
				
				accountHistory(search_account_num);
				}
			}
		});


	}
	public void getMyAccount() {
		//System.out.println(TAG+":"+clientMain.clientInfo.getMemberPri());

		clientServer.sendGetAccount("getaccount",clientMain.clientInfo.getIp(),Integer.toString(clientMain.clientInfo.getMemberPri()));
		
	}
	
	public void showAccount() {
		root.removeAllChildren();
		int total=0;
		System.out.println(TAG+":");
		for(int i=0;i<clientMain.clientInfo.getAcountNumList().size();i++) {
			DefaultMutableTreeNode account = new DefaultMutableTreeNode(clientMain.clientInfo.getBankNameList().get(i)+ "("+clientMain.clientInfo.getAcountNumList().get(i)+")" );
			DefaultMutableTreeNode balance = new DefaultMutableTreeNode("잔액 : "+clientMain.clientInfo.getAcountBalanceList().get(i)+ "원");
			total+=(Integer)clientMain.clientInfo.getAcountBalanceList().get(i);
			account.add(balance);
			root.add(account);
			
			
			//System.out.println(TAG+":"+clientMain.clientInfo.getBankNameList().get(i));
		}
		total_balance=total;
		tree.updateUI();
		clientMain.clientInfo.setTotalBalance(total_balance);
		l_total.setText(total_balance+"원");

	}

	public void create() {
		createAccount = new CreateAccount(clientMain, clientServer, home, this);
		// createAccount.setVisible(true);
		home.setVisible(false);
	}

	public void setAccountText(String account_num) {
		this.account_num=account_num;
	}
	
	public void accountHistory(String search_account_num) {
		System.out.println(TAG+":"+search_account_num);
		clientServer.sendAccountHistory("accounthistory", clientMain.clientInfo.getIp(),search_account_num);
		
	}
}
