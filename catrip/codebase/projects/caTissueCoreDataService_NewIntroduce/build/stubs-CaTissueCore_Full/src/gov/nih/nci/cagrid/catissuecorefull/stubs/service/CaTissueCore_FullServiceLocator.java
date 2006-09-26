/**
 * CaTissueCore_FullServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Mar 03, 2006 (12:17:06 EST) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.catissuecorefull.stubs.service;

public class CaTissueCore_FullServiceLocator extends org.apache.axis.client.Service implements gov.nih.nci.cagrid.catissuecorefull.stubs.service.CaTissueCore_FullService {

    public CaTissueCore_FullServiceLocator() {
    }


    public CaTissueCore_FullServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CaTissueCore_FullServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CaTissueCore_FullPortTypePort
    private java.lang.String CaTissueCore_FullPortTypePort_address = "http://localhost:8080/wsrf/services/";

    public java.lang.String getCaTissueCore_FullPortTypePortAddress() {
        return CaTissueCore_FullPortTypePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CaTissueCore_FullPortTypePortWSDDServiceName = "CaTissueCore_FullPortTypePort";

    public java.lang.String getCaTissueCore_FullPortTypePortWSDDServiceName() {
        return CaTissueCore_FullPortTypePortWSDDServiceName;
    }

    public void setCaTissueCore_FullPortTypePortWSDDServiceName(java.lang.String name) {
        CaTissueCore_FullPortTypePortWSDDServiceName = name;
    }

    public gov.nih.nci.cagrid.catissuecorefull.stubs.CaTissueCore_FullPortType getCaTissueCore_FullPortTypePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CaTissueCore_FullPortTypePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCaTissueCore_FullPortTypePort(endpoint);
    }

    public gov.nih.nci.cagrid.catissuecorefull.stubs.CaTissueCore_FullPortType getCaTissueCore_FullPortTypePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gov.nih.nci.cagrid.catissuecorefull.stubs.bindings.CaTissueCore_FullPortTypeSOAPBindingStub _stub = new gov.nih.nci.cagrid.catissuecorefull.stubs.bindings.CaTissueCore_FullPortTypeSOAPBindingStub(portAddress, this);
            _stub.setPortName(getCaTissueCore_FullPortTypePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCaTissueCore_FullPortTypePortEndpointAddress(java.lang.String address) {
        CaTissueCore_FullPortTypePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (gov.nih.nci.cagrid.catissuecorefull.stubs.CaTissueCore_FullPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                gov.nih.nci.cagrid.catissuecorefull.stubs.bindings.CaTissueCore_FullPortTypeSOAPBindingStub _stub = new gov.nih.nci.cagrid.catissuecorefull.stubs.bindings.CaTissueCore_FullPortTypeSOAPBindingStub(new java.net.URL(CaTissueCore_FullPortTypePort_address), this);
                _stub.setPortName(getCaTissueCore_FullPortTypePortWSDDServiceName());
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
        if ("CaTissueCore_FullPortTypePort".equals(inputPortName)) {
            return getCaTissueCore_FullPortTypePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://helloworld.cagrid.nci.nih.gov/CaTissueCore_Full/service", "CaTissueCore_FullService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://helloworld.cagrid.nci.nih.gov/CaTissueCore_Full/service", "CaTissueCore_FullPortTypePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        if ("CaTissueCore_FullPortTypePort".equals(portName)) {
            setCaTissueCore_FullPortTypePortEndpointAddress(address);
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
