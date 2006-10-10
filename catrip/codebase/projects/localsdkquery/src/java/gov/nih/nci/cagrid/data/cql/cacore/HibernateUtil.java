package gov.nih.nci.cagrid.data.cql.cacore;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    public static final ThreadLocal session = new ThreadLocal();
    public static Map sessionFactoryMap = new HashMap();
    
    public static SessionFactory getSessionFactory(String hibernateConfig) {
        SessionFactory sessionFactory;
        try {
            
            Configuration configuration = new Configuration().configure(hibernateConfig);
            
            String dataBaseURL = configuration.getProperty("hibernate.connection.url");
            String schema = configuration.getProperty("hibernate.connection.username");
            String sessionFactoryId = dataBaseURL+"_"+schema;
            
            if (sessionFactoryMap.get(sessionFactoryId) == null ){
                System.out.println("Building  SessionFactory ..." + sessionFactoryId);
                sessionFactory = configuration.buildSessionFactory();
                sessionFactoryMap.put(sessionFactoryId,sessionFactory);
            } else {
                System.out.println("Getting  SessionFactory ..." + sessionFactoryId);
                sessionFactory = (SessionFactory)sessionFactoryMap.get(sessionFactoryId);
            }
            
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
    
    public static Session currentSession(String hibernateConfig) throws HibernateException {
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = getSessionFactory(hibernateConfig).openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null)
            s.close();
    }
}