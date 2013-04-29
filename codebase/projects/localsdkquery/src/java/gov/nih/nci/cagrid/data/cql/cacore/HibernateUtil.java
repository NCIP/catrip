/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.data.cql.cacore;



import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static final ThreadLocal session = new ThreadLocal();
    public static Hashtable sessionFactoryMap = new Hashtable();

    
    public static SessionFactory getSessionFactory(String hibernateConfig,String dataBaseURL, String schemaOrUser) {
        SessionFactory sessionFactory;
        try {
            
            //Configuration configuration = new Configuration().configure(hibernateConfig);
            
            //String dataBaseURL = configuration.getProperty("hibernate.connection.url");
            //String schema = configuration.getProperty("hibernate.connection.username");
            String sessionFactoryId = dataBaseURL+"_"+schemaOrUser;
            
            
            if (sessionFactoryMap.get(sessionFactoryId) == null ){
                System.out.println("Building  New SessionFactory ..." + sessionFactoryId);
                sessionFactory = new Configuration().configure(hibernateConfig).buildSessionFactory();
                sessionFactoryMap.put(sessionFactoryId,sessionFactory);
            } else {
                System.out.println("Getting  Existing SessionFactory ..." + sessionFactoryId);
                sessionFactory = (SessionFactory)sessionFactoryMap.get(sessionFactoryId);
            }
            
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
    
    public static Session currentSession(String hibernateConfig,String dataBaseURL, String schemaOrUser) throws HibernateException {
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = getSessionFactory(hibernateConfig,dataBaseURL,schemaOrUser).openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null) {
            s.close();
            System.out.println("Closing session");
        }
    }
}
