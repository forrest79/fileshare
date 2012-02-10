package fileshare.net;

import java.io.File;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * List of file transfers (singleton).
 *
 * @author Jakub Trmota | Forrest79
 */
public class Transfers {
	/**
	 * Upload transfer.
	 */
	public static final int UPLOAD = 0;

	/**
	 * Download transfer.
	 */
	public static final int DOWNLOAD = 1;

	/**
	 * Cancel string.
	 */
	public static final String CANCEL = "Cancel";

	/**
	 * Done string.
	 */
	public static final String DONE = "Done";

	/**
	 * Transfers class.
	 */
	private static Transfers transfersInstance = null;

	/**
	 * Table.
	 */
	private DefaultTableModel tableModel = null;

	/**
	 * Transfers list.
	 */
	private ArrayList<ITransfer> transfers = null;

	/**
	 * Initialize transfers.
	 */
	private Transfers() {
		transfers = new ArrayList<ITransfer>();
	}

	/**
	 * Singleton - get Transfers instance.
	 * @return
	 */
	public static Transfers getTransfers() {
		if (transfersInstance == null) {
			transfersInstance = new Transfers();
		}

		return transfersInstance;
	}

	/**
	 * Set table model.
	 *
	 * @param tableModel
	 */
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * Get transfers list.
	 *
	 * @return
	 */
	public ArrayList<ITransfer> getTransfersList() {
		return transfers;
	}

	/**
	 * Add new transfer.
	 *
	 * @param type
	 * @param file
	 * @param size
	 * @param transfer
	 */
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

	/**
	 * Set transfer percent complete.
	 *
	 * @param transfer
	 * @param completed
	 */
	public void updateCompleted(ITransfer transfer, int completed) {
		tableModel.setValueAt(new Integer(completed), transfers.indexOf(transfer), 3);
	}

	/**
	 * Cancel transfer at index.
	 *
	 * @param index
	 */
	public void cancel(int index) {
		transfers.get(index).cancel();

		done(index);
	}

	/**
	 * Done transfer at index.
	 * @param index
	 */
	public void done(int index) {
		tableModel.setValueAt(DONE, index, 4);
	}

	/**
	 * Done transfer.
	 *
	 * @param transfer
	 */
	public void done(ITransfer transfer) {
		int index = transfers.indexOf(transfer);

		if (index > -1) {
			done(index);
		}
	}

	/**
	 * Remove transfer on index.
	 *
	 * @param index
	 */
	public void remove(int index) {
		tableModel.removeRow(index);
		transfers.remove(index);
	}

	/**
	 * Cancel all transfers.
	 */
	public void cancelAll() {
		for (int i = transfers.size() - 1; i >= 0; i--) {
			transfers.get(i).cancel();
		}
	}

	/**
	 * Get unique local filename for downloaded file.
	 *
	 * @param path
	 * @return
	 */
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
