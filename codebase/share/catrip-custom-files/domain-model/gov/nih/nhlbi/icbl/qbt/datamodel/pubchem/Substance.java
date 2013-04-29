/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nhlbi.icbl.qbt.datamodel.pubchem;

import java.util.List;
import java.util.Set;


/**
 * @version 1.0
 * @created 26-Mar-2007 17:56:35
 */
public class Substance {
    private Long id;
    private Long idVersion;
    private Set substanceSynonymCollection;
   
    
    public Substance(){
        
    }
    
    public Long getIdVersion() {
        return idVersion;
    }
    
    
    public Long getId() {
        return id;
    }
    
    public void setIDVersion(Long idVersion) {
        this.setIdVersion(idVersion);
    }
    
    
    public void finalize() throws Throwable {
        
    }

    public Set getSubstanceSynonymCollection() {
        return substanceSynonymCollection;
    }

    public void setSubstanceSynonymCollection(Set substanceSynonymCollection) {
        this.substanceSynonymCollection = substanceSynonymCollection;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdVersion(Long idVersion) {
        this.idVersion = idVersion;
    }
   
}