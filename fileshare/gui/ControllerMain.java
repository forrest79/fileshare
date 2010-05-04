package fileshare.gui;

import fileshare.FileShare;
import fileshare.net.Server;
import fileshare.settings.Directory;
import fileshare.settings.OneFile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * Main window controller.
 *
 * @author Jakub Trmota
 */
class ControllerMain implements ActionListener, TreeSelectionListener, MouseListener {

	private FormMain formMain = null;

	private Directory dir = null;

	public ControllerMain(FormMain formMain) {
		this.formMain = formMain;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_CONNECT)) {
			try {
				Server.getServer().connect();
				formMain.connect();
			} catch (Exception ex) {
				if (FileShare.DEBUG) {
					System.out.println("Chyba při připojení: " + ex.getMessage());
				}

				formMain.showErrorDialog("Připojení...", "Nastala chyba při připojení!");
			}
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_DISCONNECT)) {
			try {
				Server.getServer().disconnect();
				formMain.disconnect();
			} catch (Exception ex) {
				if (FileShare.DEBUG) {
					System.out.println("Chyba při odpojení: " + ex.getMessage());
				}

				formMain.showErrorDialog("Odpojení...", "Nastala chyba při odpojení!");
			}
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_SEARCH)) {
			formMain.showDialogSearch();
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_USERS)) {
			formMain.showDialogUsers();
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_SETTINGS)) {
			formMain.showDialogSettings();
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_ABOUT)) {
			formMain.showDialogAbout();
		}
	}

	public void valueChanged(TreeSelectionEvent e) {
		JTree tree = (JTree) e.getSource();

		if (!tree.isSelectionEmpty() && (tree.getSelectionPath().getLastPathComponent() instanceof Directory)) {
			Directory selectedDir = (Directory) tree.getSelectionPath().getLastPathComponent();

			formMain.showFiles(selectedDir.getFiles());

			dir = selectedDir;
		} else {
			formMain.showFiles(new OneFile[0]);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable) e.getSource();

			Server.getServer().download(dir.getUser(), dir.getFile(target.getSelectedRow()));
		}
	}

	public void mousePressed(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	public void mouseReleased(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	public void mouseEntered(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}

	public void mouseExited(MouseEvent e) {
		//throw new UnsupportedOperationException("Not supported yet.");
	}
}
