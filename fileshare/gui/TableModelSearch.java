package fileshare.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Model for table search.
 *
 * @author Jakub Trmota | Forrest79
 */
class TableModelSearch extends DefaultTableModel {
	/**
	 * Search table columns.
	 */
	private String[] columnNames = {"User", "File", "Size"};

	/**
	 * Initialize search table.
	 */
	public TableModelSearch() {
		super(0, 3);
	}

	/**
	 * Return is cell is editable.
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
