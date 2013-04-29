/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

/*
 * MappingTester.java
 *
 * Created on March 27, 2007, 10:30 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package test;

import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author srini
 */
public class MappingTester {
    
    /** Creates a new instance of MappingTester */
    public MappingTester() {
    }
    
    public  static void main(String[] args ) {
        Session session = HibernateUtil.getSession();
        String qry =  "From gov.nih.nhlbi.icbl.qbt.datamodel.pubchem.Substance";
        List result = session.createQuery(qry).list();
        
           for (int i = 0; i<result.size(); i++) {
               gov.nih.nhlbi.icbl.qbt.datamodel.pubchem.Substance obj = (gov.nih.nhlbi.icbl.qbt.datamodel.pubchem.Substance) result.get(i);
               System.out.println("ID is " + obj.getId());
               System.out.println( "Version is " + obj.getIdVersion() );
               
               System.out.println(obj.getSubstanceSynonymCollection().size());
           }
        HibernateUtil.closeSession();
    }
    
}
