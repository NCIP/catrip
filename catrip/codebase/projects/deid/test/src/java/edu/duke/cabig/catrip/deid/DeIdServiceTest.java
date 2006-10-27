/*
 * Created on Oct 4, 2006
 */
package edu.duke.cabig.catrip.deid;

import java.util.Random;

import junit.framework.TestCase;
import edu.duke.cabig.catrip.deid.util.RandomUtils;

public abstract class DeIdServiceTest
	extends TestCase
{
	private Random rand;
	
	public DeIdServiceTest(String name)
	{
		super(name);
		
		this.rand = new Random();
	}
	
	public void performDeIdTest(DeIdService service) throws Exception
	{
		// generate phi
		String[] phi = new String[20];
		for (int i = 0; i < phi.length; i++) {
			phi[i] = RandomUtils.generateRandomValue(rand, 25);
		}
		
		//  get random vals
		String[] val = new String[phi.length];
		for (int i = 0; i < phi.length; i++) {
			val[i] = service.deid(phi[i]);
		}
		
		// check for no repeats
		for (int i = 0; i < val.length; i++) {
			for (int j = i+1; j < val.length; j++) {
				assertFalse(val[i].equals(val[j]));;
			}
		}
		
		// check that we are getting the same val back
		for (int i = 0; i < phi.length; i++) {
			String nextVal = service.deid(phi[i]);
			assertEquals(val[i], nextVal);
		}				
	}
}
