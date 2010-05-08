package fileshare.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Model for table serach.
 *
 * @author Jakub Trmota (Forrest79)
 */
class TableModelSearch extends DefaultTableModel {

	private String[] columnNames = {"User", "File", "Size"};

	public TableModelSearch() {
		super(0, 3);
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
