package fileshare.net;

import fileshare.gui.FormMain;
import fileshare.settings.OneFile;

/**
 * Serveru implementation (Singleton).
 *
 * @author Jakub Trmota | Forrest79
 */
public class Server {
	/**
	 * Singleton - Server instance.
	 */
	private static Server serverInstance = null;

	/**
	 * Is server connected?
	 */
	private boolean connected = false;

	/**
	 * Server thread.
	 */
	private Thread server = null;

	/**
	 * Client thread.
	 */
	private Thread clients = null;

	/**
	 * Server thread class.
	 */
	private ServerThread serverThread = null;

	/**
	 * Users thread class.
	 */
	private UsersThread usersThread = null;

	/**
	 * Main form.
	 */
	private FormMain formMain = null;

	/**
	 * Initialize server.
	 */
	private Server() {
	}

	/**
	 * Singleton - get Server instance.
	 *
	 * @return
	 */
	public static Server getServer() {
		if (serverInstance == null) {
			serverInstance = new Server();
		}

		return serverInstance;
	}

	/**
	 * Connect server.
	 */
	public void connect() {
		if (connected) {
			return;
		}

		connected = true;
		try {
			serverThread = new ServerThread();
		} catch (Exception ex) {
			disconnect();
		}
		usersThread = new UsersThread(formMain);

		if (server == null) {
			server = new Thread(serverThread);
		}
		server.setDaemon(true);
		server.start();

		if (clients == null) {
			clients = new Thread(usersThread);
		}
		clients.setDaemon(true);
		clients.start();
	}

	/**
	 * Disconnect server.
	 */
	public void disconnect() {
		if (!connected) {
			return;
		}

		Transfers.getTransfers().cancelAll();

		connected = false;

		serverThread.disconnect();
		usersThread.stop();

		serverThread = null;
		server = null;

		usersThread = null;
		clients = null;
	}

	/**
	 * Get main form.
	 *
	 * @return
	 */
	public FormMain getFormMain() {
		return formMain;
	}

	/**
	 * Set main form.
	 *
	 * @param formMain
	 */
	public void setFormMain(FormMain formMain) {
		this.formMain = formMain;
	}

	/**
	 * Download file.
	 *
	 * @param file
	 */
	public void download(OneFile file) {
		ClientDownload clientDownload = new ClientDownload(file, formMain);
		Thread downloadFile = new Thread(clientDownload);

		downloadFile.setDaemon(true);
		downloadFile.start();
	}
}
