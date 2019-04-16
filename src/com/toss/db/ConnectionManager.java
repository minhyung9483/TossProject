/*
 *   DB Connection을 얻고 관련된 자원을 닫는 중복된 코드를 방지하기 위한 클래스 
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
				System.out.println(TAG+" 접속 실패");
			} else {
				System.out.println(TAG+" 접속 성공");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	// 데이터베이스 관련 자원 닫기
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

	// DML만 날리기
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