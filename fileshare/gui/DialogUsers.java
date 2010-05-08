package fileshare.gui;

import fileshare.FileShare;
import fileshare.settings.Settings;
import fileshare.settings.User;
import fileshare.settings.Users;
import java.awt.Frame;
import javax.swing.*;

/**
 * Dialog for users.
 *
 * @author Jakub Trmota (Forrest79)
 */
public class DialogUsers extends JDialog {

	PanelUsers panelUsers = null;
	
	private DefaultListModel modelUsers = null;

	private int edit = -1;

	public DialogUsers(Frame owner, boolean modal) {
		super(owner, modal);

		setResizable(false);

		setTitle("Users");

		panelUsers = new PanelUsers(this);
		setSize(panelUsers.getPreferredSize());
		setLocationRelativeTo(null);

		modelUsers = new DefaultListModel();
		panelUsers.getListUsers().setModel(modelUsers);

		add(panelUsers);
	}

	public void blank() {
		edit = -1;

		panelUsers.getTxtName().setText("");
		panelUsers.getTxtAddress().setText("");
		panelUsers.getTxtPort().setText(String.valueOf(Settings.DEFAULT_PORT));
		panelUsers.getTxtPassword().setText("");
	}

	public void save() {
		try {
			Users users = Users.getUsers();

			if (users.testUser(panelUsers.getTxtName().getText(), panelUsers.getTxtAddress().getText(), panelUsers.getTxtPort().getText())) {

				User user = null;
				if (edit == -1) { // New user
					user = new User();
				} else { // Edit user
					user = users.get(edit);
				}

				user.setName(panelUsers.getTxtName().getText());
				user.setAddress(panelUsers.getTxtAddress().getText());
				user.setPort(panelUsers.getTxtPort().getText());
				user.setPassword(panelUsers.getTxtPassword().getText());

				if (edit == -1) {
					users.addUser(user);
					modelUsers.addElement(user);
				} else {
					users.update(edit);
					modelUsers.remove(edit);
					modelUsers.add(edit, user);
				}

				if (!users.saveToFile()) {
					if (FileShare.DEBUG) {
						System.err.println("Error while saving user to file...");
					}
				}

				blank();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "File save error", JOptionPane.ERROR_MESSAGE);

			return;
		}
	}

	public void change() {
		if (panelUsers.getListUsers().getSelectedIndex() > -1) {
			edit = panelUsers.getListUsers().getSelectedIndex();

			User user = Users.getUsers().get(edit);

			panelUsers.getTxtName().setText(user.getName());
			panelUsers.getTxtAddress().setText(user.getAddress());
			panelUsers.getTxtPort().setText(String.valueOf(user.getPort()));
			panelUsers.getTxtPassword().setText(user.getPassword());
		}
	}

	public void remove() {
		if ((panelUsers.getListUsers().getSelectedIndex() > -1) && (JOptionPane.showConfirmDialog(this, "Remove user '" + modelUsers.elementAt(panelUsers.getListUsers().getSelectedIndex()).toString() + "'?", "Remove user?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
			modelUsers.remove(edit);
			Users.getUsers().remove(edit);
			edit = -1;

			if (!Users.getUsers().saveToFile()) {
				if (FileShare.DEBUG) {
					System.err.println("User remove from file error...");
				}
			}

			blank();
		}
	}
	
	public void close() {
		this.setVisible(false);
	}
	
	public PanelUsers getPanel() {
		return panelUsers;
	}

	public DefaultListModel getModelUsers() {
		return modelUsers;
	}
}
