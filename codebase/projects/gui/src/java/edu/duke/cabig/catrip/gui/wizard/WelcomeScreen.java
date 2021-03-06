/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.catrip.gui.wizard;

import edu.duke.cabig.catrip.gui.components.CJFrame;

/**
 * Welcome Screen Window.
 *
 * @author  Sanjeev Agarwal
 */
public class WelcomeScreen extends CJFrame {
    
    /** Creates new form WelcomeScreen */
    public WelcomeScreen() {
        initComponents();
        
        getContentPane().setBackground(new java.awt.Color(255, 255, 255));
    }
    
    
    /** 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Netbeans Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        nciIcon = new javax.swing.JLabel();
        nihIcon = new javax.swing.JLabel();
        caBIGIcon = new javax.swing.JLabel();
        caGridIcon = new javax.swing.JLabel();
        lbl1 = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        lbl2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("edu/duke/cabig/catrip/gui/resources/ResourceBundle"); // NOI18N
        setTitle(bundle.getString("TITLE_WELCOME_SCREEN")); // NOI18N
        setAlwaysOnTop(true);
        setResizable(false);
        nciIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/footer_nci.gif")));

        nihIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/footer_nih.gif")));

        caBIGIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/cabig_logo.jpg")));

        caGridIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/caTrip_logo.gif")));

        lbl1.setText("Cancer Translational Research Informatics Platform (caTRIP)");

        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/login.gif")));
        loginBtn.setText("Proceed for Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edu/duke/cabig/catrip/gui/resources/btn_icons/exit.gif")));
        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        lbl2.setText("Federated Query Execution Interface for caGrid");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(caBIGIcon))
                    .add(layout.createSequentialGroup()
                        .add(109, 109, 109)
                        .add(lbl1))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(nihIcon)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(84, 84, 84)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(lbl2)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                        .add(loginBtn)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(exitBtn, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(nciIcon))))
                    .add(layout.createSequentialGroup()
                        .add(207, 207, 207)
                        .add(caGridIcon)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(caBIGIcon)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(caGridIcon)
                .add(16, 16, 16)
                .add(lbl1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lbl2)
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(loginBtn)
                    .add(exitBtn))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(nihIcon)
                    .add(nciIcon))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
// TODO add your handling code here:
        
        LoginScreen loginScreen= new LoginScreen();
        loginScreen.setVisible(true);
        loginScreen.center();
        this.dispose();
        
    }//GEN-LAST:event_loginBtnActionPerformed
    
    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
// TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WelcomeScreen ws= new WelcomeScreen();
                //ws.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                ws.setBounds(10,10,550,365);
                ws.center();
                ws.setVisible(true);
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel caBIGIcon;
    private javax.swing.JLabel caGridIcon;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel nciIcon;
    private javax.swing.JLabel nihIcon;
    // End of variables declaration//GEN-END:variables
    
}
