package fileshare.net;

import fileshare.FileShare;
import fileshare.settings.Settings;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Server implemantation.
 *
 * @author Jakub Trmota | Forrest79
 */
public class ServerThread implements Runnable {
	/**
	 * Is running.
	 */
	private boolean run = true;

	/**
	 * Server socket.
	 */
	private ServerSocket server;

	/**
	 * Initialize server thread.
	 *
	 * @throws Exception
	 */
	public ServerThread() throws Exception {
		try {
			server = new ServerSocket(Settings.getSettings().getPort());
			if (FileShare.DEBUG) {
				System.out.println("Server connect - " + InetAddress.getLocalHost() + ":" + Settings.getSettings().getPort());
			}
		} catch (Exception ex) {
			throw ex;
		}
	}

	/**
	 * Main thread function.
	 */
	@Override
	public void run() {
		Socket socket;
		Thread thread;

		while (run) {
			try {
				server.setSoTimeout(1000);
				socket = server.accept();

				thread = new Thread(new ServerResponse(socket));
				thread.setDaemon(true);
				thread.start();
			} catch (SocketTimeoutException ste) {
				// Signals that a timeout has occurred on a socket read or accept.
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
		if (FileShare.DEBUG) {
			System.out.println("Server - disconnect...");
		}

		try {
			server.close();
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	/**
	 * Close server.
	 */
	public void disconnect() {
		run = false;
	}
}
