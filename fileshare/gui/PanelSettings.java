package fileshare.gui;

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
    textPort = new javax.swing.JTextField();
    password = new javax.swing.JPasswordField();
    scrollPaneDirs = new javax.swing.JScrollPane();
    listDirs = new javax.swing.JList();
    buttonAddDir = new javax.swing.JButton();
    buttonRemoveDir = new javax.swing.JButton();
    checkAutoUpload = new javax.swing.JCheckBox();
    labelSaveDir = new javax.swing.JLabel();
    textSaveDir = new javax.swing.JTextField();
    buttonChangeDir = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();
    buttonClose = new javax.swing.JButton();
    buttonRegenerateDirs = new javax.swing.JButton();

    setPreferredSize(new java.awt.Dimension(530, 430));

    labelPort.setText("Port:");

    labelHeslo.setText("Heslo:");

    labelDirs.setText("Adresáře pro sdílení:");

    listDirs.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    scrollPaneDirs.setViewportView(listDirs);

    buttonAddDir.setText("Přidat");

    buttonRemoveDir.setText("Vymazat");

    checkAutoUpload.setText("Povolit automatický upload souborů");

    labelSaveDir.setText("Ukládat soubory do:");

    buttonChangeDir.setText("Vybrat adresář");

    buttonSave.setText("Uložit");

    buttonClose.setText("Zavřít");
    buttonClose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonCloseActionPerformed(evt);
      }
    });

    buttonRegenerateDirs.setText("Přegenerovat seznam souborů");

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
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(checkAutoUpload)
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(labelSaveDir)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textSaveDir, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGroup(layout.createSequentialGroup()
                    .addComponent(buttonRegenerateDirs)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddDir, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                  .addComponent(buttonRemoveDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(buttonChangeDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
              .addGroup(layout.createSequentialGroup()
                .addComponent(buttonClose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave))))
          .addGroup(layout.createSequentialGroup()
            .addComponent(labelPort)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(labelHeslo)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelPort)
          .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelHeslo)
          .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(labelDirs)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPaneDirs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonRegenerateDirs)
          .addComponent(buttonAddDir)
          .addComponent(buttonRemoveDir))
        .addGap(7, 7, 7)
        .addComponent(checkAutoUpload)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(labelSaveDir)
          .addComponent(textSaveDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(buttonChangeDir))
        .addGap(32, 32, 32)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(buttonSave)
          .addComponent(buttonClose))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

		private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
			dialogSettings.close();
		}//GEN-LAST:event_buttonCloseActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonAddDir;
  private javax.swing.JButton buttonChangeDir;
  private javax.swing.JButton buttonClose;
  private javax.swing.JButton buttonRegenerateDirs;
  private javax.swing.JButton buttonRemoveDir;
  private javax.swing.JButton buttonSave;
  private javax.swing.JCheckBox checkAutoUpload;
  private javax.swing.JLabel labelDirs;
  private javax.swing.JLabel labelHeslo;
  private javax.swing.JLabel labelPort;
  private javax.swing.JLabel labelSaveDir;
  private javax.swing.JList listDirs;
  private javax.swing.JPasswordField password;
  private javax.swing.JScrollPane scrollPaneDirs;
  private javax.swing.JTextField textPort;
  private javax.swing.JTextField textSaveDir;
  // End of variables declaration//GEN-END:variables

}
