package fileshare.settings;

/**
 * Users (singleton).
 *
 * @author Jakub Trmota
 */
public class Users {

	private static Users usersInstance = null;

	private Users() {
	}

	public static Users getUsers() {
		if (usersInstance == null) {
			usersInstance = new Users();
		}

		return usersInstance;
	}

	public boolean loadFromFile() {
		return true;
	}

	public boolean saveToFile() {
		return true;
	}
}
