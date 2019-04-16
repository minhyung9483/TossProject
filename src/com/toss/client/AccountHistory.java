package com.toss.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;



public class AccountHistory extends JFrame{
	String TAG = this.getClass().getName();
	Color dodgerblue=new Color(0,90,156);
	
	JTable table;
	JScrollPane scroll;
	HistoryTableModel model;
	
	JPanel p_ok;
	JLabel l_ok;

	
	public AccountHistory(ArrayList account_num_list,ArrayList type_name_list,ArrayList receive_name_list,ArrayList amount_list,ArrayList left_balance_list,ArrayList history_date_list,int total) {
		
		setSize(1000,740);
		setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("   계좌조회");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		label.setBackground(Color.WHITE);
		label.setBounds(0, 0, 100, 35);
		panel_1.add(label);
		
		 p_ok = new JPanel();
		p_ok.setForeground(Color.BLACK);
		p_ok.setBackground(Color.WHITE);
		p_ok.setBounds(442, 612, 100, 35);
		panel_1.add(p_ok);
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
		table = new JTable();
		table.setBackground(Color.WHITE);
		scroll = new JScrollPane(table);
		scroll.setLocation(42, 80);
		scroll.setSize(900, 500);
		scroll.setPreferredSize(new Dimension(1000, 700));
		model = new HistoryTableModel();
		
		/*ArrayList account_num_list=new ArrayList();
		ArrayList type_name_list=new ArrayList();
		ArrayList receive_name_list=new ArrayList();
		ArrayList amount_list=new ArrayList();
		ArrayList left_balance_list=new ArrayList();
		ArrayList history_date_list=new ArrayList();
		Object[][] data= null;
		account_num_list=account_num_list2;
		type_name_list=type_name_list2;
		receive_name_list=receive_name_list2;
		amount_list=amount_list2;
		left_balance_list=left_balance_list2;
		history_date_list=history_date_list2;*/
		Object[][] data = new Object[total][model.columnTitle.length];
		for(int i=0;i<account_num_list.size();i++) {
			data[i][0]=(String)account_num_list.get(i);
			data[i][1]=(String)type_name_list.get(i);
			data[i][2]=(String)receive_name_list.get(i);
			data[i][3]=(Integer)amount_list.get(i);
			data[i][4]=(Integer)left_balance_list.get(i);
			data[i][5]=(String)history_date_list.get(i);
		}
		model.data=data;
		
		table.setRowHeight(65);
		table.setModel(model );
		table.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		table.updateUI();
		panel_1.add(scroll);
		setVisible(true);
	}
	
	public void ok() {
		setVisible(false);
	}
	
	public void setData(ArrayList account_num_list2,ArrayList type_name_list2,ArrayList receive_name_list2,ArrayList amount_list2,ArrayList left_balance_list2,ArrayList history_date_list2){
		ArrayList account_num_list=new ArrayList();
		ArrayList type_name_list=new ArrayList();
		ArrayList receive_name_list=new ArrayList();
		ArrayList amount_list=new ArrayList();
		ArrayList left_balance_list=new ArrayList();
		ArrayList history_date_list=new ArrayList();
		Object[][] data= null;
		account_num_list=account_num_list2;
		type_name_list=type_name_list2;
		receive_name_list=receive_name_list2;
		amount_list=amount_list2;
		left_balance_list=left_balance_list2;
		history_date_list=history_date_list2;
		
		for(int i=0;i<account_num_list.size();i++) {
			data[i][0]=account_num_list.get(i);
			data[i][1]=type_name_list.get(i);
			data[i][2]=receive_name_list.get(i);
			data[i][3]=amount_list.get(i);
			data[i][4]=left_balance_list.get(i);
			data[i][5]=history_date_list.get(i);
		}
		model.data=data;
	}
	
	
}
