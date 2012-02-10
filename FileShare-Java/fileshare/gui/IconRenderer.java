package fileshare.gui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Icont to table cell.
 *
 * @author Jakub Trmota | Forrest79
 */
public class IconRenderer extends JLabel implements TableCellRenderer {
	/**
	 * Get table cell renderer component.
	 *
	 * @param table
	 * @param type
	 * @param isSelected
	 * @param hasFocus
	 * @param row
	 * @param column
	 * @return
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object type, boolean isSelected, boolean hasFocus, int row, int column) {
		String newString = (String) type;

		String icon;
		String toolTip;

		if (newString.equalsIgnoreCase("download")) {
			icon = "icon-download";
			toolTip = "Downloading...";
		} else {
			icon = "icon-upload";
			toolTip = "Uploading...";
		}

		setIcon(new ImageIcon(getClass().getResource("icon/" + icon + ".png"), toolTip));

		setToolTipText(toolTip);

		return this;
	}
}
