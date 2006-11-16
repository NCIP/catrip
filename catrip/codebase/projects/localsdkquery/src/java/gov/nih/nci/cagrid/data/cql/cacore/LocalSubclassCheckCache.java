package gov.nih.nci.cagrid.data.cql.cacore;

import gov.nih.nci.cagrid.data.QueryProcessingException;



import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;


/**
 *  SubclassCheckCache
 *  Checks that a given class has or does not have a class property in the
 *  caCORE backend data source.  This property is only present for classes
 *  which have subclasses stored in the database as well.
 *
 * @author <A HREF="MAILTO:ervin@bmi.osu.edu">David W. Ervin</A>
 *
 * @created Sep 19, 2006
 * @version $Id: LocalSubclassCheckCache.java,v 1.2 2006-11-16 20:49:38 srakkala Exp $
 */
public class LocalSubclassCheckCache {

	private static Map cache = null;
        public static final String CLASS_PROPERTY_NOT_FOUND_ERROR = "could not resolve property: class of";
	
	/**
	 * Executes a test query against the application service to see if the specified
	 * type has a class discriminator parameter, which indicates that it has subclass
	 * instances available in the data source.  If an exception indicating the type
	 * has no class discriminator is thrown, this method returns false.  If the query
	 * succedes, the method returns true.  In this case, this is a somewhat expensive
	 * operation on the first call using that class name.  All subsequent calls with
	 * the same class name are cached and return in order log(n) time, where n is the
	 * number of unique class names checked with this method.
	 * 
	 * @param className
	 * 		The name of the class to test
	 * @param 
	 * 		The application service to submit the test query to
	 * @return
	 * 		True if the class discriminator is present, false otherwise
	 * @throws QueryProcessingException
	 */
	 public static boolean hasClassProperty(String className,String hibernateCfgFile,String dataBaseURL, String schemaOrUser) throws QueryProcessingException {
	     

	         if (cache == null) {
	                 cache = Collections.synchronizedMap(new HashMap());
	         }
	         Boolean flag = (Boolean) cache.get(className);
	         if (flag == null) {
	                 String testHql = "From " + className + " as c where c.class = " + className + ")";
	                 // try the query and catch application exceptions
	                 try {
	                     Session session = HibernateUtil.currentSession(hibernateCfgFile,dataBaseURL,schemaOrUser);
	                         //queryService.query(new HQLCriteria(testHql), className);
	                         session.createQuery(testHql).list();
	                         flag = Boolean.TRUE;
	                 } catch (HibernateException ex) {
	                         // check the exception message for the magical words from Hibernate...
	                         String message = ex.getMessage();
	                         if (message.startsWith(CLASS_PROPERTY_NOT_FOUND_ERROR)) {
	                                 flag = Boolean.FALSE;
	                         } else {
	                                 // uh oh... some other exception happened
	                                 throw new QueryProcessingException(ex.getMessage(), ex);
	                         }
	                 }  finally {
	                     HibernateUtil.closeSession();
	                 }
	                 HibernateUtil.closeSession();
	                 cache.put(className, flag);
	         }
	         return flag.booleanValue();
	 }
}
