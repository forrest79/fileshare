package fileshare.gui;

import fileshare.net.Transfers;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Button in table row.
 *
 * @author Jakub Trmota | Forrest79
 */
class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
	/**
	 * Table.
	 */
	JTable table;

	/**
	 * Button in cell.
	 */
	JButton renderButton;

	/**
	 * Edit button.
	 */
	JButton editButton;

	/**
	 * Text input.
	 */
	String text;

	/**
	 * Initialize button column.
	 *
	 * @param table
	 * @param column
	 */
	public ButtonColumn(JTable table, int column)	{
		super();
		this.table = table;
		renderButton = new JButton();

		editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
	}

	/**
	 * Get table cell renderer component.
	 *
	 * @param table
	 * @param value
	 * @param isSelected
	 * @param hasFocus
	 * @param row
	 * @param column
	 * @return
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (hasFocus)
		{
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		} else if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(table.getSelectionBackground());
		}
		else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		renderButton.setText((value == null) ? "" : value.toString());
		return renderButton;
	}

	/**
	 * Get table cell editor component.
	 *
	 * @param table
	 * @param value
	 * @param isSelected
	 * @param row
	 * @param column
	 * @return
	 */
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		text = (value == null) ? "" : value.toString();
		editButton.setText(text);
		return editButton;
	}

	/**
	 * Get cell editor value.
	 *
	 * @return
	 */
	@Override
	public Object getCellEditorValue() {
		return text;
	}

	/**
	 * Call at action performed.
	 *
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		fireEditingStopped();

		if (e.getActionCommand().equals(Transfers.CANCEL)) {
			if (JOptionPane.showConfirmDialog((Component) e.getSource(), "Do you really want to cancel?", "Cancel?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				Transfers.getTransfers().cancel(table.getSelectedRow());
			}
		} else if (e.getActionCommand().equals(Transfers.DONE)) {
			Transfers.getTransfers().remove(table.getSelectedRow());
		}
	}
}
