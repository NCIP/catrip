package gov.nih.nci.catrip.fqe.service;

import gov.nih.nci.cagrid.client.HelloWorldClient;
import gov.nih.nci.cagrid.client.MyServiceClient;

import java.rmi.RemoteException;
import org.apache.axis.types.URI.MalformedURIException;


public class ServiceClientFactory {
    public ServiceClientFactory() { }
    
    
    public Object getSeviceClient(String serviceURL) {
        Object client = null;
        
            try {
                if (serviceURL.equals("http://localhost:8181/wsrf/services/cagrid/HelloWorld")){
                    client = new HelloWorldClient(serviceURL);
                } else if(serviceURL.equals("http://localhost:8181/wsrf/services/cagrid/MyService")){
                    client = new MyServiceClient(serviceURL);
                }
            } catch (MalformedURIException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            
            return client;
        }        
        
    
    
    
/*
    public MyServiceClient getSeviceClientMS(String serviceURL) {
        MyServiceClient client = null;
        if (serviceURL.equals("http://localhost:8181/wsrf/services/cagrid/MyService")){
            try {
                client = new MyServiceClient(serviceURL);
            } catch (MalformedURIException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }        
        return client;
    }
*/
}
