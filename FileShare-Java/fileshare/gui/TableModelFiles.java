package fileshare.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Model for table files.
 *
 * @author Jakub Trmota | Forrest79
 */
class TableModelFiles extends DefaultTableModel {
	/**
	 * Table column names.
	 */
	private String[] columnNames = {"File", "Size"};

	/**
	 * Initialize model table files.
	 */
	public TableModelFiles() {
		super(0, 2);
	}

	/**
	 * Return if cell is editable.
	 *
	 * @param row
	 * @param column
	 * @return
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/**
	 * Return column count.
	 * @return
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Return comlumn name.
	 *
	 * @param column
	 * @return
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	/**
	 * Return column class.
	 *
	 * @param column
	 * @return
	 */
	@Override
	public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}
}
