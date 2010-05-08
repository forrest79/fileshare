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
 * @author Jakub Trmota (Forrest79)
 */
public class DialogSettings extends JDialog {
	
	private PanelSettings panelSettings = null;

	private DefaultListModel modelShareDirs = null;

	private JFileChooser dirChooser = null;

	private boolean regenerateShareDirs = false;

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

	public void close() {
		this.setVisible(false);
	}

	public PanelSettings getPanel() {
		return panelSettings;
	}

	public DefaultListModel getModelShareDirs() {
		return modelShareDirs;
	}

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

	public void addShareDir() {
		dirChooser.setAcceptAllFileFilterUsed(false);

		if (dirChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			modelShareDirs.addElement(dirChooser.getSelectedFile().toString());
			regenerateShareDirs = true;
		}
	}

	public void removeShareDir() {
		if ((panelSettings.getListDirs().getSelectedIndex() > -1) && (JOptionPane.showConfirmDialog(this, "Remove directory '" + modelShareDirs.elementAt(panelSettings.getListDirs().getSelectedIndex()).toString() + "'?", "Remove directory?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
			modelShareDirs.remove(panelSettings.getListDirs().getSelectedIndex());
			regenerateShareDirs = true;
		}
	}

	public void chooseDownloadDir() {
		dirChooser.setAcceptAllFileFilterUsed(false);

		if (dirChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			panelSettings.getTxtSaveDir().setText(dirChooser.getSelectedFile().toString());
		}
	}

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
