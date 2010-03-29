package fileshare.gui;

import java.awt.Frame;
import javax.swing.*;

/**
 * Dialog pro správu připojených uživatelů.
 *
 * @author Jakub Trmota
 */
public class DialogUsers extends JDialog {

	public DialogUsers(Frame owner, boolean modal) {
		super(owner, modal);

		setResizable(false);

		setTitle("Uživatelé");

		PanelUsers panelUsers = new PanelUsers(this);
		setSize(panelUsers.getPreferredSize());
		setLocationRelativeTo(null);

		add(panelUsers);
	}

	public void close() {
		this.setVisible(false);
	}
}
