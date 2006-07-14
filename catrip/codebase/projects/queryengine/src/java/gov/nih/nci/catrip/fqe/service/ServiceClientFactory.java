package gov.nih.nci.catrip.fqe.service;
import gov.nih.nci.cagrid.client.HelloWorldClient;
import java.rmi.RemoteException;
import org.apache.axis.types.URI.MalformedURIException;

public class ServiceClientFactory {
    public ServiceClientFactory() { }
    
    public HelloWorldClient getSeviceClient(String serviceURL) {
        HelloWorldClient client = null;
        if (serviceURL.equals("http://localhost:8181/wsrf/services/cagrid/HelloWorld")){
            try {
                client = new HelloWorldClient(serviceURL);
            } catch (MalformedURIException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }        
        return client;
    }
}
