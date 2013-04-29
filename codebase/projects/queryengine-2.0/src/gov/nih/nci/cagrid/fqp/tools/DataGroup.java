/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package gov.nih.nci.cagrid.fqp.tools;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataGroup {
    
    private List dataRows = new ArrayList();
    
    public DataGroup() {
    }

    public void addDataRow(Map dataRow) {
        this.dataRows.add(dataRow);
    }

    public List getDataRows() {
        return dataRows;
    }
}