package ru.ancevt.util.texttable;

import java.util.ArrayList;
import java.util.List;

public class TextTable {
	
	private static final int DEFAULT_MAX_STRING_LENGTH = 100;
	
	public static void main(String[] args) {
/*
		+----+--------------------+------------+------+------+
		| id | name               | country_id | code | desc |
		+----+--------------------+------------+------+------+
		|  1 | Москва             | 1          | MOS  | NULL |
		|  2 | Нью-Йорк           | 3          | NY   | NULL |
		|  3 | Киев               | 2          | KI   | NULL |
		|  6 | Вашингтон          | 3          | WS   | NULL |
		|  7 | Самара             | 1          | SM   | NULL |
		+----+--------------------+------------+------+------+
*/
		
		final TextTable textTable = new TextTable();
		textTable.setColumnNames(new String[] {"id", "name", "code"});
		textTable.setMaxStringLength(15);
		textTable.addRow(new String[] {"1", "Moscow", "RU"});
		textTable.addRow(new String[] {"2", "St.Petersburg", "RU"});
		textTable.addRow(new Object[] {3, "Rostov-on-Don", ""});
		
		System.out.println(textTable.render());
	}
	
	private List<TextTableRow> rows;
	private String[] columnNames;
	private int[] columnSizes;
	private int maxStringLength;
	
	public TextTable() {
		rows = new ArrayList<>();
		maxStringLength = DEFAULT_MAX_STRING_LENGTH;
	}
	
	public TextTable(String[] columnNames) {
		this();
		setColumnNames(columnNames);
	}
	
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	
	public String[] getColumnNames() {
		return columnNames;
	}
	
	public void addRow(Object[] rowData) {
		final TextTableRow row = new TextTableRow();
		row.set(rowData);
		rows.add(row);
	}
	
	public void addRow(Object[] rowData, Object key) {
		final TextTableRow row = new TextTableRow();
		row.set(rowData);
		row.setKey(key);
		rows.add(row);
	}
	
	public void addRow(List<Object> rowData) {
		final TextTableRow row = new TextTableRow();
		row.set(rowData);
		rows.add(row);
	}

	public void addRow(List<Object> rowData, Object key) {
		final TextTableRow row = new TextTableRow();
		row.set(rowData);
		row.setKey(key);
		rows.add(row);
	}
	
	public int size() {
		return rows.size();
	}
	
	public Object[] getRow(Object key) {
		for(final TextTableRow row : rows)
			if(row.getKey() == key || key.equals(row.getKey()))
				return row.rowData();
		
		return null;
	}
	
	public Object[] getRow(int index) {
		return rows.get(index).rowData();
	}
	
	public void removeRow(Object key) {
		for(final TextTableRow row : rows) {
			if(row.getKey() == key || key.equals(row.getKey())) {
				rows.remove(row);
				break;
			}
		}
	}
	
	public boolean hasRow(Object key) {
		for(final TextTableRow row : rows)
			if(row.getKey() == key || key.equals(row.getKey()))
				return true;
		
		return false;
	}
	
	public String render() {
		final StringBuilder sb = new StringBuilder();
		
		detectColumnSizes();
		
		sb.append(columnNames != null ? renderHeader() : renderDecor());
		sb.append('\n');
		
		for(int i = 0; i < size(); i ++) {
			sb.append(renderLine(getRow(i)));
			sb.append('\n');
		}
		
		sb.append(renderDecor());
		
		return sb.toString();
	}
	
	private final String renderDecor() {
		final StringBuilder sb = new StringBuilder();
		
		sb.append('+');
		for (int size : columnSizes) {
			for(int j = 0; j < size + 1; j ++) {
				sb.append('-');
			}
			sb.append('+');
		}
		
		return sb.toString();
	}
	
	private final String renderHeader() {
		final StringBuilder sb = new StringBuilder();
		sb.append(renderDecor());
		sb.append('\n');
		sb.append(renderLine(columnNames));
		sb.append('\n');
		sb.append(renderDecor());
		return sb.toString();
	}
	
	private final String renderLine(Object[] cells) {
		final StringBuilder sb = new StringBuilder();

		sb.append('|');
		
		for (int i = 0; i < cells.length; i++) {
			if(i >= columnSizes.length) break;
			
			String n = String.valueOf(cells[i]);
			if(n.length() > maxStringLength) {
				n = n.substring(0, maxStringLength - 2) + "..";
			}
			
			sb.append(' ');
			sb.append(n);
			final int spacesLeftToRender = getSpacesLeft(n, columnSizes[i]);
			sb.append(spaces(spacesLeftToRender));
			sb.append('|');
		}
		
		if (cells.length < columnSizes.length) {
			final int difference = columnSizes.length - cells.length;
			
			for(int i = 0; i < difference; i ++) {
				sb.append(spaces(columnSizes[i + cells.length] + 1));
				sb.append('|');
			}
		}
		
		return sb.toString();
	}
	
	private static final int getSpacesLeft(String string, int size) {
		return size - string.length();
	}
	
	private static final String spaces(int count) {
		final StringBuilder sb = new StringBuilder();
		for(int i = 0; i < count; i ++) sb.append(' ');
		return sb.toString();
	}
	
	private final void detectColumnSizes() {
		int columnCount = 0;
		if (columnNames != null) {
			columnCount = columnNames.length;
		} else {
			int max = 0;
			for(final TextTableRow row : rows) {
				final Object[] cells = row.rowData();
				if(cells.length > max) max = cells.length;
			}
			columnCount = max;
		}

		this.columnSizes = new int[columnCount];

		if(columnNames != null) {
			for(int i = 0; i < columnNames.length; i ++) {
				for(int j = 0; j < columnNames[i].length(); j ++) {
					if (j >= columnSizes.length) break;
					
					String string = columnNames[j];
					
					if (string.length() > maxStringLength) {
						string = string.substring(0, maxStringLength);
					}
					
					
					final int stringLength = string.length();
					
					if (stringLength > columnSizes[j])
						columnSizes[j] = stringLength + 1;
				}
			}
		}
		
		for(int i = 0; i < size(); i ++) {
			final Object[] rowData = getRow(i);
			
			for(int j = 0; j < rowData.length; j ++) {
				if (j >= columnSizes.length) break;
				
				String string = String.valueOf(rowData[j]);
				if (string.length() > maxStringLength) {
					string = string.substring(0, maxStringLength);
				}
				
				final int stringLength = string.length();
				
				if (stringLength > columnSizes[j])
					columnSizes[j] = stringLength + 2;
			}
		}
	}

	public int getMaxStringLength() {
		return maxStringLength;
	}

	public void setMaxStringLength(int value) {
		this.maxStringLength = value;
	}
	
	
	
}



















