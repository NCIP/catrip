package gov.nih.nci.cagrid.data.cql.tools;

import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.data.QueryProcessingException;
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
    /*
     private String hibernateCfgFile ="";
     private String dataBaseURL  ="";
     private String schemaOrUser  ="";

     public ResultObjectAssemblerNlevels(String hibernateCfgFile,String dataBaseURL,String schemaOrUser) {
         this.hibernateCfgFile=hibernateCfgFile;
         this.dataBaseURL=dataBaseURL;
         this.schemaOrUser=schemaOrUser;
         
     }
     */    
    public AbstractResultObjectAssembler(Session session,String dialect) {
        this.session=session;
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

        //session.flush();
        List resultObjects = session.createQuery(hqlCriteria.getHqlString()).list();
        
        return resultObjects;
    }
}
