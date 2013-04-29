/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.data.cql.tools;

public class Parent {

    private String parentAssocationClassName = "";
    private String parentAssocationRoleName = "";
    
    public Parent() {
    }

    public void setParentAssocationClassName(String parentAssocationClassName) {
        this.parentAssocationClassName = parentAssocationClassName;
    }

    public String getParentAssocationClassName() {
        return parentAssocationClassName;
    }

    public void setParentAssocationRoleName(String parentAssocationRoleName) {
        this.parentAssocationRoleName = parentAssocationRoleName;
    }

    public String getParentAssocationRoleName() {
        return parentAssocationRoleName;
    }
}
