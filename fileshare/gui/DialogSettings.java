package fileshare.gui;

import java.awt.Frame;
import javax.swing.JDialog;

/**
 * Panel pro formulář nastavení.
 *
 * @author Jakub Trmota
 */
public class DialogSettings extends JDialog {
	public DialogSettings(Frame owner, boolean modal) {
		super(owner, modal);

		setResizable(false);

		setTitle("Nastavení");

		PanelSettings panelSettings = new PanelSettings(this);
		setSize(panelSettings.getPreferredSize());
		setLocationRelativeTo(null);

		add(panelSettings);
	}

	public void close() {
		this.setVisible(false);
	}
}
