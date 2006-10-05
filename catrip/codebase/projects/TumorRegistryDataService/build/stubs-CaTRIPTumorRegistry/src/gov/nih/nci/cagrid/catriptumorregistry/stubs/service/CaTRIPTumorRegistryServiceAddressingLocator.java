/**
 * CaTRIPTumorRegistryServiceAddressingLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Jun 16, 2005 (10:54:53 EDT) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.catriptumorregistry.stubs.service;

public class CaTRIPTumorRegistryServiceAddressingLocator extends gov.nih.nci.cagrid.catriptumorregistry.stubs.service.CaTRIPTumorRegistryServiceLocator implements gov.nih.nci.cagrid.catriptumorregistry.stubs.service.CaTRIPTumorRegistryServiceAddressing {
    public gov.nih.nci.cagrid.catriptumorregistry.stubs.CaTRIPTumorRegistryPortType getCaTRIPTumorRegistryPortTypePort(org.apache.axis.message.addressing.EndpointReferenceType reference) throws javax.xml.rpc.ServiceException {
	org.apache.axis.message.addressing.AttributedURI address = reference.getAddress();
	if (address == null) {
		throw new javax.xml.rpc.ServiceException("No address in EndpointReference");
	}
	java.net.URL endpoint;
	try {
		endpoint = new java.net.URL(address.toString());
	} catch (java.net.MalformedURLException e) {
		throw new javax.xml.rpc.ServiceException(e);
	}
	gov.nih.nci.cagrid.catriptumorregistry.stubs.CaTRIPTumorRegistryPortType _stub = getCaTRIPTumorRegistryPortTypePort(endpoint);
	if (_stub != null) {
		org.apache.axis.message.addressing.AddressingHeaders headers =
			new org.apache.axis.message.addressing.AddressingHeaders();
		headers.setTo(address);
		headers.setReferenceProperties(reference.getProperties());
		((javax.xml.rpc.Stub)_stub)._setProperty(org.apache.axis.message.addressing.Constants.ENV_ADDRESSING_SHARED_HEADERS, headers);
	}
	return _stub;
    }


}