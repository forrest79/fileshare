package fileshare.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Forrest79
 */
class ButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;

  private boolean isPushed;

  public ButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

	public void setActionCommand(String command) {
		button.setActionCommand(command);
	}

	@Override
  public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();

		String[] labels = label.split(":");

    button.setText(labels[0]);
		button.setActionCommand(labels[1]);
    isPushed = true;
    return button;
  }

	@Override
  public Object getCellEditorValue() {
    if (isPushed) {
      JOptionPane.showMessageDialog(button, label + ": Ouch!" + button.getActionCommand());
    }
    isPushed = false;
    return new String(label);
  }

	@Override
  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

	@Override
  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}
