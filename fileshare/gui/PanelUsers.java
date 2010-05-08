package fileshare.gui;

import javax.swing.JList;
import javax.swing.JTextField;

/**
 * Form for users.
 *
 * @author Jakub Trmota (Forrest79)
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
    txtName = new javax.swing.JTextField();
    labelAddress = new javax.swing.JLabel();
    labelPort = new javax.swing.JLabel();
    labelPassword = new javax.swing.JLabel();
    txtPort = new javax.swing.JTextField();
    buttonClose = new javax.swing.JToggleButton();
    buttonNew = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();
    buttonRemove = new javax.swing.JButton();
    scrollPaneUsers = new javax.swing.JScrollPane();
    listUsers = new javax.swing.JList();
    txtAddress = new javax.swing.JTextField();
    txtPassword = new javax.swing.JTextField();

    labelName.setText("Name:");

    labelAddress.setText("Address:");

    labelPort.setText("Port:");

    labelPassword.setText("Password:");

    buttonClose.setText("Close");
    buttonClose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonCloseActionPerformed(evt);
      }
    });

    buttonNew.setText("New");
    buttonNew.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonNewActionPerformed(evt);
      }
    });

    buttonSave.setText("Save");
    buttonSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonSaveActionPerformed(evt);
      }
    });

    buttonRemove.setText("Remove");
    buttonRemove.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buttonRemoveActionPerformed(evt);
      }
    });

    listUsers.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    listUsers.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
        listUsersValueChanged(evt);
      }
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
              .addComponent(labelName)
              .addComponent(labelAddress)
              .addComponent(labelPort)
              .addComponent(labelPassword))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(txtPort, 0, 0, Short.MAX_VALUE)
              .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
              .addComponent(txtAddress, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
              .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
          .addComponent(buttonClose)
          .addGroup(layout.createSequentialGroup()
            .addComponent(buttonNew)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(buttonSave, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
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
              .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(labelAddress)
              .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(labelPort)
              .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(labelPassword)
              .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(buttonNew)
              .addComponent(buttonRemove)
              .addComponent(buttonSave))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
            .addComponent(buttonClose)))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

		private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCloseActionPerformed
			dialogUsers.close();
		}//GEN-LAST:event_buttonCloseActionPerformed

		private void buttonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNewActionPerformed
			dialogUsers.blank();
		}//GEN-LAST:event_buttonNewActionPerformed

		private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
			dialogUsers.save();
		}//GEN-LAST:event_buttonSaveActionPerformed

		private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed
			dialogUsers.remove();
		}//GEN-LAST:event_buttonRemoveActionPerformed

		private void listUsersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listUsersValueChanged
			dialogUsers.change();
		}//GEN-LAST:event_listUsersValueChanged


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToggleButton buttonClose;
  private javax.swing.JButton buttonNew;
  private javax.swing.JButton buttonRemove;
  private javax.swing.JButton buttonSave;
  private javax.swing.JLabel labelAddress;
  private javax.swing.JLabel labelName;
  private javax.swing.JLabel labelPassword;
  private javax.swing.JLabel labelPort;
  private javax.swing.JList listUsers;
  private javax.swing.JScrollPane scrollPaneUsers;
  private javax.swing.JTextField txtAddress;
  private javax.swing.JTextField txtName;
  private javax.swing.JTextField txtPassword;
  private javax.swing.JTextField txtPort;
  // End of variables declaration//GEN-END:variables

	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtAddress() {
		return txtAddress;
	}

	public JTextField getTxtPort() {
		return txtPort;
	}

	public JTextField getTxtPassword() {
		return txtPassword;
	}

	public JList getListUsers() {
		return listUsers;
	}
}
