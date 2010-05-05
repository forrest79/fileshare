package fileshare.gui;

import fileshare.FileShare;
import java.awt.Desktop;
import java.awt.Frame;
import java.net.URI;
import javax.swing.JDialog;

/**
 * Dialog o aplikaci.
 *
 * @author Jakub Trmota
 */
public class DialogAbout extends JDialog {
	public DialogAbout(Frame owner, boolean modal) {
		super(owner, modal);

		setResizable(false);

		setTitle("O aplikaci");

		PanelAbout panelAbout = new PanelAbout(this);
		setSize(panelAbout.getPreferredSize());
		setLocationRelativeTo(null);

		add(panelAbout);
	}

	public void close() {
		this.setVisible(false);
	}

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
