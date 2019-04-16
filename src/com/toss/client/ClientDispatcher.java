package com.toss.client;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ClientDispatcher {
	JSONParser jsonParser;
	ClientServer clientServer;
	ClientController controller;
	ClientMain clientMain;
	String TAG = this.getClass().getName();
	int account_count;
	int account_list_count=0;
	//account_num type_name receive_name amount left_balance history_date
	ArrayList account_num_list=new ArrayList();
	ArrayList type_name_list=new ArrayList();
	ArrayList receive_name_list=new ArrayList();
	ArrayList amount_list=new ArrayList();
	ArrayList left_balance_list=new ArrayList();
	ArrayList history_date_list=new ArrayList();
	
	
	public ClientDispatcher(ClientMain clientMain, ClientServer clientServer) {
		this.clientMain = clientMain;
		this.clientServer = clientServer;
		controller = new ClientController(clientMain, clientServer, this);
	}

	public void dispatch(String msg) {
		jsonParser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) jsonParser.parse(msg);
			String requestType = (String) obj.get("requestType");

			if (requestType.equals("sendip")) {
				String ip = (String) obj.get("ip");
				clientMain.clientInfo.setIp(ip);
				// System.out.println(TAG+":내 ip는 "+clientMain.clientInfo.getIp());
			} else if (requestType.equals("checkidback")) {
				String idcheck = (String) obj.get("idcheck");
				String member_id = (String) obj.get("member_id");

				if (idcheck.equals("true")) {
					clientMain.clientInfo.setCheckId(member_id);
				}
				controller.setCheckIdBack(idcheck);
			} else if (requestType.equals("loginbacko")) {
				String check = (String) obj.get("check");
				String member_id = (String) obj.get("member_id");
				String member_pw = (String) obj.get("member_pw");
				int member_pri = Integer.valueOf((String) obj.get("member_pri"));

				if (check.equals("true")) {
					clientMain.clientInfo.setId(member_id);
					clientMain.clientInfo.setPw(member_pw);
					clientMain.clientInfo.setCheckLogin(check);
					clientMain.clientInfo.setMemberPri(member_pri);
				}

				controller.setLoginBack(check);
			} else if (requestType.equals("loginbackx")) {
				String check = (String) obj.get("check");
				String member_id = (String) obj.get("member_id");
				String member_pw = (String) obj.get("member_pw");

				if (check.equals("true")) {
					clientMain.clientInfo.setId(member_id);
					clientMain.clientInfo.setPw(member_pw);
					clientMain.clientInfo.setCheckLogin(check);
				}
				controller.setLoginBack(check);
			} else if (requestType.equals("checkaccountback")) {
				String check = (String) obj.get("check");
				String account_num = (String) obj.get("account_num");

				if (check.equals("true")) {
					clientMain.clientInfo.setAccountNum(account_num);
				}

			} else if (requestType.equals("getaccountback")) {
				if (account_count == 0) {
					clientMain.clientInfo.removeAccountBalanceList();
					clientMain.clientInfo.removeBankNameList();
					clientMain.clientInfo.removeAccountNumList();
				}
				String bank_name = (String) obj.get("bank_name");
				String account_num = (String) obj.get("account_num");
				int account_balance = Integer.valueOf((String) obj.get("account_balance"));
				int total = Integer.valueOf((String) obj.get("total"));

				clientMain.clientInfo.setBankNameList(bank_name);
				clientMain.clientInfo.setAcountNumList(account_num);
				clientMain.clientInfo.addAccountBalanceList(account_balance);
				account_count++;

				if (account_count == total) {
					clientMain.login.home.balance.showAccount();
					System.out.println(TAG + ":" + clientMain.clientInfo.getBankNameList().size());
					account_count = 0;
				}

			} else if (requestType.equals("createaccountback")) {

				String bank_name = (String) obj.get("bank_name");
				String account_num = (String) obj.get("account_num");
				int account_balance = Integer.valueOf((String) obj.get("account_balance"));

				clientMain.clientInfo.setBankNameList(bank_name);
				clientMain.clientInfo.setAcountNumList(account_num);
				clientMain.clientInfo.addAccountBalanceList(account_balance);

				clientMain.login.home.balance.showAccount();

			} else if (requestType.equals("senddepositback")) {

				String bank_name = (String) obj.get("bank_name");
				String account_num = (String) obj.get("account_num");
				int account_balance = Integer.valueOf((String) obj.get("account_balance"));
				int index = Integer.valueOf((String) obj.get("index"));

				clientMain.clientInfo.setAccountBalanceList(index, account_balance);
				//System.out.println(TAG+":index"+index);
				//System.out.println(TAG+":account_balance"+account_balance);
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getAcountBalanceList().get(index));
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getBankNameList().get(index));
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getAcountNumList().get(index));
				clientMain.login.home.balance.showAccount();

			}else if (requestType.equals("sendwithdrawback")) {

				String bank_name = (String) obj.get("bank_name");
				String account_num = (String) obj.get("account_num");
				int account_balance = Integer.valueOf((String) obj.get("account_balance"));
				int index = Integer.valueOf((String) obj.get("index"));

				clientMain.clientInfo.setAccountBalanceList(index, account_balance);
				//System.out.println(TAG+":index"+index);
				//System.out.println(TAG+":account_balance"+account_balance);
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getAcountBalanceList().get(index));
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getBankNameList().get(index));
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getAcountNumList().get(index));
				clientMain.login.home.balance.showAccount();
			}
			else if (requestType.equals("checksendmoneyback")) {

				String check = (String) obj.get("check");
				
				//System.out.println(TAG+":index"+index);
				//System.out.println(TAG+":account_balance"+account_balance);
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getAcountBalanceList().get(index));
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getBankNameList().get(index));
				//System.out.println(TAG+":방금 바꾼 값"+clientMain.clientInfo.getAcountNumList().get(index));
				if(check.equals("true")) {
				clientMain.login.home.send.sendForm.success();
				}else {
					clientMain.login.home.send.sendForm.fail();
				}
			}
			else if (requestType.equals("sendreceivemoneyback")) {
				String bank_name = (String) obj.get("bank_name");
				String account_num = (String) obj.get("account_num");
				int account_balance = Integer.valueOf((String) obj.get("account_balance"));
				
				for(int i=0;i<clientMain.clientInfo.getAcountNumList().size();i++) {
					if(clientMain.clientInfo.getAcountNumList().get(i).equals(account_num)) {
						JOptionPane.showMessageDialog(clientMain.login.home, "입금되었습니다!!");
						clientMain.clientInfo.setAccountBalanceList(i, account_balance);
						clientMain.login.home.balance.showAccount();
						break;
					}
					
				}
				
			}
			else if (requestType.equals("accounthistoryback")) {
				String account_num = (String) obj.get("account_num");
				String type_name = (String) obj.get("type_name");
				String receive_name = (String) obj.get("receive_name");
				int amount = Integer.valueOf((String) obj.get("amount"));
				int left_balance = Integer.valueOf((String) obj.get("left_balance"));
				String history_date = (String) obj.get("history_date");
				int total = Integer.valueOf((String) obj.get("total"));
			
				account_num_list.add(account_num);
				type_name_list.add(type_name);
				receive_name_list.add(receive_name);
				amount_list.add((Integer)amount);
				left_balance_list.add((Integer)left_balance);
				history_date_list.add(history_date);
				account_list_count++;
				
				if(account_list_count==total) {
					new AccountHistory(account_num_list, type_name_list, receive_name_list, amount_list, left_balance_list, history_date_list,total);
					
					
				}
				
			}
			
			
			

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
