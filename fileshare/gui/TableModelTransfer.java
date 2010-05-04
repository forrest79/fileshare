package fileshare.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Model pro tabulku transferů.
 *
 * @author Jakub Trmota
 */
class TableModelTransfer extends DefaultTableModel {

	private String[] columnNames = {"Přenos", "Soubor", "Velikost", "Hotovo", ""};

	public TableModelTransfer() {
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col <= 3) {
			return false;
		} else {
			return true;
		}
	}
}
