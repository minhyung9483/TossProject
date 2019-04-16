package com.toss.client;

import java.util.ArrayList;
import java.util.Date;

//등록시 값이 부여됨..
public class ClientInfo {
	String TAG = this.getClass().getName();
	ClientMain clientMain;

	private Date date; // 입장 날짜 //디폴트 시스데이트
	private String ip;
	private String member_id;
	private String member_pw;
	private String checkId;
	private String checkLogin;
	private String account_num;
	private int member_pri;
	private int total_balance;
	private ArrayList<String> bank_name_list=new ArrayList();
	private ArrayList<String> account_num_list=new ArrayList();
	private ArrayList<Integer> account_balance_list=new ArrayList();
	
	public ClientInfo(ClientMain clientMain) {
		this.clientMain=clientMain;
	}

	// 날짜
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	// tableIp
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getId() {
		return member_id;
	}

	public void setId(String member_id) {
		this.member_id = member_id;
	}
	
	public String getPw() {
		return member_pw;
	}

	public void setPw(String member_pw) {
		this.member_pw = member_pw;
	}
	
	public String getCheckLogin() {
		return member_pw;
	}

	public void setCheckLogin(String checkLogin) {
		this.checkLogin = checkLogin;
	}

	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getAccountNum() {
		return account_num;
	}
	public void setAccountNum(String account_num) {
		this.account_num = account_num;
	}
	public int getMemberPri() {
		return member_pri;
	}
	public void setMemberPri(int member_pri) {
		this.member_pri = member_pri;
	}
	public ArrayList getBankNameList() {
		return bank_name_list;
	}
	public void setBankNameList(String bank_name) {
		bank_name_list.add(bank_name);
	}
	public void removeBankNameList() {
		bank_name_list.removeAll(bank_name_list);
	}
	public ArrayList getAcountNumList() {
		return account_num_list;
	}
	public void setAcountNumList(String account_num) {
		account_num_list.add(account_num);
		}
	public void removeAccountNumList() {
		account_num_list.removeAll(account_num_list);
	}
	public ArrayList getAcountBalanceList() {
		return account_balance_list;
	}
	public void addAccountBalanceList(int account_balance) {
		account_balance_list.add((Integer)account_balance);
	}
	public void removeAccountBalanceList() {
		account_balance_list.removeAll(account_balance_list);
	}
	public void setAccountBalanceList(int index,int account_balance) {
		account_balance_list.set(index, account_balance);
	}
	public int getTotalBalance() {
		return total_balance;
	}
	public void setTotalBalance(int total_balance) {
		this.total_balance=total_balance;
	}
	
	
}