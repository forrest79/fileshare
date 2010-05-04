package fileshare.net;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 * Seznam transferů souborů (singleton).
 *
 * @author Jakub Trmota
 */
public class Transfers {

	public static final int UPLOAD = 0;
	public static final int DOWNLOAD = 1;

	public static final String CANCEL = "Zrušit";
	public static final String DONE = "Hotovo";

	private static Transfers transfersInstance = null;

	private DefaultTableModel tableModel = null;

	private ArrayList<ITransfer> transfers = null;

	private Transfers() {
		transfers = new ArrayList<ITransfer>();
	}

	public static Transfers getTransfers() {
		if (transfersInstance == null) {
			transfersInstance = new Transfers();
		}

		return transfersInstance;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public ArrayList<ITransfer> getTransfersList() {
		return transfers;
	}

	public void addTransfer(int type, String file, long size, ITransfer transfer) {
		transfers.add(transfer);

		String transferType = "";
		if (type == UPLOAD) {
			transferType = "upload";
		} else if (type == DOWNLOAD) {
			transferType = "download";
		}

		tableModel.addRow(new Object[] {transferType, file, String.valueOf(size / 1024) + " kB", new Integer(0), CANCEL});
	}

	public void updateCompleted(ITransfer transfer, int completed) {
		tableModel.setValueAt(new Integer(completed), transfers.indexOf(transfer), 3);
	}

	public void cancel(int index) {
		transfers.get(index).cancel();

		done(index);
	}

	public void done(int index) {
		tableModel.setValueAt(DONE, index, 4);
	}

	public void done(ITransfer transfer) {
		int index = transfers.indexOf(transfer);

		if (index > -1) {
			done(index);
		}
	}

	public void remove(int index) {
		tableModel.removeRow(index);
		transfers.remove(index);
	}

	public void cancelAll() {
		for (int i = transfers.size() - 1; i >= 0; i--) {
			transfers.get(i).cancel();
		}
	}

	public static String getFreeDownloadFilename(String path) {
		File file = new File(path);

		if (!file.exists()) {
			return path;
		}

		for (int i = 1; ; i++) {
			file = new File(path + "-" + i);

			if (!file.exists()) {
				return path + "-" + i;
			}
		}
	}
}
