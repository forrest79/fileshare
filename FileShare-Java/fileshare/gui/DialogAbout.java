package fileshare.gui;

import fileshare.FileShare;
import java.awt.Desktop;
import java.awt.Frame;
import java.net.URI;
import javax.swing.JDialog;

/**
 * Dialog about.
 *
 * @author Jakub Trmota | Forrest79
 */
public class DialogAbout extends JDialog {
	/**
	 * Initialize dialog.
	 *
	 * @param owner
	 * @param modal
	 */
	public DialogAbout(Frame owner, boolean modal) {
		super(owner, modal);

		setResizable(false);

		setTitle("About");

		PanelAbout panelAbout = new PanelAbout(this);
		setSize(panelAbout.getPreferredSize());
		setLocationRelativeTo(null);

		add(panelAbout);
	}

	/**
	 * Close dialog.
	 */
	public void close() {
		this.setVisible(false);
	}

	/**
	 * Perform send mail action.
	 */
	public void sendMail() {
		if (Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
			try {
				Desktop.getDesktop().mail(new URI("mailto", FileShare.EMAIL, null));
			} catch(Exception e) {
				if (FileShare.DEBUG) {
					System.err.println(e);
				}
			}
		}
	}
}
