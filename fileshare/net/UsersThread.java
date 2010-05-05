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
 * Kontroluje status klientů a získává seznam souborů.
 *
 * @author Jakub Trmota
 */
public class UsersThread implements Runnable {

	private static final int TIME = 60;

	private boolean run = true;

	private FormMain formMain = null;

	public UsersThread(FormMain formMain) {
		this.formMain = formMain;
	}

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
						output.write(Settings.encrypt(user.getPassword()) + "\n");
						output.flush();

						String response = input.readLine();
						if (!response.equalsIgnoreCase("ok")) {
							formMain.showErrorDialog("Chyba připojení: " + user.getName(), user.getName() + ": " + input.readLine());
							user.setOfflineStatus();
							Users.getUsers().reloadTreeView(i);
						}
					} else {
						output.write("CONNECT\n");
						output.write(Settings.encrypt(user.getPassword()) + "\n");
						output.flush();

						String response = input.readLine();
						if (response.equalsIgnoreCase("ok")) {
							user.setOnlineStatus();

							String xml = "";
							String xmlLine = "";
							while ((xmlLine = input.readLine()) != null) {
								xml += xmlLine + "\n";
							}
							Users.getUsers().parseShareDirsXml(i, xml);
							Users.getUsers().reloadTreeView(i);
						} else if (response.equalsIgnoreCase("error")) {
							formMain.showErrorDialog("Chyba připojení: " + user.getName(), user.getName() + ": " + input.readLine());
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
