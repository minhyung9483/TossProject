/*
 *   DB Connection�� ��� ���õ� �ڿ��� �ݴ� �ߺ��� �ڵ带 �����ϱ� ���� Ŭ���� 
 */

package com.toss.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
	String TAG = this.getClass().getName();
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String user = "hunter";
	private String password = "1234";

	public Connection connect() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			if (con == null) {
				System.out.println(TAG+" ���� ����");
			} else {
				System.out.println(TAG+" ���� ����");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// �����ͺ��̽� ���� �ڿ� �ݱ�
	public void closeDB(Connection con) { // Connection con
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// DML�� ������
	public void closeDB(PreparedStatement pstmt) { // Connection con
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeDB(PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}