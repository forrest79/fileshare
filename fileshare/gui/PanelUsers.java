package fileshare.gui;

/**
 * Formulář pro seznam uživatelů.
 *
 * @author Jakub Trmota
 */
public class PanelUsers extends javax.swing.JPanel {

	private DialogUsers dialogUsers = null;

	public PanelUsers(DialogUsers dialogUsers) {
		this.dialogUsers = dialogUsers;

		initComponents();
	}

	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    labelName = new javax.swing.JLabel();
    textName = new javax.swing.JTextField();
    textIP = new javax.swing.JFormattedTextField();
    labelIP = new javax.swing.JLabel();
    labelPort = new javax.swing.JLabel();
    labelPassword = new javax.swing.JLabel();
    textPort = new javax.swing.JTextField();
    password = new javax.swing.JPasswordField();
    buttonClose = new javax.swing.JToggleButton();
    buttonNew = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();
    buttonRemove = new javax.swing.JButton();
    scrollPaneUsers = new javax.swing.JScrollPane();
    listUsers = new javax.swing.JList();

    labelName.setText("Název:");

    try {
      textIP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###.###")));
    } catch (java.text.ParseException ex) {
      ex.printStackTrace();
    }

    labelIP.setText("IP:");

    labelPort.setText("Port:");

    labelPassword.setText("Heslo:");

    buttonClose.setText("Zavřít");
    buttonClose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonCloseActionPerformed(evt);
      }
    });

    buttonNew.setText("Nový");

    buttonSave.setText("Uložit");

    buttonRemove.setText("Vymazat");

    listUsers.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    scrollPaneUsers.setViewportView(listUsers);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(labelPassword)
              .addComponent(labelName)
              .addComponent(labelIP)
              .addComponent(labelPort))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(textPort, 0, 0, Short.MAX_VALUE)
              .addComponent(textIP, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
              .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
              .addComponent(textName, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(buttonClose, javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(buttonNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSave)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonRemove)))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(scrollPaneUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(scrollPaneUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(labelName)
              .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(textIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(labelIP))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(labelPort)
              .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(labelPassword)
              .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(buttonNew)
              .addComponent(buttonSave)
              .addComponent(buttonRemove))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
            .addComponent(buttonClose)))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

		private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
			dialogUsers.close();
		}//GEN-LAST:event_buttonCloseActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToggleButton buttonClose;
  private javax.swing.JButton buttonNew;
  private javax.swing.JButton buttonRemove;
  private javax.swing.JButton buttonSave;
  private javax.swing.JLabel labelIP;
  private javax.swing.JLabel labelName;
  private javax.swing.JLabel labelPassword;
  private javax.swing.JLabel labelPort;
  private javax.swing.JList listUsers;
  private javax.swing.JPasswordField password;
  private javax.swing.JScrollPane scrollPaneUsers;
  private javax.swing.JFormattedTextField textIP;
  private javax.swing.JTextField textName;
  private javax.swing.JTextField textPort;
  // End of variables declaration//GEN-END:variables

}
