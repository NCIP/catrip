package gov.nih.nci.catrip.fqe.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;

import java.util.Properties;

public class PropertyReader {
    public PropertyReader() {
    }
    public static String CATRIP_HOME = System.getProperty("user.home") + File.separator + ".caTRIP";

    public static Properties  initProperties(){
    
        //InputStream is = ClassLoader.getSystemResourceAsStream("gov/nih/nci/catrip/fqe/utils/service_config.xml");
        Properties properties = new Properties();
        try {
               properties.loadFromXML(new FileInputStream(new File ( CATRIP_HOME + File.separator + "service_config.xml")));
           } catch (IOException e) {
              e.printStackTrace();
              System.exit(1);
        }
        return properties;
    }
    
}
