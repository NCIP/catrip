/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.catrip.cagrid.catripquery.service;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = sessionFactory.openSession();
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
	public static void create(Object obj) throws HibernateException{
		Transaction tx = null;
		Session session = HibernateUtil.currentSession();
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(obj);
			tx.commit();
			session.flush();
			HibernateUtil.closeSession();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			throw e;
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
	

	public static void delete(Object obj) {
		Transaction tx = null;
		Session session = HibernateUtil.currentSession();
		try {
			tx = session.beginTransaction();
			System.out.println("\n****** Deleting " + obj.getClass().getName() + " ******\n");
			session.delete(obj);
			tx.commit();
			HibernateUtil.closeSession();
			System.out.println("\n****** Done Deleting " + obj.getClass().getName() + " ******\n");
		} catch (HibernateException e) {
			e.printStackTrace();
			session.flush();
			HibernateUtil.closeSession();
			if (tx != null)
				tx.rollback();
			//assertTrue(false);
		}
		finally{
			HibernateUtil.closeSession();
		}
	}
}


