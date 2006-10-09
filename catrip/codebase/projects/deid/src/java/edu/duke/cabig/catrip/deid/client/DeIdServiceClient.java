/*
 * Created on Oct 9, 2006
 */
package edu.duke.cabig.catrip.deid.client;

import edu.duke.cabig.catrip.deid.DeIdService;

public class DeIdServiceClient
	implements DeIdService
{
	private CatripDeid10Stub client;
	
	public DeIdServiceClient(String targetEndpoint) 
		throws java.lang.Exception 
	{
		client = new  CatripDeid10Stub(targetEndpoint);
	}
		   
	public String deid(String phi) 
		throws Exception
	{
		CatripDeid10Stub.Deid param1 = new CatripDeid10Stub.Deid();
		param1.setPhi(phi);
		return client.deid(param1).get_return();
	}
}
