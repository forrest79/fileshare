package fileshare.gui;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JProgressBar;

public class ProgressBarRenderer extends JProgressBar implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object progress, boolean isSelected, boolean hasFocus, int row, int column) {
        Integer newProgress = (Integer) progress;
				setValue(newProgress.intValue());
        
        setToolTipText("Done " + newProgress.intValue() + "%");
				
        return this;
    }
}
