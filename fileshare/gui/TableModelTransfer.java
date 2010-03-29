package fileshare.gui;

import javax.swing.table.AbstractTableModel;

/**
 * Model pro tabulku transferů.
 *
 * @author Jakub Trmota
 */
class TableModelTransfer extends AbstractTableModel {

	private String[] columnNames = {"Přenos", "Soubor", "Velikost", "Hotovo", "", ""};
	private Object[][] data = {{"download", "vsechno.mp3", "4,57 MB", new Integer(40), "", "Zrušit:1"}, {"upload", "neco.jpg", "0,23 MB", new Integer(89), "", "Zrušit:2"}};

	public TableModelTransfer() {
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	public Object getValueAt(int row, int column) {
		return data[row][column];
	}

	@Override
	public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col <= 2) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}
