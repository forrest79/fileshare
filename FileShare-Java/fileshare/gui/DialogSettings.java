package fileshare.gui;

import fileshare.FileShare;
import fileshare.settings.Settings;
import java.awt.Frame;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Dialog for settings.
 *
 * @author Jakub Trmota | Forrest79
 */
public class DialogSettings extends JDialog {
	/**
	 * Settings panel.
	 */
	private PanelSettings panelSettings = null;

	/**
	 * Share directories list.
	 */
	private DefaultListModel modelShareDirs = null;

	/**
	 * Directory file picker.
	 */
	private JFileChooser dirChooser = null;

	/**
	 * If regenerate share directories.
	 */
	private boolean regenerateShareDirs = false;

	/**
	 * Initialize settings dialog.
	 *
	 * @param owner
	 * @param modal
	 */
	public DialogSettings(Frame owner, boolean modal) {
		super(owner, modal);

		setResizable(false);

		setTitle("Settings");

		panelSettings = new PanelSettings(this);
		setSize(panelSettings.getPreferredSize());
		setLocationRelativeTo(null);

		modelShareDirs = new DefaultListModel();
		panelSettings.getListDirs().setModel(modelShareDirs);

		add(panelSettings);

		dirChooser = new JFileChooser();
		dirChooser.setDialogTitle("Choose directory");
		dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

	/**
	 * Close settings dialog.
	 */
	public void close() {
		this.setVisible(false);
	}

	/**
	 * Get settings panel.
	 *
	 * @return
	 */
	public PanelSettings getPanel() {
		return panelSettings;
	}

	/**
	 * Get share directories list.
	 * @return
	 */
	public DefaultListModel getModelShareDirs() {
		return modelShareDirs;
	}

	/**
	 * Save settings.
	 */
	public void save() {
		try {
			Settings settings = Settings.getSettings();

			settings.setPort(panelSettings.getTxtPort().getText());

			settings.setPassword(panelSettings.getTxtPassword().getText());

			settings.clearShareDirs();
			for (int i = 0; i < modelShareDirs.size(); i++) {
				settings.addShareDir(modelShareDirs.get(i).toString());
			}

			settings.setDownloadDir(panelSettings.getTxtSaveDir().getText());

			if (regenerateShareDirs) {
				if (!settings.generateShareDirsXml()) {
					if (FileShare.DEBUG) {
						System.err.println("XML file list for share directories error...");
					}
				}

				regenerateShareDirs = false;
			}

			if (!settings.saveToFile()) {
				if (FileShare.DEBUG) {
					System.err.println("Error saving settings to file...");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Save error", JOptionPane.ERROR_MESSAGE);

			return;
		}

		close();
	}

	/**
	 * Add share directory.
	 */
	public void addShareDir() {
		dirChooser.setAcceptAllFileFilterUsed(false);

		if (dirChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			modelShareDirs.addElement(dirChooser.getSelectedFile().toString());
			regenerateShareDirs = true;
		}
	}

	/**
	 * Remove share directory.
	 */
	public void removeShareDir() {
		if ((panelSettings.getListDirs().getSelectedIndex() > -1) && (JOptionPane.showConfirmDialog(this, "Remove directory '" + modelShareDirs.elementAt(panelSettings.getListDirs().getSelectedIndex()).toString() + "'?", "Remove directory?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
			modelShareDirs.remove(panelSettings.getListDirs().getSelectedIndex());
			regenerateShareDirs = true;
		}
	}

	/**
	 * Choose directory to download in.
	 */
	public void chooseDownloadDir() {
		dirChooser.setAcceptAllFileFilterUsed(false);

		if (dirChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			panelSettings.getTxtSaveDir().setText(dirChooser.getSelectedFile().toString());
		}
	}

	/**
	 * Generate share directories list.
	 */
	public void generateShareList() {
		if (JOptionPane.showConfirmDialog(this, "Do you really want to regenerate XML file list?\nIt can take a minute...", "Regenerate file list?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			if (!Settings.getSettings().generateShareDirsXml()) {
				if (FileShare.DEBUG) {
					System.err.println("XML file list generate error...");
				}
			}
		}
	}
}
