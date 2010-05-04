package fileshare.net;

import fileshare.FileShare;
import fileshare.settings.Settings;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Odpověď serveru.
 *
 * @author Jakub Trmota
 */
public class ServerResponse implements Runnable, ITransfer {
	private Socket socket;

	private int completed;
	private boolean cancel = false;

	public ServerResponse(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader input = null;
		BufferedOutputStream output = null;
		String clientMessage = "";
		String password = "";

		if (FileShare.DEBUG) {
			System.out.println("Server [" + socket.getRemoteSocketAddress() + " | Client connect]");
		}

		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new BufferedOutputStream(socket.getOutputStream());

			clientMessage = input.readLine();

			password = Settings.decrypt(input.readLine());
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
									int i = 0;
									byte[] data = new byte[16384];
									long dataSize = 0;
									while(((i = fileInput.read(data)) != -1) && !cancel) {
										dataSize += i;
										output.write(data, 0, i);

										completed = (int) (((double)dataSize / (double)size) * 100);
										Transfers.getTransfers().updateCompleted(this, completed);
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
				output.write("ERROR\nŠpatné heslo.".getBytes());
			}
		} catch (IOException ex) {
			System.err.println(ex);
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException ex) {
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

	public synchronized int getCompleted() {
		return completed;
	}

	public void cancel() {
		cancel = true;
	}
}
