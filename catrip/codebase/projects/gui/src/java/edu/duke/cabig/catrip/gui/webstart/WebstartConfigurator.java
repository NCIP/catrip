

package edu.duke.cabig.catrip.gui.webstart;

import edu.duke.cabig.catrip.gui.util.UnZip;
import edu.duke.catrip.config.CatripConfigurationDocument;
import edu.duke.catrip.config.GuiConfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Sanjeev Agarwal
 */
public class WebstartConfigurator {
    
    
    public static final String CATRIP_HOME = System.getProperty("user.home") + File.separator + ".caTRIP";
    public static final String CATRIP_CONFIG_FILE_LOCATION = CATRIP_HOME + File.separator + "catrip-config.xml";
    public static final String CATRIP_CONF_HOME = CATRIP_HOME + File.separator + "conf";
    
    
    /** Creates a new instance of WebstartConfigurator */
    public WebstartConfigurator() {
    }
    
    public static void configure(){
        String dir  = System.getProperty("user.home")+File.separator+".caTRIP";
        File confDir = new File(dir);
        if (!confDir.exists()){
            configureForWebstart();
        }
    }
    
    
    
    public static void configureForWebstart(){
        try {
            
            // sanjeev: locate the zip file containing the configuration files.
            String inFilename = "conf.zip";
            String dir  = System.getProperty("user.home")+File.separator+".caTRIP";
            File confDir = new File(dir);confDir.mkdir();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(inFilename);;//ClassLoader.getSystemClassLoader().getResourceAsStream(inFilename);
            String outFilename = dir+File.separator+"conf.zip";
            OutputStream out = new FileOutputStream(outFilename);
            
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
            
            UnZip.unzip(outFilename,dir);  
            new File(outFilename).delete();
            
            
            
            // sanjeev: replace the conf dir location.
            CatripConfigurationDocument conf = null;
            String configXMLFile = CATRIP_CONFIG_FILE_LOCATION;
            try {
                conf = CatripConfigurationDocument.Factory.parse(new File(configXMLFile));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            GuiConfiguration guiConfig =  conf.getCatripConfiguration().getGuiConfiguration();
            guiConfig.setRootDirectory(CATRIP_CONF_HOME);
            conf.save(new File(CATRIP_CONFIG_FILE_LOCATION));
            
            
            // sanjeev: replace the conf dir location.
            String fqeFile = CATRIP_HOME + File.separator + "query_engine_services_config.xml";
            Properties properties = new Properties(); 
            try {
                properties.loadFromXML(new FileInputStream(new File(fqeFile )));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String wsddFile = dir+File.separator+"conf"+File.separator+"client-config.wsdd";
            properties.setProperty("clientConfigWsdd",wsddFile);
            OutputStream os = new FileOutputStream(fqeFile);
            properties.storeToXML(os,"Modified via webstart.");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
