package fileshare.net;

import fileshare.FileShare;
import fileshare.gui.FormMain;
import fileshare.settings.OneFile;
import fileshare.settings.Settings;
import fileshare.settings.User;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Stáhne ze serveru.
 *
 * @author Jakub Trmota
 */
public class ClientDownload implements Runnable {

	private User user = null;
	private OneFile file = null;

	private long fileSize = 0;

	private FormMain formMain = null;

	public ClientDownload(User user, OneFile file, FormMain formMain) {
		this.user = user;
		this.file = file;
		this.formMain = formMain;
	}

	public void run() {
		try {
			Socket socket = new Socket(user.getAddress(), user.getPort());

      File downloadFile = new File(Settings.getSettings().getDownloadDir() + FileShare.SLASH + file.getName());
			byte[] data = new byte[16384];
			try {
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				BufferedInputStream inputData = new BufferedInputStream(socket.getInputStream());
				BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedOutputStream fileOutput = new BufferedOutputStream(new FileOutputStream(downloadFile));

				output.write("GET " + file.getPath() + "\n");
				output.write(Settings.decrypt(Settings.getSettings().getPassword()) + "\n");
				output.flush();

				String response = input.readLine();
				if (response.equalsIgnoreCase("ok")) {
					fileSize = Long.parseLong(input.readLine());

					int i = 0;
					while((i = inputData.read(data)) != -1) {
						fileOutput.write(data, 0, i);
					}
				} else if (response.equalsIgnoreCase("error")) {
					formMain.showErrorDialog("Chyba přenosu: " + user.getName(), input.readLine());
				}
				input.close();
				fileOutput.close();
				inputData.close();
			} catch(Exception ex) {
				formMain.showErrorDialog("Chyba přenosu: " + user.getName(), ex.getMessage());
			}
		} catch (Exception ex) {
			formMain.showErrorDialog("Chyba přenosu: " + user.getName(), ex.getMessage());
		}
	}

}
