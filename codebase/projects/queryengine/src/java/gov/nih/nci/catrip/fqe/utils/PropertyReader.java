/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

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

    /**
     * read property file .
     * @return
     */
    public static Properties  initProperties(){

        //InputStream is = ClassLoader.getSystemResourceAsStream("gov/nih/nci/catrip/fqe/utils/service_config.xml");
        Properties properties = new Properties();
        try {
               properties.loadFromXML(new FileInputStream(new File ( CATRIP_HOME + File.separator + "query_engine_services_config.xml")));
           } catch (IOException e) {
              e.printStackTrace();
              //System.exit(1);
        }
        return properties;
    }

}
