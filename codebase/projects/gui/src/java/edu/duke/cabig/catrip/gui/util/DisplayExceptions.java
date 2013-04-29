/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */


package edu.duke.cabig.catrip.gui.util;

import edu.duke.cabig.catrip.gui.components.CJDialog;
import edu.duke.cabig.catrip.gui.panels.MessageDisplayPanel;
import java.awt.Frame;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JFrame;

/**
 *
 * @author Sanjeev Agarwal
 */
public class DisplayExceptions {
    
    /** Creates a new instance of DisplayExceptions */
    public DisplayExceptions() {
    }
    
    
    public static void display(String title, String msg, Exception e){ 

        CJDialog jd = new CJDialog((JFrame) findActiveFrame(), title); 
        
        MessageDisplayPanel ws = new MessageDisplayPanel(jd);
        ws.getTextArea().setText(formatStackTrace(e));
        ws.getTextArea().setCaretPosition(0);
        ws.getMsgLbl().setText(msg);
        ws.getMsgLbl().setToolTipText(msg);
                
        jd.add(ws);
        jd.setBounds(1,1,660,557);
        jd.center();jd.setModal(true);
        jd.setVisible(true);
        
    } 
    
    
    
    private static String formatStackTrace(Throwable e){
                
        if (e instanceof java.lang.reflect.InvocationTargetException){
            e = ((java.lang.reflect.InvocationTargetException)e).getTargetException();
            String serverMsg = "\n\n Exception at Server side:\n\n";
            try{
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true); 
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            serverMsg = serverMsg+getErrorMsgsOnly(sw.toString());
            } catch (Exception ee){}
            
           return serverMsg;
            
        }
        
        StackTraceElement[] ste = e.getStackTrace();
        
        int numLines = 5;
        String msg = e.toString();
        for (int i = 0; i < (ste.length >numLines ? numLines : ste.length) ; i++) {
            msg = msg + "\n\tat " + ste[i].toString();
        }
        
        Throwable t = e.getCause();  
        if (t != null){
           msg =  msg +"\n\n"+ formatStackTrace(t);
        }
            
        return msg;
    } 
    
    
    private static Frame findActiveFrame() { 
        Frame[] frames = JFrame.getFrames(); 
        for (int i = 0; i < frames.length; i++) {
            Frame frame = frames[i]; 
            if (frame.isVisible()) {
                return frame;
            }
        }
        return null;
    }
    
    private static String getErrorMsgsOnly(String axisFault){
        
        String errorMsg = "- Error messages from Server.\n";
        
        try{
        
        String[] descriptions = axisFault.split("Description");
        
        for (int i = 0; i < descriptions.length-1; i++) {
            i++;
            String uncut = descriptions[i];
            int left = uncut.indexOf(">");
            int right = uncut.indexOf("<");

            errorMsg = errorMsg+"\n\t"+uncut.substring(left+1, right);
        }
        errorMsg = errorMsg+"\n";
        
        String[] causedBy = axisFault.split("Caused by:");
        
        for (int i = 1; i < causedBy.length; i++) {
            String uncut = causedBy[i];
            String oneline = uncut.substring(0,uncut.indexOf("\n"));
            errorMsg = errorMsg+"\n\t"+"Caused by: "+oneline;
        }
        errorMsg = errorMsg+"\n\n";
        
        } catch (Exception e){}
        
        return errorMsg;//axisFault;
    }
    
    
}
