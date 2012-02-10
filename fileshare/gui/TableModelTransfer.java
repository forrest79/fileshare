package fileshare.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Model for table of transfers.
 *
 * @author Jakub Trmota | Forrest79
 */
class TableModelTransfer extends DefaultTableModel {
	/**
	 * Column names.
	 */
	private String[] columnNames = {"Transfer", "File", "Size", "Done", ""};

	/**
	 * Initialize table transfer model.
	 */
	public TableModelTransfer() {
	}

	/**
	 * Return if cell is editable.
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	@Override
	public boolean isCellEditable(int row, int col) {
		if (col <= 3) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Return column count.
	 *
	 * @return
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Return column name.
	 *
	 * @param column
	 * @return
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	/**
	 * Return columns class.
	 *
	 * @param column
	 * @return
	 */
	@Override
	public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}
}
