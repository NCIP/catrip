
package edu.duke.cabig.catrip.gui.wizard;

import edu.duke.cabig.catrip.gui.common.IndentityProviderBean;
import edu.duke.cabig.catrip.gui.components.CJFrame;
import edu.duke.cabig.catrip.gui.security.AuthenticationErrorException;
import edu.duke.cabig.catrip.gui.security.AuthenticationManager;
import edu.duke.cabig.catrip.gui.security.AuthenticationManagerFactory;
import edu.duke.cabig.catrip.gui.security.LoginProviderLocatorFactory;
import edu.duke.cabig.catrip.gui.util.GUIConstants;
import java.awt.Cursor;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import org.apache.commons.logging.Log;
import edu.duke.cabig.catrip.gui.util.Logger;
import javax.swing.JOptionPane;


/**
 * Login Screen window.
 *
 * @author  Sanjeev Agarwal
 */
public class LoginScreen extends CJFrame {
    static Log log = Logger.getDefaultLogger();
    
    private Map<String,IndentityProviderBean> idpBeans = new HashMap<String,IndentityProviderBean>();
    
    private int loginAttempts = 0;
    
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
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
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
        visualGuiChkBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle"); // NOI18N
        setTitle(bundle.getString("TITLE_LOGIN_SCREEN")); // NOI18N
        setAlwaysOnTop(true);
        setName("LoginScreen");
        setResizable(false);
        loginIdLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        loginIdLbl.setText(bundle.getString("LOGIN_SCREEN_WIZARD_LBL_LOGIN_ID")); // NOI18N

        passwordLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        passwordLbl.setText(bundle.getString("LOGIN_SCREEN_WIZARD_LBL_PASSWORD")); // NOI18N

        identityProviderLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        identityProviderLbl.setText(bundle.getString("LOGIN_SCREEN_WIZARD_LBL_ID_PROVIDER")); // NOI18N

        userId.setText("guest");
        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/a11y/a11yBundle"); // NOI18N
        userId.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.userId.name")); // NOI18N
        userId.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.userId.description")); // NOI18N

        identityProvider.setModel(getComboBoxModel());
        identityProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identityProviderActionPerformed(evt);
            }
        });

        identityProvider.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.identityProvider.name")); // NOI18N
        identityProvider.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.identityProvider.description")); // NOI18N

        password.setText("catrip1");
        password.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.password.name")); // NOI18N
        password.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.password.description")); // NOI18N

        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/login.gif")));
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        loginBtn.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.loginBtn.name")); // NOI18N
        loginBtn.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.loginBtn.description")); // NOI18N

        clearBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/clear.gif")));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        clearBtn.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.clearBtn.name")); // NOI18N
        clearBtn.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.clearBtn.description")); // NOI18N

        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/exit.gif")));
        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        exitBtn.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.exitBtn.name")); // NOI18N
        exitBtn.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.exitBtn.description")); // NOI18N

        visualGuiChkBox.setText(bundle.getString("LOGIN_SCREEN_RADIO_BTN_LBL")); // NOI18N
        visualGuiChkBox.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        visualGuiChkBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
        visualGuiChkBox.getAccessibleContext().setAccessibleName(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.visualGuiChkBox.name")); // NOI18N
        visualGuiChkBox.getAccessibleContext().setAccessibleDescription(bundle1.getString("edu.duke.cabig.catrip.gui.wizard.LoginScreen.visualGuiChkBox.description")); // NOI18N

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
                    .add(visualGuiChkBox)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(loginBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .add(29, 29, 29)
                        .add(clearBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                        .add(30, 30, 30)
                        .add(exitBtn, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                    .add(password, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .add(userId, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .add(identityProvider, 0, 330, Short.MAX_VALUE))
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(visualGuiChkBox)
                .add(14, 14, 14)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(loginBtn)
                    .add(clearBtn)
                    .add(exitBtn))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void identityProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identityProviderActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_identityProviderActionPerformed
    
    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        userId.setText("");
        password.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed
    
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        exit();
    }//GEN-LAST:event_exitBtnActionPerformed
    
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        boolean authenticate = false;
        
        loginAttempts++;
        
        // check if that is a guest demo login.
        boolean guestUser = false;
        String userIdStr = userId.getText().trim();
        log.info(" User Login id: "+userIdStr);
        if (userIdStr.equalsIgnoreCase("guest")){
            guestUser = true;
            authenticate = true;
        }
        
        
        if (!guestUser){
            
            IndentityProviderBean idpBean = getIdpBeans().get(identityProvider.getSelectedItem().toString());
            try {
                log.info("\n Trying to authenticate the user: "+userIdStr +"\n IDP url: "+idpBean.getIdpUrl()+"\n Dorian URL:"+idpBean.getDorianUrl()+"\n");
                System.out.println("xxxx"+userId.getText().trim()+" : "+ idpBean.getIdpUrl()+" : "+idpBean.getDorianUrl());
                AuthenticationManager authenticationManager = AuthenticationManagerFactory.getAuthenticationManager(idpBean.getDisplayName());
                authenticate = authenticationManager.authenticate(userId.getText().trim(), password.getText().trim(), idpBean.getIdpUrl(),idpBean.getDorianUrl());
            } catch (AuthenticationErrorException ex) {
                ex.printStackTrace();
            }
            
        }
        
        
        if (authenticate) {
            
            if (visualGuiChkBox.isSelected()){ // show the complax gui search service screen..
                log.info(" Going into Advance GUI mode. ");
                GUIConstants.simpleGui = false;
                
                SearchServicesScreen screen= new SearchServicesScreen();
                screen.center();
                screen.setVisible(true);
                
            }else { // show simple gui main screen directly..
                log.info(" Going into Simple GUI mode. ");
                // disable all the buttons first and show a waiting cursor..
                disableButtons();
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
                GUIConstants.simpleGui = true;
                
                MainFrame mf = new MainFrame();
                
                // added added
                // remove and disable the left hand side split pane..
                mf.getCentralSplitPane().setDividerLocation(1);
                mf.getCentralSplitPane().setOneTouchExpandable(false);
                mf.getCentralSplitPane().setDividerSize(0);
                mf.getCentralSplitPane().setEnabled(false);
                
                // reset the top size of the right hand side split pane.
                mf.getRightSplitPane().setDividerLocation(450);
                // added added
                
                
                mf.getVisualPanel().getTabbedPane().setSelectedIndex(2);
                mf.setVisible(true);
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            
            this.dispose();
            
            
        } else {
            // tell the user to login again or exit.. // popup a info window..
            new JOptionPane().showMessageDialog(this ,"Username or Password is incorrect. \nPlease re-enter the correct Username and Password. \n\nLogin Attempts : "+loginAttempts,"Login Error.", JOptionPane.ERROR_MESSAGE);
            
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
        IndentityProviderBean[] indentityProviderBeans = LoginProviderLocatorFactory.getLoginProviderLocator().getLoginProviderURLs();
        String idpNames[] = new String[indentityProviderBeans.length];
        for (int i=0; i<indentityProviderBeans.length ; i++) {
            idpNames[i] = ((IndentityProviderBean)indentityProviderBeans[i]).getDisplayName();
            idpBeans.put(idpNames[i],(IndentityProviderBean)indentityProviderBeans[i]);
        }
        
        // log idp stuff..
        log.debug(" IDPs : " );
        for (int i = 0; i < idpNames.length; i++) {
            log.debug(" IDP Name: "+ idpNames[i].toString() +" | " +  " IDP URL: "+ indentityProviderBeans[i].getIdpUrl() +" | " +  " Dorian URL: "+ indentityProviderBeans[i].getDorianUrl() );
        }
        
        
        DefaultComboBoxModel cb = new javax.swing.DefaultComboBoxModel(idpNames);
        
        return cb;
    }
    
    private Map<String,IndentityProviderBean> getIdpBeans() {
        return idpBeans;
    }
    private void disableButtons(){
        clearBtn.setEnabled(false);
        exitBtn.setEnabled(false);
        loginBtn.setEnabled(false);
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
    private javax.swing.JCheckBox visualGuiChkBox;
    // End of variables declaration//GEN-END:variables
    
}
