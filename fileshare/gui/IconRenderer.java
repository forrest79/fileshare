package fileshare.gui;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class IconRenderer extends JLabel implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object type, boolean isSelected, boolean hasFocus, int row, int column) {
        String newString = (String) type;

				String icon = "";
				String toolTip = "";

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
