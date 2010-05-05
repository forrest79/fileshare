package fileshare.net;

import fileshare.gui.FormMain;
import fileshare.settings.OneFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementace serveru (Singleton).
 *
 * @author Jakub Trmota
 */
public class Server {

	private static Server serverInstance = null;

	boolean isConnected = false;

	private Thread server = null;
	private Thread clients = null;

	private ServerThread serverThread = null;
	private UsersThread usersThread = null;

	private FormMain formMain = null;

	private Server() {
	}

	public static Server getServer() {
		if (serverInstance == null) {
			serverInstance = new Server();
		}

		return serverInstance;
	}

	public void connect() {
		if (isConnected) {
			return;
		}

		isConnected = true;
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

	public void disconnect() {
		if (!isConnected) {
			return;
		}

		Transfers.getTransfers().cancelAll();

		isConnected = false;

		serverThread.disconnect();
		usersThread.stop();

		serverThread = null;
		server = null;

		usersThread = null;
		clients = null;
	}

	public FormMain getFormMain() {
		return formMain;
	}

	public void setFormMain(FormMain formMain) {
		this.formMain = formMain;
	}

	public void download(OneFile file) {
		ClientDownload clientDownload = new ClientDownload(file, formMain);
		Thread downloadFile = new Thread(clientDownload);
		
		downloadFile.setDaemon(true);
		downloadFile.start();
	}
}
