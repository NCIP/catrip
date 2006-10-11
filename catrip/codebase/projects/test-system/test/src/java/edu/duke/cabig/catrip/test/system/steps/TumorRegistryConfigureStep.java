/*
 * Created on Jun 11, 2006
 */
package edu.duke.cabig.catrip.test.system.steps;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import com.atomicobject.haste.framework.Step;

import edu.duke.cabig.catrip.test.system.util.HibernatePropertiesUtil;

public class TumorRegistryConfigureStep
	extends Step
{
	private File serviceDir;
	
	public TumorRegistryConfigureStep(File serviceDir) 
	{
		super();
		
		this.serviceDir = serviceDir;
	}
	
	public void runStep() 
		throws IOException
	{
		Properties props = new Properties();
		props.setProperty(
			"hibernate.connection.url", 
			System.getProperty("tumorregistry.connection.url", "jdbc:oracle:thin:@pparker:1521:TRIP")
		);
		HibernatePropertiesUtil.configure(
			new File(serviceDir, "hibernate.properties"),
			props
		);
	}
}
