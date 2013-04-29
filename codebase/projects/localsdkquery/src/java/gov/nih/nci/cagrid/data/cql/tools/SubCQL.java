/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.data.cql.tools;

import java.util.ArrayList;
import java.util.List;

public class SubCQL {
    public SubCQL() {
    }
    public static final String TARGET = "target";
    
    private String parentAssocationClassName = "";
    private String parentRoleName = "";
    private String CQLString;
    private List parents = new ArrayList();

    public void setCQLString(String cQLString) {
        this.CQLString = cQLString;
    }

    public String getCQLString() {
        return CQLString;
    }


    public void setParentAssocationClassName(String parentAssocationClassName) {
        this.parentAssocationClassName = parentAssocationClassName;
    }

    public String getParentAssocationClassName() {
        return parentAssocationClassName;
    }

    public void setParentRoleName(String parentRoleName) {
        this.parentRoleName = parentRoleName;
    }

    public String getParentRoleName() {
        return parentRoleName;
    }


    public void setParents(List parents) {
        this.parents = parents;
    }

    public List getParents() {
        return parents;
    }
}
