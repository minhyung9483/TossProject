package com.toss.client;

import javax.swing.table.AbstractTableModel;

public class HistoryTableModel extends AbstractTableModel{
	String[] columnTitle = {"계좌번호","입출금","이름","금액","잔액","날짜"};
	Object[][] data= {};	
	public String getColumnName(int col) {
		return columnTitle[col];
	}
	public int getColumnCount() {
		return columnTitle.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
}
