
package edu.duke.cabig.catrip.gui.util;

/**
 *
 * @author Sanjeev Agarwal
 */
public class CommonUtils {
    
    public static String getStringException(Exception e){
        String exceptionStr = e.getMessage()+"\n";
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < stackTrace.length; i++) {
            exceptionStr += "\t"+stackTrace[i].toString()+"\n";
        }
        
        return exceptionStr;
    } 
    
    
    
    
     public static String getFullStringException(Exception e){
        String exceptionStr = e.getMessage()+"\n";
        // write the exception to a ByteArrayStream and than read that stream again..
        // this will ensure that the nested exceptions are also captured..
        
        return exceptionStr;
    }
    
     
    
    
}
