package fileshare.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Forrest79
 */
class ControllerMain implements ActionListener {

	private FormMain formMain = null;

	public ControllerMain(FormMain formMain) {
		this.formMain = formMain;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());

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

}
