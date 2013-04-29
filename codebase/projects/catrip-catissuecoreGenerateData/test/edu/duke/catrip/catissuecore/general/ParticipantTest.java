/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/**
 * Created on Oct 31, 2006 by PEEDI002
**/

package edu.duke.catrip.catissuecore.general;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.duke.catrip.catissuecore.util.HibernateUtil;
import edu.wustl.catissuecore.domainobject.Participant;
import edu.wustl.catissuecore.domainobject.impl.ParticipantImpl;

public class ParticipantTest extends TestCase {
  public ParticipantTest(String sTestName) {
      super(sTestName);
  }


   public void setUp() {

   }

   public void tearDown() {
   }


   public static Test suite() {
       return new TestSuite(ParticipantTest.class);
   }



   public void testSelect() throws Exception {

	   final boolean DEBUG = true;
	   
	   	   		if (DEBUG) System.out.println("Inside testSelect() - Setting up a session...");
           Session session = HibernateUtil.currentSession();

           		if (DEBUG) System.out.println("Inside testSelect() - Setting up a tx...");
           Transaction tx = session.beginTransaction();

	   			if (DEBUG) System.out.println("Inside testSelect() - Setting up a result...");
           List result = new ArrayList();
           
  				if (DEBUG) System.out.println("Inside testSelect() - Querying the Participant Table");
  			
	   			if (DEBUG) System.out.println("Inside testSelect() - calling session.createQuery...");
	   			result = session.createQuery("from ParticipantImpl").list();
	   			
           if (DEBUG) System.out.println("Inside testSelect() - calling tx.commit()...");
           tx.commit();
                     
	   			if (DEBUG) System.out.println("Inside testSelect() - looping thru result set...");
           for (int i = 0; i<result.size(); i++) {
        	   Participant obj = (ParticipantImpl) result.get(i);
               System.out.println("ID is " + obj.getId());
               System.out.println("getFirstName is " + obj.getFirstName());
               System.out.println("getLastName is " + obj.getLastName());
               System.out.println("getBirthDate is " + obj.getBirthDate());
               System.out.println("getEthnicity is " + obj.getEthnicity());
               System.out.println("getGender is " + obj.getGender());
               System.out.println("getRace is " + obj.getRace());
           }
	   			if (DEBUG) System.out.println("Inside testSelect() - after for loop...");

 	   			if (DEBUG) System.out.println("Inside testSelect() - calling HibernateUtil.closeSession()...");
	   			HibernateUtil.closeSession();
   }
}




