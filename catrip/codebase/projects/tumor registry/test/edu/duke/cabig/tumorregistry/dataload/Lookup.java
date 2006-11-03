package edu.duke.cabig.tumorregistry.dataload;

import java.io.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

import edu.duke.cabig.tumorregistry.domain.Data;
import edu.duke.cabig.tumorregistry.domain.DataLoadUtils;

 

public class Lookup {
	private static Lookup instance = null;
	private static String lookupData = DataLoadUtils.getProperty("lookup_data");
	private static String characterData = DataLoadUtils.getProperty("character_data");
	private static String countryData = DataLoadUtils.getProperty("country_data");
	private static String countyData = DataLoadUtils.getProperty("county_data");
	private static String nonSurgeryData = DataLoadUtils.getProperty("non_surgery_data");
	private static String surgeryData = DataLoadUtils.getProperty("surgery_data");
	private static Set<Data> lookup = new HashSet<Data>();
	private static Set<Data> characterization = new HashSet<Data>();
	private static Set<Data> county = new HashSet<Data>();
	private static Set<Data> country = new HashSet<Data>();
	private static Set<Data> nonSurgery = new HashSet<Data>();
	private static Set<Data> surgery = new HashSet<Data>();
	
	public String getNonSurgeryData(String codeValue, String tableName){
		for (Iterator iter = nonSurgery.iterator(); iter.hasNext();) {
			Data element = (Data) iter.next();
			if (element.getTableName() != null && element.getValue() != null && element.getTableName().equalsIgnoreCase(tableName) &&
					element.getValue().equalsIgnoreCase(codeValue))
				return element.getDescription();
		}
		return "";
	}

	public String getSurgeryData(String codeValue, String txCodeGroup){
		for (Iterator iter = surgery.iterator(); iter.hasNext();) {
			Data element = (Data) iter.next();
			if (element.getTxCodeGroup() != null && element.getValue() != null && element.getTxCodeGroup().equalsIgnoreCase(txCodeGroup) &&
					element.getValue().equalsIgnoreCase(codeValue))
				return element.getDescription();
		}
		return "";
	}

	public String getValue(String codeValue, String tableName){
		for (Iterator iter = lookup.iterator(); iter.hasNext();) {
			Data element = (Data) iter.next();
			if (element.getTableName() != null && element.getValue() != null && element.getTableName().equalsIgnoreCase(tableName) &&
					element.getValue().equalsIgnoreCase(codeValue))
				return element.getDescription();
		}
		return "";
	}

	public String getCharacterization(String codeValue, String txType){
		for (Iterator iter = characterization.iterator(); iter.hasNext();) {
			Data element = (Data) iter.next();
			if (element.getTableName() != null && element.getValue() != null && element.getTableName().equalsIgnoreCase(txType) &&
					element.getValue().equalsIgnoreCase(codeValue))
				return element.getDescription();
		}
		return "";
	}
	
	public String getSummaryCharacterization(String codeValue, String txType){
		for (Iterator iter = characterization.iterator(); iter.hasNext();) {
			Data element = (Data) iter.next();
			if (element.getTableName() != null && element.getValue() != null && element.getTxType().equalsIgnoreCase(txType) &&
					element.getValue().equalsIgnoreCase(codeValue))
				return element.getDescription();
		}
		return "";
	}
	public String getCountyValue(String theCounty, String theState){
		for (Iterator iter = county.iterator(); iter.hasNext();) {
			Data element = (Data) iter.next();
			if (element.getState() != null && element.getCounty() != null && element.getState().equalsIgnoreCase(theState) &&
					element.getCounty().equalsIgnoreCase(theCounty)){
				return element.getDescription();
			}
		}
		return "";
	}

	public String getCountryValue(String geoCode){
		for (Iterator iter = country.iterator(); iter.hasNext();) {
			Data element = (Data) iter.next();
			if (element.getValue() != null  &&
					element.getValue().equalsIgnoreCase(geoCode)){
				return element.getDescription();
			}
		}
		return "";
	}

	public static Lookup getInstance(){
		if (instance == null){
			instance = new Lookup();
			loadData(lookupData,lookup);
			loadData(characterData, characterization);
			loadData(countryData, country);
			loadData(countyData, county);
			loadData(surgeryData, surgery);
			loadData(nonSurgeryData, nonSurgery);
		}
		return instance;
	}
	
    private static void loadData(String dataFile, Set<Data> set) {
    	String[] column = new String[25];
    	int rowNumber;

        try {
        	InputStream input = new FileInputStream(dataFile);
            POIFSFileSystem fs = new POIFSFileSystem( input );
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            // Iterate over each row in the sheet
            Iterator rows = sheet.rowIterator(); 
            while( rows.hasNext() ) {        
                HSSFRow row = (HSSFRow) rows.next();
                rowNumber = row.getRowNum();
                Data data = new Data();

                // Iterate over each cell in the row and print out the cell's content
                Iterator cells = row.cellIterator();
               
                while( cells.hasNext() ) {
                    HSSFCell cell = (HSSFCell) cells.next();
                  	if (rowNumber == 0){
                		column[cell.getCellNum()] = cell.getStringCellValue();
                	}
                  	else{
                  	
                    try {
		                   setValue(data, column[cell.getCellNum()], cell.getStringCellValue());
					} catch (RuntimeException e) {
		                   setValue(data, column[cell.getCellNum()], cell.getNumericCellValue());
					}
                 }
                }
                set.add(data);
                	
             }
            
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }

    }
 
    private static void setValue(Data data, String column, Object value){
    	if (column.equalsIgnoreCase("CodeTable_Number")){
    		if (value != null){
    			data.setCodeTableId(DataLoadUtils.format(value));
    		}
    	}
    	if (column.equalsIgnoreCase("Code_Val")){
    		data.setValue(DataLoadUtils.format(value));
    	}
    	if (column.equalsIgnoreCase("Code_Description")){
    		data.setDescription(DataLoadUtils.format(value));
    	}
    	if (column.equalsIgnoreCase("TxType")){
    		data.setTxType(DataLoadUtils.format(value));
    	}
    	if (column.equalsIgnoreCase("geo_code")){
    		data.setValue(DataLoadUtils.format(value));
    	}
    	if (column.equalsIgnoreCase("Geo_TopDescription")){
    		data.setDescription(DataLoadUtils.format(value));
    	}
    	if (column.equalsIgnoreCase("Count")){
    		data.setCount(DataLoadUtils.format(value));
    	}
    	if (column.equalsIgnoreCase("Fp_State")){
    		data.setState(DataLoadUtils.format(value));
    	}
    	if (column.equalsIgnoreCase("Fp_County")){
    		data.setCounty(DataLoadUtils.format(value));
    	}
      
      	if (column.equalsIgnoreCase("Fp_Dscrp")){
       		data.setDescription(DataLoadUtils.format(value));
       	}

      	if (column.equalsIgnoreCase("CodeTable_Desc")){
       		data.setTableName(DataLoadUtils.format(value));
       	}
      	if (column.equalsIgnoreCase("Code_Description") || column.equalsIgnoreCase("Characterization")){
       		data.setDescription(DataLoadUtils.format(value));
       	}
      	if (column.equalsIgnoreCase("TxType")){
       		data.setTxType(DataLoadUtils.format(value));
       	}

      	if (column.equalsIgnoreCase("Code_Grp")){
       		data.setTxCodeGroup(DataLoadUtils.format(value));
       	}
     
    }

}
   

    
