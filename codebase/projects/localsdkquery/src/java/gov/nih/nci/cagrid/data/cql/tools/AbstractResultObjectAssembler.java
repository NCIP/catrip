/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.data.cql.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.data.QueryProcessingException;
import gov.nih.nci.cagrid.data.cql.cacore.HibernateUtil;
import gov.nih.nci.cagrid.data.cql.cacore.LocalCQL2HQL;
import gov.nih.nci.common.util.HQLCriteria;

import java.io.CharArrayReader;

import java.util.List;

import org.globus.wsrf.encoding.DeserializationException;
import org.globus.wsrf.encoding.ObjectDeserializer;

import org.hibernate.Session;

import org.xml.sax.InputSource;

public class AbstractResultObjectAssembler {
    private String dialect  ="";
    private Session session;
    private int qryCount = 0;
    private String hibernateCfgFile;
    private String dataBaseURL;
    private String schemaOrUser;
  
    public AbstractResultObjectAssembler(Session session,String dialect) {
        this.session=session;
        this.dialect=dialect;
    }
    public AbstractResultObjectAssembler(String hibernateCfgFile,String dataBaseURL,
                                            String schemaOrUser,String dialect) {
        this.dataBaseURL=dataBaseURL;
        this.schemaOrUser=schemaOrUser;
        this.hibernateCfgFile=hibernateCfgFile;
        this.dialect=dialect;
    }    
    public int getQryCount() {
        return qryCount;
    }

    public CQLQuery convertStringToCQL(String cqlString){


        StringBuffer buf = new StringBuffer(cqlString);

        char[] chars = new char[buf.length()];
        buf.getChars(0, chars.length, chars, 0);

        CharArrayReader car = new CharArrayReader(chars);
        InputSource source = new InputSource(car);
         java.lang.Object obj = null ;

        try {
            obj = ObjectDeserializer.deserialize(source,CQLQuery.class);
        } catch (DeserializationException e) {
            System.out.println("ERROR deserializing CQLQuery .. ");
            e.printStackTrace();
        }
        return ((CQLQuery)obj);
    }

    public List executeQry (String cqlStr){
        qryCount++;
        CQLQuery cqlQuery = convertStringToCQL(cqlStr);

        LocalCQL2HQL cql2hql = new LocalCQL2HQL(dialect);
        String hql="";
        try {
            hql = cql2hql.translate(cqlQuery, false);
        } catch (QueryProcessingException e) {
            e.printStackTrace();
        }
        HQLCriteria hqlCriteria = new HQLCriteria(hql);
        Session sessionx = HibernateUtil.currentSession(hibernateCfgFile,dataBaseURL,schemaOrUser);
        //session.flush();
        List resultObjects = sessionx.createQuery(hqlCriteria.getHqlString()).list();
        
        HibernateUtil.closeSession();
        return resultObjects;
    }
}
