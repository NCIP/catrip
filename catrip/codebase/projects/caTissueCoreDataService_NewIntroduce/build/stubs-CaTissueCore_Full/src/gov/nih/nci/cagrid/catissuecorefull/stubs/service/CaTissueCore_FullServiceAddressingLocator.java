/**
 * CaTissueCore_FullServiceAddressingLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.2RC2 Mar 03, 2006 (12:17:06 EST) WSDL2Java emitter.
 */

package gov.nih.nci.cagrid.catissuecorefull.stubs.service;

public class CaTissueCore_FullServiceAddressingLocator extends gov.nih.nci.cagrid.catissuecorefull.stubs.service.CaTissueCore_FullServiceLocator implements gov.nih.nci.cagrid.catissuecorefull.stubs.service.CaTissueCore_FullServiceAddressing {
    public gov.nih.nci.cagrid.catissuecorefull.stubs.CaTissueCore_FullPortType getCaTissueCore_FullPortTypePort(org.apache.axis.message.addressing.EndpointReferenceType reference) throws javax.xml.rpc.ServiceException {
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
	gov.nih.nci.cagrid.catissuecorefull.stubs.CaTissueCore_FullPortType _stub = getCaTissueCore_FullPortTypePort(endpoint);
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
