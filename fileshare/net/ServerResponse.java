package fileshare.net;

import fileshare.FileShare;
import fileshare.settings.Settings;
import java.io.*;
import java.net.Socket;

/**
 * Server response.
 *
 * @author Jakub Trmota | Forrest79
 */
public class ServerResponse implements Runnable, ITransfer {
	/**
	 * Socket.
	 */
	private Socket socket;

	/**
	 * Is canceled.
	 */
	private boolean cancel = false;

	/**
	 * Initialize server response.
	 *
	 * @param socket
	 */
	public ServerResponse(Socket socket) {
		this.socket = socket;
	}

	/**
	 * Main thread function.
	 */
	@Override
	public void run() {
		BufferedReader input = null;
		BufferedOutputStream output = null;

		if (FileShare.DEBUG) {
			System.out.println("Server [" + socket.getRemoteSocketAddress() + " | Client connect]");
		}

		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new BufferedOutputStream(socket.getOutputStream());

			String clientMessage = input.readLine();

			String password = Settings.decode(input.readLine());
			if (FileShare.DEBUG) {
				System.out.println("Server [" + socket.getRemoteSocketAddress() + " | " + password + "] " + clientMessage);
			}

			if (password.equals(Settings.getSettings().getPassword())) {
				if (clientMessage.equalsIgnoreCase("connect")) {
					output.write("OK\n".getBytes());
					output.write(Settings.getSettings().getShareDirsXml().getBytes());
				} else if(clientMessage.equalsIgnoreCase("isalive")) {
					output.write("OK".getBytes());
				} else if(clientMessage.toLowerCase().startsWith("get")) {
					if (clientMessage.contains(" ")) {
						String[] path = clientMessage.split(" ", 2);
						String slash = "";

						if (path[1].contains("/")) {
							slash = "/";
						} else if(path[1].contains("\\")) {
							slash = "\\";
						}

						if (!slash.isEmpty()) {
							String dir = path[1].substring(0, path[1].lastIndexOf(slash));
							String file = path[1].substring(path[1].lastIndexOf(slash) + 1);

							if (Settings.getSettings().isShareDir(dir)) {
								File upload = new File(dir + slash + file);
								long size = upload.length();
								if (upload.exists()) {
									Transfers.getTransfers().addTransfer(Transfers.UPLOAD, file, size, this);

									output.write("OK\n".getBytes());
									output.write((String.valueOf(size) + "\n").getBytes());

									BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(dir + slash + file));
									byte[] data = new byte[16384];
									long dataSize = 0;
									int i;
									while(((i = fileInput.read(data)) != -1) && !cancel) {
										dataSize += i;
										output.write(data, 0, i);

										Transfers.getTransfers().updateCompleted(this, (int) (((double)dataSize / (double)size) * 100));
									}
									fileInput.close();
									Transfers.getTransfers().done(this);
								} else {
									output.write("ERROR\nFile doesn't exists.".getBytes());
								}
							} else {
								output.write("ERROR\nThis directory is not sharing.".getBytes());
							}
						} else {
							output.write("ERROR\nYou must specify file with directory.".getBytes());
						}
					} else {
						output.write("ERROR\nYou must specify file to get.".getBytes());
					}
				} else {
					output.write("ERROR\nOnly CONNECT, ISALIVE and GET [file] command is allowed.".getBytes());
				}
			} else {
				output.write("ERROR\nBad password.".getBytes());
			}
		} catch (Exception ex) {
			System.err.println(ex);
			Transfers.getTransfers().done(this);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (Exception ex) {
					System.err.println(ex);
				}
			}
			try {
				if (input != null) {
					input.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}

		if (FileShare.DEBUG) {
			System.out.println("Server [" + socket.getRemoteSocketAddress() + " | Client disconnect]");
		}
	}

	/**
	 * Close thread.
	 */
	@Override
	public void cancel() {
		cancel = true;
	}
}
