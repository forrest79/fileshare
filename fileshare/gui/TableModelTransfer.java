package fileshare.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Model for table of transfers.
 *
 * @author Jakub Trmota (Forrest79)
 */
class TableModelTransfer extends DefaultTableModel {

	private String[] columnNames = {"Transfer", "File", "Size", "Done", ""};

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
