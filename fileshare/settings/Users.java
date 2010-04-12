package fileshare.settings;

import fileshare.FileShare;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Users (singleton).
 *
 * @author Jakub Trmota
 */
public class Users {

	private static Users usersInstance = null;

	private File fileUsers = null;

	private ArrayList<User> userList = null;

	private Users() {
		fileUsers = new File(FileShare.getAppDir() + "users.ini");

		userList = new ArrayList<User>();
	}

	public static Users getUsers() {
		if (usersInstance == null) {
			usersInstance = new Users();
		}

		return usersInstance;
	}

	public boolean loadFromFile() {
		if (fileUsers.exists()) {
			try {
				BufferedReader input = new BufferedReader(new FileReader(fileUsers));

				String line = "";

				String name = "";
				String address = "";
				int port = 0;
				String password = "";
				while ((line = input.readLine()) != null) {
					line = line.trim();

					if (line.startsWith("#")) { // Komentar
						continue;
					}

					if (line.equalsIgnoreCase("[user]")) {
						if (!name.isEmpty() && !address.isEmpty() && (port > 0) && !password.isEmpty()) {
							userList.add(new User(name, address, port, password));
						}

						name = "";
						address = "";
						port = 0;
						password = "";
					}

					String[] params = line.split("=", 2);

					if (params.length >= 2) {
						String key = params[0].trim().toLowerCase();
						String value = params[1].trim();

						if (key.equals("name")) {
							name = value;
						} else if (key.equals("address")) {
							address = value;
						} else if (key.equals("port")) {
							try {
								port = Integer.parseInt(value);
							} catch (Exception e) {
								if (FileShare.DEBUG) {
									System.err.println("Port error value: " + e.getMessage());
								}

								continue;
							}
						} else if(key.equals("password")) {
							password = Settings.decrypt(value);
						}
					}
				}
				if (!name.isEmpty() && !address.isEmpty() && (port > 0) && !password.isEmpty()) {
					userList.add(new User(name, address, port, password));
				}


				input.close();
			} catch (IOException e) {
				if (FileShare.DEBUG) {
					System.err.println("Settings load error: " + e.getMessage());
				}

				return false;
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean saveToFile() {
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(fileUsers));

			for (int i = 0; i < userList.size(); i++) {
				User user = userList.get(i);

				output.write("[User]" + FileShare.NL);
				output.write("Name=" + user.getName() + FileShare.NL);
				output.write("Address=" + user.getAddress() + FileShare.NL);
				output.write("Port=" + String.valueOf(user.getPort()) + FileShare.NL);
				output.write("Password=" + Settings.encrypt(user.getPassword()) + FileShare.NL);
				output.write(FileShare.NL);
			}

			output.close();
		} catch (IOException e) {
			if (FileShare.DEBUG) {
				System.err.println("Users write error: " + e.getMessage());
			}

			return false;
		}

		return true;
	}

	public boolean testUser(String name, String address, String port) throws Exception {
		if (name.isEmpty()) {
			throw new Exception("Musíte zadat název!");
		}

		if (address.isEmpty()) {
			throw new Exception("Musíte zadat adresu!");
		}

		int intPort = 0;

		try {
			intPort = Integer.parseInt(port);
		} catch (Exception e) {
			throw new Exception("Port musí být celé číslo!");
		}

		if ((intPort < 1) || (intPort > 10000)) {
			throw new Exception("Číslo portu musí být větší než 0 a menší než 10000!");
		}

		return true;
	}

	public void addUser(User user) {
		userList.add(user);
	}

	public User get(int index) {
		return userList.get(index);
	}

	public void remove(int index) {
		userList.remove(index);
	}

	public String[] getUsersArray() {
		String[] users = new String[userList.size()];

		for (int i = 0; i < userList.size(); i++) {
			users[i] = userList.get(i).toString();
		}

		return users;
	}
}
