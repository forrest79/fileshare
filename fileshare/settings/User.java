package fileshare.settings;

/**
 * User - one user.
 *
 * @author Jakub Trmota
 */
public class User {

	private String name = "";

	private String address = "";

	private int port = Settings.DEFAULT_PORT;

	private String password = "";

	public User() {
	}

	public User(String name, String address, int port, String password) {
		this.name = name;
		this.address = address;
		this.port = port;
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(String port) throws Exception {
		int intPort = 0;

		try {
			intPort = Integer.parseInt(port);
		} catch (Exception e) {
			throw new Exception("Port musí být celé číslo!");
		}

		if ((intPort < 1) || (intPort > 10000)) {
			throw new Exception("Číslo portu musí být větší než 0 a menší než 10000!");
		}

		setPort(intPort);
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return name + " (" + address + ":" + port + ")";
	}
}
