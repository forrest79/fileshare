package fileshare.gui;

import javax.swing.table.AbstractTableModel;

/**
 * Model pro tabulku transferu.
 *
 * @author Jakub Trmota
 */
class TableModelFiles extends AbstractTableModel {

	private String[] columnNames = {"Soubor", "Velikost"};
	private Object[][] data = {{"vsechno.mp3", "4,43 MB"}, {"neco.mp3", "0,23 MB"}};

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
}
