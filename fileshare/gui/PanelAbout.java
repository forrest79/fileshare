package fileshare.gui;

import fileshare.FileShare;

/**
 * Formulář pro dialog O aplikaci.
 *
 * @author Jakub Trmota
 */
public class PanelAbout extends javax.swing.JPanel {

	private DialogAbout dialogAbout = null;

	public PanelAbout(DialogAbout dialogAbout) {
		this.dialogAbout = dialogAbout;
		
		initComponents();

		this.labelName.setText(FileShare.NAME);
		this.labelVersion.setText(String.valueOf(FileShare.VERSION));
		this.labelEmail.setText(FileShare.EMAIL);
	}

	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    iconMain = new javax.swing.JLabel();
    buttonClose = new javax.swing.JButton();
    labelName = new javax.swing.JLabel();
    labelVersion = new javax.swing.JLabel();
    labelProgrammer = new javax.swing.JLabel();
    labelEmail = new javax.swing.JLabel();

    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

    iconMain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fileshare/gui/icon/icon-main.png"))); // NOI18N

    buttonClose.setText("Zavřít");
    buttonClose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonCloseActionPerformed(evt);
      }
    });

    labelName.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
    labelName.setForeground(new java.awt.Color(82, 24, 24));
    labelName.setText("FileShare");

    labelVersion.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
    labelVersion.setForeground(new java.awt.Color(82, 24, 24));
    labelVersion.setText("1.0");

    labelProgrammer.setText("Jakub Trmota |");

    labelEmail.setForeground(new java.awt.Color(0, 0, 255));
    labelEmail.setText("trmotjak@fel.cvut.cz");
    labelEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    labelEmail.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        labelEmailMouseClicked(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(iconMain))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(labelProgrammer)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelEmail)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
            .addComponent(buttonClose))
          .addGroup(layout.createSequentialGroup()
            .addGap(41, 41, 41)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(labelVersion)
              .addComponent(labelName))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(iconMain)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelName)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelVersion)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(buttonClose)
          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(labelProgrammer)
            .addComponent(labelEmail)))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

	private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
		dialogAbout.close();
	}//GEN-LAST:event_buttonCloseActionPerformed

	private void labelEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelEmailMouseClicked
		dialogAbout.sendMail();
	}//GEN-LAST:event_labelEmailMouseClicked


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonClose;
  private javax.swing.JLabel iconMain;
  private javax.swing.JLabel labelEmail;
  private javax.swing.JLabel labelName;
  private javax.swing.JLabel labelProgrammer;
  private javax.swing.JLabel labelVersion;
  // End of variables declaration//GEN-END:variables

}
