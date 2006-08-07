package gov.nih.nci.catrip.fqe.service;

import gov.nih.nci.catrip.fqe.utils.PropertyReader;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.lang.reflect.Constructor;

import java.util.Enumeration;
import java.util.Properties;


public class ServiceClientFactory {
    private Properties properties;
    public ServiceClientFactory() { 
    }

    public Object getSeviceClient(String serviceURL) {
        
        Object client = null;
        Class cls  = null;
        
            try {
            String className = PropertyReader.initProperties().getProperty(serviceURL);
            cls=Class.forName(className);
        /*    
                if(serviceURL.equals("http://localhost:8181/wsrf/services/cagrid/CaTissueCore_Full")){
                    cls = Class.forName("gov.nih.nci.cagrid.catissuecorefull.client.CaTissueCore_FullClient");
                } else if(serviceURL.equals("http://localhost:8181/wsrf_cae/services/cagrid/CAE")){
                    cls = Class.forName("gov.nih.nci.cagrid.cae.client.CAEClient");
                } 
        */        
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
