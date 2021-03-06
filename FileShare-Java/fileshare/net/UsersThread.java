package fileshare.net;

import fileshare.FileShare;
import fileshare.gui.FormMain;
import fileshare.settings.Settings;
import fileshare.settings.User;
import fileshare.settings.Users;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Check users status and gets file list.
 *
 * @author Jakub Trmota | Forrest79
 */
public class UsersThread implements Runnable {
	/**
	 * How often check users in seconds.
	 */
	private static final int TIME = 60;

	/**
	 * Is running?
	 */
	private boolean run = true;

	/**
	 * Main form.
	 */
	private FormMain formMain = null;

	/**
	 * Initialize users thred.
	 *
	 * @param formMain
	 */
	public UsersThread(FormMain formMain) {
		this.formMain = formMain;
	}

	/**
	 * Thread main function.
	 */
	@Override
	public void run() {
		while (run) {
			ArrayList<User> users = Users.getUsers().getUsersList();

			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				try {
					Socket socket = new Socket(user.getAddress(), user.getPort());
					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

					if (user.isOnline()) {
						output.write("ISALIVE\n");
						output.write(Settings.encode(user.getPassword()) + "\n");
						output.flush();

						String response = input.readLine();
						if (!response.equalsIgnoreCase("ok")) {
							formMain.showErrorDialog("Connection error: " + user.getName(), user.getName() + ": " + input.readLine());
							user.setOfflineStatus();
							Users.getUsers().reloadTreeView(i);
						}
					} else {
						output.write("CONNECT\n");
						output.write(Settings.encode(user.getPassword()) + "\n");
						output.flush();

						String response = input.readLine();
						if (response.equalsIgnoreCase("ok")) {
							user.setOnlineStatus();

							String xml = "";
							String xmlLine;
							while ((xmlLine = input.readLine()) != null) {
								xml += xmlLine + "\n";
							}
							Users.getUsers().parseShareDirsXml(i, xml);
							Users.getUsers().reloadTreeView(i);
						} else if (response.equalsIgnoreCase("error")) {
							formMain.showErrorDialog("Connection error: " + user.getName(), user.getName() + ": " + input.readLine());
							user.setOfflineStatus();
						}
					}
				} catch (Exception ex) {
					if (FileShare.DEBUG) {
						System.err.println(ex);
					}
					user.setOfflineStatus();
				}
			}

			try {
				Thread.sleep(TIME * 1000);
			} catch (InterruptedException ex) {
				if (FileShare.DEBUG) {
					System.err.println(ex);
				}
			}
		}
	}

	/**
	 * Stop checking.
	 */
	public void stop() {
		run = false;

		ArrayList<User> users = Users.getUsers().getUsersList();

		for (int i = 0; i < users.size(); i++) {
			users.get(i).setOfflineStatus();
			Users.getUsers().removeDirsFromTree(i);
		}

		Users.getUsers().reloadTreeView();
	}
}
