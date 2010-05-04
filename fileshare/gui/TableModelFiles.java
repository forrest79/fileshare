package fileshare.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Model pro tabulku transferu.
 *
 * @author Jakub Trmota
 */
class TableModelFiles extends DefaultTableModel {

	private String[] columnNames = {"Soubor", "Velikost"};

	public TableModelFiles() {
		super(0, 2);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
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
}
