package fileshare.settings;

import java.util.ArrayList;

/**
 * User - one user.
 *
 * @author Jakub Trmota | Forrest79
 */
public class User {
	/**
	 * Offline status.
	 */
	public static final int STATUS_OFFLINE = 0;

	/**
	 * Online status.
	 */
	public static final int STATUS_ONLINE = 1;

	/**
	 * User name.
	 */
	private String name = "";

	/**
	 * User address.
	 */
	private String address = "";

	/**
	 * User port.
	 */
	private int port = Settings.DEFAULT_PORT;

	/**
	 * User password.
	 */
	private String password = "";

	/**
	 * User status.
	 */
	private int status = STATUS_OFFLINE;

	/**
	 * User shared directories.
	 */
	private ArrayList<Directory> directories = null;

	/**
	 * Blank user initialization.
	 */
	public User() {
	}

	/**
	 * User initialization.
	 *
	 * @param name
	 * @param address
	 * @param port
	 * @param password
	 */
	public User(String name, String address, int port, String password) {
		this.name = name;
		this.address = address;
		this.port = port;
		this.password = password;

		directories = new ArrayList<Directory>();
	}

	/**
	 * Get user address.
	 *
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set user address.
	 *
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get user name.
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set user name.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get user password.
	 *
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set user password.
	 *
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get user port.
	 *
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Check and set user port.
	 *
	 * @param port
	 * @throws Exception
	 */
	public void setPort(String port) throws Exception {
		int intPort = 0;

		try {
			intPort = Integer.parseInt(port);
		} catch (Exception e) {
			throw new Exception("Port needs to be integer!");
		}

		if ((intPort < 1) || (intPort > 10000)) {
			throw new Exception("Port number between 0 and 10000!");
		}

		setPort(intPort);
	}

	/**
	 * Set user port.
	 *
	 * @param port
	 */
	private void setPort(int port) {
		this.port = port;
	}

	/**
	 * Set status as offline.
	 */
	public void setOfflineStatus() {
		status = STATUS_OFFLINE;
	}

	/**
	 * Set status as online.
	 */
	public void setOnlineStatus() {
		status = STATUS_ONLINE;
	}

	/**
	 * Check online status.
	 */
	public boolean isOnline() {
		return status == STATUS_ONLINE;
	}

	/**
	 * Check offline status.
	 * @return
	 */
	public boolean isOffline() {
		return status == STATUS_OFFLINE;
	}

	/**
	 * Add shared directory.
	 *
	 * @param directory
	 */
	public void addDirectory(Directory directory) {
		directories.add(directory);
	}

	/**
	 * Get string representation.
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return name + " (" + address + ":" + port + ")";
	}
}
