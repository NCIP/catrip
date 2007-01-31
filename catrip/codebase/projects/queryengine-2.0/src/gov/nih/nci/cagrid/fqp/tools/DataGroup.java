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