/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.upmc.opi.caBIG.caTIES.database.domain;

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
			session.save(obj);
			tx.commit();
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			throw e;
		}
	}
	
/*	public static List selectPatientIds() throws HibernateException{
		Transaction tx = null;
		List<Patient> result = new ArrayList<Patient>();
		List<Long> ids = new ArrayList<Long>();
		Session session = HibernateUtil.currentSession();
		Patient aPatient = null;
		try {
			//tx = session.beginTransaction();
			result = session.createQuery("from Patient").list();
			//tx.commit();
			HibernateUtil.closeSession();
			
			for (int i = 0; i < result.size(); i++) {
				ids.add(result.get(i).getId());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return ids;
	}
*/
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
	}
}


