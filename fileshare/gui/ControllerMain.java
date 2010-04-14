package fileshare.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * Main window controller.
 *
 * @author Jakub Trmota
 */
class ControllerMain implements ActionListener, TreeSelectionListener {

	private FormMain formMain = null;

	public ControllerMain(FormMain formMain) {
		this.formMain = formMain;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(FormMain.COMMAND_SEARCH)) {
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
		System.out.println(e.toString());
	}
}
