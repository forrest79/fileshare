package fileshare.net;

import fileshare.gui.FormMain;
import fileshare.settings.OneFile;
import fileshare.settings.User;

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
	private ClientsThread clientsThread = null;

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

		serverThread = new ServerThread();
		clientsThread = new ClientsThread(formMain);

		if (server == null) {
			server = new Thread(serverThread);
		}
		server.setDaemon(true);
		server.start();

		if (clients == null) {
			clients = new Thread(clientsThread);
		}
		clients.setDaemon(true);
		clients.start();
	}

	public void disconnect() {
		if (!isConnected) {
			return;
		}

		isConnected = false;

		serverThread.disconnect();
		clientsThread.stop();

		serverThread = null;
		server = null;

		clientsThread = null;
		clients = null;
	}

	public FormMain getFormMain() {
		return formMain;
	}

	public void setFormMain(FormMain formMain) {
		this.formMain = formMain;
	}

	public void download(User user, OneFile file) {
		ClientDownload clientDownload = new ClientDownload(user, file, formMain);
		Thread downloadFile = new Thread(clientDownload);
		
		downloadFile.setDaemon(true);
		downloadFile.start();
	}
}
