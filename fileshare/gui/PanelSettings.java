package fileshare.gui;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 * Formulář pro nastavení.
 *
 * @author Jakub Trmota
 */
public class PanelSettings extends javax.swing.JPanel {

	private DialogSettings dialogSettings = null;

	public PanelSettings(DialogSettings dialogSettings) {
		this.dialogSettings = dialogSettings;

		initComponents();
	}

    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    labelPort = new javax.swing.JLabel();
    labelHeslo = new javax.swing.JLabel();
    labelDirs = new javax.swing.JLabel();
    txtPort = new javax.swing.JTextField();
    scrollPaneDirs = new javax.swing.JScrollPane();
    lstDirs = new javax.swing.JList();
    buttonAddDir = new javax.swing.JButton();
    buttonRemoveDir = new javax.swing.JButton();
    labelSaveDir = new javax.swing.JLabel();
    txtSaveDir = new javax.swing.JTextField();
    buttonChangeDir = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();
    buttonClose = new javax.swing.JButton();
    buttonRegenerateDirs = new javax.swing.JButton();
    txtPassword = new javax.swing.JTextField();

    setPreferredSize(new java.awt.Dimension(530, 430));

    labelPort.setText("Port:");

    labelHeslo.setText("Heslo:");

    labelDirs.setText("Adresáře pro sdílení:");

    lstDirs.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    scrollPaneDirs.setViewportView(lstDirs);

    buttonAddDir.setText("Přidat");
    buttonAddDir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonAddDirActionPerformed(evt);
      }
    });

    buttonRemoveDir.setText("Vymazat");
    buttonRemoveDir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonRemoveDirActionPerformed(evt);
      }
    });

    labelSaveDir.setText("Ukládat soubory do:");

    buttonChangeDir.setText("Vybrat adresář");
    buttonChangeDir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonChangeDirActionPerformed(evt);
      }
    });

    buttonSave.setText("Uložit");
    buttonSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonSaveActionPerformed(evt);
      }
    });

    buttonClose.setText("Zavřít");
    buttonClose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonCloseActionPerformed(evt);
      }
    });

    buttonRegenerateDirs.setText("Přegenerovat seznam souborů");
    buttonRegenerateDirs.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonRegenerateDirsActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelDirs)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
            .addComponent(scrollPaneDirs, javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
              .addComponent(buttonRegenerateDirs)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(buttonAddDir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(buttonRemoveDir)))
          .addGroup(layout.createSequentialGroup()
            .addComponent(labelPort)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(labelHeslo)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
              .addComponent(labelSaveDir)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(txtSaveDir, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(buttonChangeDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
              .addComponent(buttonSave)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(buttonClose))))
        .addContainerGap(50, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelPort)
          .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelHeslo)
          .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labelDirs)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPaneDirs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonRegenerateDirs)
          .addComponent(buttonAddDir)
          .addComponent(buttonRemoveDir))
        .addGap(18, 18, 18)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelSaveDir)
          .addComponent(txtSaveDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonChangeDir))
        .addGap(35, 35, 35)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonClose)
          .addComponent(buttonSave))
        .addContainerGap(79, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

		private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
			dialogSettings.close();
		}//GEN-LAST:event_buttonCloseActionPerformed

		private void buttonAddDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddDirActionPerformed
			dialogSettings.addShareDir();
		}//GEN-LAST:event_buttonAddDirActionPerformed

		private void buttonChangeDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangeDirActionPerformed
			dialogSettings.chooseDownloadDir();
		}//GEN-LAST:event_buttonChangeDirActionPerformed

		private void buttonRemoveDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveDirActionPerformed
			dialogSettings.removeShareDir();
		}//GEN-LAST:event_buttonRemoveDirActionPerformed

		private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
			dialogSettings.save();
		}//GEN-LAST:event_buttonSaveActionPerformed

		private void buttonRegenerateDirsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegenerateDirsActionPerformed
			dialogSettings.generateShareList();
		}//GEN-LAST:event_buttonRegenerateDirsActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonAddDir;
  private javax.swing.JButton buttonChangeDir;
  private javax.swing.JButton buttonClose;
  private javax.swing.JButton buttonRegenerateDirs;
  private javax.swing.JButton buttonRemoveDir;
  private javax.swing.JButton buttonSave;
  private javax.swing.JLabel labelDirs;
  private javax.swing.JLabel labelHeslo;
  private javax.swing.JLabel labelPort;
  private javax.swing.JLabel labelSaveDir;
  private javax.swing.JList lstDirs;
  private javax.swing.JScrollPane scrollPaneDirs;
  private javax.swing.JTextField txtPassword;
  private javax.swing.JTextField txtPort;
  private javax.swing.JTextField txtSaveDir;
  // End of variables declaration//GEN-END:variables

	public JTextField getTxtPort() {
		return txtPort;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public JList getListDirs() {
		return lstDirs;
	}

	public JTextField getTxtSaveDir() {
		return txtSaveDir;
	}
}
