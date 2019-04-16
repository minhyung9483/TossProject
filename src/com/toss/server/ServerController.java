package com.toss.server;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ServerController {
	String TAG = this.getClass().getName();
	ServerMain serverMain;
	public ServerController(ServerMain serverMain) {
		this.serverMain = serverMain;
		// TODO Auto-generated constructor stub
	}
	
	public void checkId(String ip,String member_id) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check = "";
		
		String sql = "select * from member where member_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = "false"; // 중복 아이디가 있는경우
			} else {
				check = "true"; // 가입가능 아이디
			}
			serverMain.sendCheckIdBack("checkidback",ip,check,member_id);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
	}
	
	public void join(String ip,String member_id,String member_pw,String member_name) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		
		String sql = "insert into member(member_pri ,member_id, member_pw ,member_name)  values(seq_member.nextval,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);// 
			pstmt.setString(2, member_pw);// 
			pstmt.setString(3,member_name);// 

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt);
	
	}
	
	public void login(String ip,String member_id,String member_pw) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check = "";
		int member_pri=0;
		
		String sql = "select * from member where member_id=? and member_pw=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_pw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member_pri=rs.getInt("member_pri");
				check = "true"; // 일치
				serverMain.sendLoginBack("loginbacko",ip,check,member_id,member_pw,Integer.toString(member_pri));
			} else {
				check = "false"; // 불일치
				serverMain.sendLoginBack("loginbackx",ip,check,member_id,member_pw);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
	}

	
	public void checkAccount(String ip,String account_num) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String check = "";
		
		String sql = "select * from account where account_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = "false"; // 중복
			} else {
				check = "true"; // 사용가능
			}
			serverMain.sendCheckAccountBack("checkaccountback",ip,check,account_num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
	}
	
	public void createAccount(String ip,String account_num,int bank,int member_pri) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		String bank_name="";
		
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		
		String sql2 = "select * from bank where bank_pri=?";
		try {
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, bank);
			rs2 = pstmt2.executeQuery();
			if (rs2.next()) {
				bank_name=rs2.getString("bank_name");
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt2, rs2);
		
		
		String sql = "insert into account(account_pri ,member_pri, bank_pri ,account_num)  values(seq_account.nextval,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_pri);// 
			pstmt.setInt(2, bank);// 
			pstmt.setString(3,account_num);// 

			int result=pstmt.executeUpdate();
			if(result!=0) {
				int account_balance=0;
				serverMain.sendCreateAccountBack("createaccountback",ip,bank_name,account_num,Integer.toString(account_balance));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt);
	}
	
	public void getAccount(String ip,int member_pri) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		String sql = "select bank_name,account_num,account_balance from account,bank where member_pri=? and account.bank_pri=bank.bank_pri order by account_pri asc";
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, member_pri);
			rs = pstmt.executeQuery();
			rs.last();
			int total=rs.getRow();
			rs.beforeFirst();
			for(int i=0;i<total;i++) {
				rs.next();
				String bank_name=rs.getString("bank_name");
				String account_num=rs.getString("account_num");
				int account_balance=rs.getInt("account_balance");
				serverMain.sendGetAccountBack("getaccountback",ip,bank_name,account_num,Integer.toString(account_balance),Integer.toString(total));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
	}
	
	public void getSendDeposit(String ip,String bank_name,String account_num,int account_balance,int deposit_money,int member_pri,int index) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum=account_balance+deposit_money;
		//System.out.println(TAG+","+ip+","+bank_name+","+account_num+","+Integer.toString(sum)+","+Integer.toString(index));

		String sql = "update account set account_balance=? where member_pri=? and account_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sum);
			pstmt.setInt(2, member_pri);
			pstmt.setString(3, account_num);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				
				serverMain.sendDepositBack("senddepositback",ip,bank_name,account_num,Integer.toString(sum),Integer.toString(index));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		
	}
	
	public void depositHistory(String ip,String bank_name,String account_num,int account_balance,int deposit_money,int member_pri,int index) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum=account_balance+deposit_money;
		
		String deposit="입금";
		String sql = "insert into history(history_pri ,member_pri, type_name,account_num,amount,left_balance)  values(seq_history.nextval,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_pri);// 
			pstmt.setString(2, deposit);// 
			pstmt.setString(3, account_num);// 
			pstmt.setInt(4, deposit_money);// 
			pstmt.setInt(5, sum);// 

			int result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt);
	}

	
	public void getSendWithdraw(String ip,String bank_name,String account_num,int account_balance,int withdraw_money,int member_pri,int index) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sub=account_balance-withdraw_money;
		//System.out.println(TAG+","+ip+","+bank_name+","+account_num+","+Integer.toString(sub)+","+Integer.toString(index));
		
		String sql = "update account set account_balance=? where member_pri=? and account_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sub);
			pstmt.setInt(2, member_pri);
			pstmt.setString(3, account_num);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				
				serverMain.sendWithdrawBack("sendwithdrawback",ip,bank_name,account_num,Integer.toString(sub),Integer.toString(index));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		
		
	}
	
	public void withdrawHistory(String ip,String bank_name,String account_num,int account_balance,int withdraw_money,int member_pri,int index) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		int sub=account_balance-withdraw_money;
		String withdraw="출금";
		System.out.println(TAG+","+ip+","+bank_name+","+account_num+","+Integer.toString(sub)+","+Integer.toString(index));

		String sql = "insert into history(history_pri ,member_pri, type_name,account_num,amount,left_balance)  values(seq_history.nextval,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_pri);// 
			pstmt.setString(2, withdraw);// 
			pstmt.setString(3, account_num);// 
			pstmt.setInt(4, withdraw_money);// 
			pstmt.setInt(5, sub);//  

			int result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt);
			
	}
	
public void checkSendMoney(String ip,String bank_name,String account_num,int account_balance,int amount,int member_pri,int index,int bank_pri_receiver,String account_num_receiver) {
	Connection con = serverMain.getCon();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String check="";

	
	String sql = "select * from account,bank where bank.bank_pri=? and account_num=? and bank.bank_pri=account.bank_pri";
	try {
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bank_pri_receiver);
		pstmt.setString(2, account_num_receiver);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			check="true";
			
			SendMoney(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver);
			ReceiveMoney(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver);
		}else {
			check="false";
			
		}
		serverMain.sendCheckSendMoneyBack("checksendmoneyback",ip,check);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}serverMain.connectionManager.closeDB(pstmt, rs);
		
		
	}

	public void SendMoney(String ip,String bank_name,String account_num,int account_balance,int amount,int member_pri,int index,int bank_pri_receiver,String account_num_receiver) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sub=account_balance-amount;
		//System.out.println(TAG+","+ip+","+bank_name+","+account_num+","+Integer.toString(sub)+","+Integer.toString(index));
		
		String sql = "update account set account_balance=? where member_pri=? and account_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sub);
			pstmt.setInt(2, member_pri);
			pstmt.setString(3, account_num);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				
				serverMain.sendWithdrawBack("sendwithdrawback",ip,bank_name,account_num,Integer.toString(sub),Integer.toString(index));
				sendHistory(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		
		
	}
	
	public void ReceiveMoney(String ip,String bank_name,String account_num,int account_balance,int amount,int member_pri,int index,int bank_pri_receiver,String account_num_receiver) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sum=getReceiverBalance(bank_pri_receiver, account_num_receiver)+amount;
		//System.out.println(TAG+","+ip+","+bank_name+","+account_num+","+Integer.toString(sum)+","+Integer.toString(index));

		String sql = "update account set account_balance=? where bank_pri=? and account_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sum);
			pstmt.setInt(2, bank_pri_receiver);
			pstmt.setString(3, account_num_receiver);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				
				getReceiverInfo(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver,sum);
				//serverMain.sendDepositBack("senddepositback",ip,bank_name,account_num,Integer.toString(sum),Integer.toString(index));
				receiveHistory(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver,sum);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		
		
	}
	
	public int getReceiverBalance(int bank_pri_receiver,String account_num_receiver) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int receiver_balance=0;
		
		String sql = "select account_balance from account where bank_pri=? and account_num=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bank_pri_receiver);
			pstmt.setString(2, account_num_receiver);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				receiver_balance=rs.getInt("account_balance");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		return receiver_balance;
	}
	
	public void sendHistory(String ip,String bank_name,String account_num,int account_balance,int amount,int member_pri,int index,int bank_pri_receiver,String account_num_receiver) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		int sub=account_balance-amount;
		String withdraw="출금";
		String receive_name=getReceiverName(account_num_receiver);
		
		String sql = "insert into history(history_pri ,member_pri, type_name,receive_name,account_num,amount,left_balance)  values(seq_history.nextval,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_pri);// 
			pstmt.setString(2, withdraw);// 
			pstmt.setString(3, receive_name);// 
			pstmt.setString(4, account_num);// 
			pstmt.setInt(5, amount);// 
			pstmt.setInt(6, sub);//  

			int result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt);
		
		
	}
	
	public void receiveHistory(String ip,String bank_name,String account_num,int account_balance,int amount,int member_pri,int index,int bank_pri_receiver,String account_num_receiver,int sum) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int member_pri_receiver=getMemberPri(account_num_receiver);
		String receive_name=getReceiverName(account_num);
		
		System.out.println();
		String deposit="입금";
		String sql = "insert into history(history_pri ,member_pri, type_name,receive_name,account_num,amount,left_balance)  values(seq_history.nextval,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_pri_receiver);// 
			pstmt.setString(2, deposit);// 
			pstmt.setString(3, receive_name);// 
			pstmt.setString(4, account_num_receiver);// 
			pstmt.setInt(5, amount);// 
			pstmt.setInt(6, sum);// 

			int result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt);
		
		
	}
	
	public void getReceiverInfo(String ip,String bank_name,String account_num,int account_balance,int amount,int member_pri,int index,int bank_pri_receiver,String account_num_receiver,int sum) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String bank_name_receiver="";

		
		String sql = "select bank_name from bank,account where account_num=? and bank.bank_pri=account.bank_pri";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_num_receiver);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bank_name_receiver=rs.getString("bank_name");
				
				serverMain.sendReceiveMoneyBack("sendreceivemoneyback",ip,bank_name_receiver,account_num_receiver,Integer.toString(sum));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		
	}
	
	public String getReceiverName(String account_num) {
		String receive_name="";
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		String sql = "select member_name from member,account where account_num=? and account.member_pri=member.member_pri";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				receive_name=rs.getString("member_name");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		
		
		
		return receive_name;
	}
	public int getMemberPri(String account_num) {
		int member_pri=0;
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select member.member_pri from member,account where account_num=? and account.member_pri=member.member_pri";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member_pri=rs.getInt("member_pri");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		
		
		
		return member_pri;
	}
	
	public void checkAccountHistory(String ip,String account_num) {
		Connection con = serverMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select account_num,type_name,receive_name,amount,left_balance,history_date from history where account_num=? order by history_date asc";
		try {
						
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, account_num);
			rs = pstmt.executeQuery();
			
			rs.last();
			int total=rs.getRow();
			rs.beforeFirst();
			for(int i=0;i<total;i++) {
				rs.next();
				String type_name=rs.getString("type_name");
				String receive_name=rs.getString("receive_name");
				int amount=rs.getInt("amount");
				int left_balance=rs.getInt("left_balance");
				String history_date=rs.getString("history_date");
				
				serverMain.sendAccountHistoryBack("accounthistoryback",ip,account_num,type_name,receive_name,Integer.toString(amount),Integer.toString(left_balance),history_date,Integer.toString(total));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}serverMain.connectionManager.closeDB(pstmt, rs);
		
	}
	

	
	

}
