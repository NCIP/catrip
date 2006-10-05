/**
 * CaTRIPTumorRegistryServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.catriptumorregistry.stubs.service;

public class CaTRIPTumorRegistryServiceLocator extends org.apache.axis.client.Service implements gov.nih.nci.cagrid.catriptumorregistry.stubs.service.CaTRIPTumorRegistryService {

    public CaTRIPTumorRegistryServiceLocator() {
    }


    public CaTRIPTumorRegistryServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CaTRIPTumorRegistryServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CaTRIPTumorRegistryPortTypePort
    private java.lang.String CaTRIPTumorRegistryPortTypePort_address = "http://localhost:8080/wsrf/services/";

    public java.lang.String getCaTRIPTumorRegistryPortTypePortAddress() {
        return CaTRIPTumorRegistryPortTypePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CaTRIPTumorRegistryPortTypePortWSDDServiceName = "CaTRIPTumorRegistryPortTypePort";

    public java.lang.String getCaTRIPTumorRegistryPortTypePortWSDDServiceName() {
        return CaTRIPTumorRegistryPortTypePortWSDDServiceName;
    }

    public void setCaTRIPTumorRegistryPortTypePortWSDDServiceName(java.lang.String name) {
        CaTRIPTumorRegistryPortTypePortWSDDServiceName = name;
    }

    public gov.nih.nci.cagrid.catriptumorregistry.stubs.CaTRIPTumorRegistryPortType getCaTRIPTumorRegistryPortTypePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CaTRIPTumorRegistryPortTypePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCaTRIPTumorRegistryPortTypePort(endpoint);
    }

    public gov.nih.nci.cagrid.catriptumorregistry.stubs.CaTRIPTumorRegistryPortType getCaTRIPTumorRegistryPortTypePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gov.nih.nci.cagrid.catriptumorregistry.stubs.bindings.CaTRIPTumorRegistryPortTypeSOAPBindingStub _stub = new gov.nih.nci.cagrid.catriptumorregistry.stubs.bindings.CaTRIPTumorRegistryPortTypeSOAPBindingStub(portAddress, this);
            _stub.setPortName(getCaTRIPTumorRegistryPortTypePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCaTRIPTumorRegistryPortTypePortEndpointAddress(java.lang.String address) {
        CaTRIPTumorRegistryPortTypePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (gov.nih.nci.cagrid.catriptumorregistry.stubs.CaTRIPTumorRegistryPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                gov.nih.nci.cagrid.catriptumorregistry.stubs.bindings.CaTRIPTumorRegistryPortTypeSOAPBindingStub _stub = new gov.nih.nci.cagrid.catriptumorregistry.stubs.bindings.CaTRIPTumorRegistryPortTypeSOAPBindingStub(new java.net.URL(CaTRIPTumorRegistryPortTypePort_address), this);
                _stub.setPortName(getCaTRIPTumorRegistryPortTypePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CaTRIPTumorRegistryPortTypePort".equals(inputPortName)) {
            return getCaTRIPTumorRegistryPortTypePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://catriptumorregistry.cagrid.nci.nih.gov/CaTRIPTumorRegistry/service", "CaTRIPTumorRegistryService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://catriptumorregistry.cagrid.nci.nih.gov/CaTRIPTumorRegistry/service", "CaTRIPTumorRegistryPortTypePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        if ("CaTRIPTumorRegistryPortTypePort".equals(portName)) {
            setCaTRIPTumorRegistryPortTypePortEndpointAddress(address);
        }
        else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
