package fileshare.gui;

import java.awt.Component;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Progress bar to table cell.
 *
 * @author Jakub Trmota | Forrest79
 */
public class ProgressBarRenderer extends JProgressBar implements TableCellRenderer {
	/**
	 * Get table cell renderer component.
	 *
	 * @param table
	 * @param progress
	 * @param isSelected
	 * @param hasFocus
	 * @param row
	 * @param column
	 * @return
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object progress, boolean isSelected, boolean hasFocus, int row, int column) {
		Integer newProgress = (Integer) progress;
		setValue(newProgress.intValue());

		setToolTipText("Done " + newProgress.intValue() + "%");

		return this;
	}
}
