package fileshare.gui;

import fileshare.FileShare;
import fileshare.settings.Settings;
import java.awt.Frame;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Panel pro formulář nastavení.
 *
 * @author Jakub Trmota
 */
public class DialogSettings extends JDialog {
	
	private PanelSettings panelSettings = null;

	private DefaultListModel modelShareDirs = null;

	private JFileChooser dirChooser = null;

	private boolean regenerateShareDirs = false;

	public DialogSettings(Frame owner, boolean modal) {
		super(owner, modal);

		setResizable(false);

		setTitle("Nastavení");

		panelSettings = new PanelSettings(this);
		setSize(panelSettings.getPreferredSize());
		setLocationRelativeTo(null);

		modelShareDirs = new DefaultListModel();
		panelSettings.getListDirs().setModel(modelShareDirs);

		add(panelSettings);

		dirChooser = new JFileChooser();
		dirChooser.setDialogTitle("Vyberte adresář");
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

			int port = 0;
			try {
				port = Integer.parseInt(panelSettings.getTextPort().getText());
			} catch (Exception e) {
				throw new Exception("Port musí být celé číslo!");
			}
			if ((port < 1) || (port > 10000)) {
				throw new Exception("Port musí být celé číslo mezi 1 a 10000!");
			}

			settings.setPassword(panelSettings.getPassword().getPassword().toString());

			settings.clearShareDirs();
			for (int i = 0; i < modelShareDirs.size(); i++) {
				settings.addShareDir(modelShareDirs.get(i).toString());
			}

			settings.setPort(port);

			settings.setAutomaticUpload(panelSettings.getCheckAutoUpload().isSelected());

			settings.setDownloadDir(panelSettings.getTextSaveDir().getText());

			if (!settings.saveToFile()) {
				if (FileShare.DEBUG) {
					System.err.println("Nastavení se nepodařilo uložit do souboru...");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Chyba při ukládání", JOptionPane.ERROR_MESSAGE);

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
		if ((panelSettings.getListDirs().getSelectedIndex() > -1) && (JOptionPane.showConfirmDialog(this, "Vymazat adresář '" + modelShareDirs.elementAt(panelSettings.getListDirs().getSelectedIndex()).toString() + "'?", "Vymazat adresář?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)) {
			modelShareDirs.remove(panelSettings.getListDirs().getSelectedIndex());
			regenerateShareDirs = true;
		}
	}

	public void chooseDownloadDir() {
		dirChooser.setAcceptAllFileFilterUsed(false);

		if (dirChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			panelSettings.getTextSaveDir().setText(dirChooser.getSelectedFile().toString());
		}
	}

	public void generateShareList() {
	}
}
