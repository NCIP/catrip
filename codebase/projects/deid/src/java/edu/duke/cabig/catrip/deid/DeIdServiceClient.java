/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * Created on Oct 9, 2006
 */
package edu.duke.cabig.catrip.deid;

import edu.duke.cabig.catrip.deid.client.CatripDeid10Stub;

public class DeIdServiceClient
	implements DeIdService
{
	private CatripDeid10Stub client;
	private String user;
	private String password;
	
	public DeIdServiceClient(String targetEndpoint) 
		throws java.lang.Exception 
	{
		this(targetEndpoint, null, null);
	}
	
	public DeIdServiceClient(String targetEndpoint, String user, String password) 
		throws java.lang.Exception 
	{
		super();
		
		this.client = new CatripDeid10Stub(targetEndpoint);
		this.user = user;
		this.password = password;
	}
		   
	public String deid(String phi) 
		throws Exception
	{
		CatripDeid10Stub.Deid param1 = new CatripDeid10Stub.Deid();

		client.setUser(user);
		client.setPassword(password);
		
// 		SOAPHeaderBlock header = env.getHeader().addHeaderBlock("security", env.getNamespace());
// 		header.addAttribute("user", "mccon012", null);
// 		header.addAttribute("password", "mypassword", null);
//		client._getServiceClient().addHeader(el);
//		client._getServiceClient().addStringHeader(new QName("security", "password"), "hi");
		
		param1.setPhi(phi);
		return client.deid(param1).get_return();
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}
}
