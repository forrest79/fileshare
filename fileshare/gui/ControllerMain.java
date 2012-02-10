package fileshare.gui;

import fileshare.FileShare;
import fileshare.net.Server;
import fileshare.settings.Directory;
import fileshare.settings.OneFile;
import fileshare.settings.Settings;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * Main window controller.
 *
 * @author Jakub Trmota | Forrest79
 */
class ControllerMain implements ActionListener, TreeSelectionListener, MouseListener, PropertyChangeListener {
	/**
	 * Main form.
	 */
	private FormMain formMain = null;

	/**
	 * Directory with files to download.
	 */
	private Directory dir = null;

	/**
	 * Initialize controller.
	 *
	 * @param formMain
	 */
	public ControllerMain(FormMain formMain) {
		this.formMain = formMain;
	}

	/**
	 * Call on action performed.
	 *
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_CONNECT)) {
			try {
				Server.getServer().connect();
				formMain.connect();
			} catch (Exception ex) {
				if (FileShare.DEBUG) {
					System.out.println("Connection error: " + ex.getMessage());
				}

				formMain.showErrorDialog("Connect...", "Error while connecting!");
			}
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_DISCONNECT)) {
			try {
				Server.getServer().disconnect();
				formMain.disconnect();
			} catch (Exception ex) {
				if (FileShare.DEBUG) {
					System.out.println("Disconnection error: " + ex.getMessage());
				}

				formMain.showErrorDialog("Disconnect...", "Error while disconnection!");
			}
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_SEARCH)) {
			formMain.showDialogSearch();
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_USERS)) {
			formMain.showDialogUsers();
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_SETTINGS)) {
			formMain.showDialogSettings();
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_END)) {
			System.exit(0);
		} else if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_ABOUT)) {
			formMain.showDialogAbout();
		}
	}

	/**
	 * Call on value changed in tree.
	 *
	 * @param e
	 */
	@Override
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

	/**
	 * Call on mouse clicked in table.
	 *
	 * @param e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			JTable target = (JTable) e.getSource();

			Server.getServer().download(dir.getFile(target.getSelectedRow()));
		}
	}

	/**
	 * Call on mouse button pressed.
	 *
	 * @param e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// Not supported here...
	}

	/**
	 * Call on mouse button released.
	 *
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// Not supported here...
	}

	/**
	 * Call on mouse cursor entered.
	 *
	 * @param e
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// Not supported here...
	}

	/**
	 * Call on mouse cursor exited.
	 *
	 * @param e
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// Not supported here...
	}

	/**
	 * Call on property changed in split pane (horizontal and vertical).
	 *
	 * @param evt
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		JSplitPane splitPane = (JSplitPane) evt.getSource();

		if (splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
			Settings.getSettings().setHorizontalSplit(Integer.parseInt(String.valueOf(evt.getNewValue())));
		} else {
			Settings.getSettings().setVerticalSplit(Integer.parseInt(String.valueOf(evt.getNewValue())));
		}
		Settings.getSettings().saveToFile();
	}
}
