package com.toss.server;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerDispatcher {
	String TAG = this.getClass().getName();
	JSONParser jsonParser;
	ServerMain serverMain;
	ServerController controller;

	public ServerDispatcher(ServerMain serverMain) {
		this.serverMain = serverMain;
		controller = new ServerController(serverMain);
	}

	public void dispatch(String msg) {
		jsonParser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) jsonParser.parse(msg);
			String requestType = (String) obj.get("requestType");
			
			if (requestType.equals("checkid")) {
				String ip = (String) obj.get("ip");
				String member_id = (String) obj.get("member_id");
				
				controller.checkId(ip, member_id);
			}else if (requestType.equals("join")) {
				String ip = (String) obj.get("ip");
				String member_id = (String) obj.get("member_id");
				String member_pw = (String) obj.get("member_pw");
				String member_name = (String) obj.get("member_name");
				
				controller.join(ip, member_id,member_pw,member_name);
			} else if (requestType.equals("login")) {
				String ip = (String) obj.get("ip");
				String member_id = (String) obj.get("member_id");
				String member_pw = (String) obj.get("member_pw");
				
				controller.login(ip, member_id,member_pw);
			} else if (requestType.equals("checkaccount")) {
				String ip = (String) obj.get("ip");
				String account_num = (String) obj.get("account_num");
				
				controller.checkAccount(ip,account_num);
			} else if (requestType.equals("createaccount")) {
				String ip = (String) obj.get("ip");
				String account_num = (String) obj.get("account_num");
				int bank=Integer.valueOf((String)obj.get("bank"));
				int member_pri=Integer.valueOf((String)obj.get("member_pri"));
				
				controller.createAccount(ip,account_num,bank,member_pri);
			}else if (requestType.equals("getaccount")) {
				String ip = (String) obj.get("ip");
				int member_pri=Integer.valueOf((String)obj.get("member_pri"));
				controller.getAccount(ip,member_pri);
			}
			else if (requestType.equals("senddeposit")) {
				String ip = (String) obj.get("ip");
				String bank_name = (String) obj.get("bank_name");
				String account_num = (String) obj.get("account_num");
				int account_balance=Integer.valueOf((String)obj.get("account_balance"));
				int deposit_money=Integer.valueOf((String)obj.get("deposit_money"));
				int member_pri=Integer.valueOf((String)obj.get("member_pri"));
				int index=Integer.valueOf((String)obj.get("index"));
				controller.getSendDeposit(ip,bank_name,account_num,account_balance,deposit_money,member_pri,index);
				controller.depositHistory(ip, bank_name, account_num, account_balance, deposit_money, member_pri, index);
			}
			else if (requestType.equals("sendwithdraw")) {
				String ip = (String) obj.get("ip");
				String bank_name = (String) obj.get("bank_name");
				String account_num = (String) obj.get("account_num");
				int account_balance=Integer.valueOf((String)obj.get("account_balance"));
				int withdraw_money=Integer.valueOf((String)obj.get("withdraw_money"));
				int member_pri=Integer.valueOf((String)obj.get("member_pri"));
				int index=Integer.valueOf((String)obj.get("index"));
				controller.getSendWithdraw(ip,bank_name,account_num,account_balance,withdraw_money,member_pri,index);
				controller.withdrawHistory(ip, bank_name, account_num, account_balance, withdraw_money, member_pri, index);
			}
			//Ÿ��v,�ƾ���v,�������̸�v,�����¹�ȣv,���ܾ�v,�����ݾ�v,��ȸ����ȣv,�������ε���v,�������pri,�����¹�ȣ
			else if (requestType.equals("sendmoney")) {
				String ip = (String) obj.get("ip");
				String bank_name = (String) obj.get("bank_name");
				String account_num = (String) obj.get("account_num");
				int account_balance=Integer.valueOf((String)obj.get("account_balance"));
				int amount=Integer.valueOf((String)obj.get("amount"));
				int member_pri=Integer.valueOf((String)obj.get("member_pri"));
				int index=Integer.valueOf((String)obj.get("index"));
				int bank_pri_receiver=Integer.valueOf((String)obj.get("bank_pri_receiver"));
				String account_num_receiver = (String) obj.get("account_num_receiver");

				controller.checkSendMoney(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver);
				//controller.sendHistory(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver);
				//controller.receiveHistory(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver);
			}
			else if (requestType.equals("accounthistory")) {
				String ip = (String) obj.get("ip");
				String account_num = (String) obj.get("account_num");

				controller.checkAccountHistory(ip,account_num);
				//controller.sendHistory(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver);
				//controller.receiveHistory(ip,bank_name,account_num,account_balance,amount,member_pri,index,bank_pri_receiver,account_num_receiver);
			}
			
		} catch (ParseException e){
			e.printStackTrace();
		}
	}
}
