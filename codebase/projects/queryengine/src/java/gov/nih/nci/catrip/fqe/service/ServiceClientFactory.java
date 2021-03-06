/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.fqe.service;

import gov.nih.nci.catrip.fqe.utils.PropertyReader;

import java.lang.reflect.Constructor;

import java.util.Properties;


public class ServiceClientFactory {

    public ServiceClientFactory() {
    }

    /**
     * get the service Client for the given service URL . This info is configured in query_engine_services_config.xml
     *
     * @param serviceURL
     * @return
     */
    public Object getSeviceClient(String serviceURL) {

        Object client = null;
        Class cls  = null;

            try {

                String[] tokens = serviceURL.split("/");

                String serviceName = tokens[tokens.length-1];

                //String className = PropertyReader.initProperties().getProperty(serviceURL);
                 String className = PropertyReader.initProperties().getProperty(serviceName);
                 cls=Class.forName(className);

                // parameter is only serviceURL which is String
                Class paramTypes[] = new Class[1];
                paramTypes[0] = String.class;

                // pass serviceURL
                Object constructorArgs[] = new Object[1];
                constructorArgs[0] = serviceURL;

                //instantiate ServiceClient Constructor
                Constructor constructor = cls.getConstructor(paramTypes);
                client = constructor.newInstance(constructorArgs);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return client;
    }

}
