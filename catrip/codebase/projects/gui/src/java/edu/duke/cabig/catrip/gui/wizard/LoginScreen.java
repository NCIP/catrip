/*
 * LoginScreen.java
 *
 * Created on June 9, 2006, 12:59 PM
 */

package edu.duke.cabig.catrip.gui.wizard;

import edu.duke.cabig.catrip.gui.components.CJFrame;
import edu.duke.cabig.catrip.gui.security.AuthenticationManager;
import edu.duke.cabig.catrip.gui.security.LoginProviderLocatorFactory;
import javax.swing.DefaultComboBoxModel;

        
        
        
/**
 *
 * @author  Sanjeev Agarwal
 */
public class LoginScreen extends CJFrame {
    
    
    /** Creates new form LoginScreen */
    public LoginScreen() {
        initComponents();
        init();
    }
    
    private void init(){
        // First check if the IDP list is populated into combobox..
        loginBtn.setEnabled(false);
        if (identityProvider.getModel().getSize() > 0){
            loginBtn.setEnabled(true);
            identityProvider.setSelectedIndex(0);
        }
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        loginIdLbl = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        identityProviderLbl = new javax.swing.JLabel();
        userId = new javax.swing.JTextField();
        identityProvider = new javax.swing.JComboBox();
        password = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle").getString("TITLE_LOGIN_SCREEN"));
        setAlwaysOnTop(true);
        setName("LoginScreen");
        setResizable(false);
        loginIdLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        loginIdLbl.setText(java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle").getString("LOGIN_SCREEN_WIZARD_LBL_LOGIN_ID"));

        passwordLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        passwordLbl.setText(java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle").getString("LOGIN_SCREEN_WIZARD_LBL_PASSWORD"));

        identityProviderLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        identityProviderLbl.setText(java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle").getString("LOGIN_SCREEN_WIZARD_LBL_ID_PROVIDER"));

        userId.setText("User ID");

        identityProvider.setModel(getComboBoxModel());

        password.setText("User Password");

        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, identityProviderLbl)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, passwordLbl)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, loginIdLbl))
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(loginBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .add(29, 29, 29)
                        .add(clearBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .add(30, 30, 30)
                        .add(exitBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                    .add(password, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .add(userId, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .add(identityProvider, 0, 320, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {identityProviderLbl, loginIdLbl, passwordLbl}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(loginIdLbl)
                    .add(userId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(passwordLbl)
                    .add(password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(identityProviderLbl)
                    .add(identityProvider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(17, 17, 17)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(clearBtn)
                    .add(exitBtn)
                    .add(loginBtn))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
// TODO add your handling code here:
        userId.setText("");
        password.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed
    
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        exit();
    }//GEN-LAST:event_exitBtnActionPerformed
    
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
// TODO add your handling code here:
        if (AuthenticationManager.authenticate(userId.getText().trim(), password.getPassword().toString().trim(), identityProvider.getSelectedItem().toString() )){
            
        
        SearchServicesScreen screen= new SearchServicesScreen();
        screen.center();
        screen.setVisible(true);
        this.dispose();
        System.out.println("#####  "+identityProvider.getSelectedItem());
        
         }
    }//GEN-LAST:event_loginBtnActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }
    
    private DefaultComboBoxModel getComboBoxModel(){
        String[] urls = LoginProviderLocatorFactory.getLoginProviderLocator().getLoginProviderURLs();
        
        DefaultComboBoxModel cb = new javax.swing.DefaultComboBoxModel(urls);
        
        
        
        return cb;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JComboBox identityProvider;
    private javax.swing.JLabel identityProviderLbl;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel loginIdLbl;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JTextField userId;
    // End of variables declaration//GEN-END:variables
    
}