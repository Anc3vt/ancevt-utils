package ru.ancevt.util.texttable;

import java.util.List;

class TextTableRow {
	
	private Object[] data;
	private Object key;
	
	public void set(Object[] rowData) {
		this.data = rowData;
	}
	
	public void set(List<Object> rowData) {
		this.data = rowData.toArray(new Object[] {});
	}
	
	public Object[] rowData() {
		return data;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}
}
