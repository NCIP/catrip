/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.tumorregistry.dataload;

import java.io.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

import edu.duke.cabig.tumorregistry.domain.*;

public class ActivitySummaryDataLoad {
	private static ActivitySummaryDataLoad instance = null;
	private static String data = DataLoadUtils.getProperty("activity_summary_data");
	//private static Lookup lookup = Lookup.getInstance();
	private Set<ActivitySummary> activitySummaries = new HashSet<ActivitySummary>();
	
	static ActivitySummaryDataLoad getInstance(){
		if (instance == null)
			instance = new ActivitySummaryDataLoad();
		return instance;
	}

	private ActivitySummaryDataLoad(){
		activitySummaries = buildActivitySummaries();
	}
	 

	public ActivitySummaryData getActivitySummary(Long sequenceId, Long patientId){
		for (Iterator iter = activitySummaries.iterator(); iter.hasNext();) {
			ActivitySummaryData aTreatment = (ActivitySummaryData) iter.next();
			if (aTreatment.getId().equals(patientId) && aTreatment.getSequenceNumber().equals(sequenceId)){
				return aTreatment;
			}
		}
		return null;
	}
	
	private Set<ActivitySummary> buildActivitySummaries() {
		String[] column = new String[100];
		int rowNumber;
		try {
			InputStream input = new FileInputStream(data);
			POIFSFileSystem fs = new POIFSFileSystem( input );
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator(); 
			while( rows.hasNext() ) {        
				HSSFRow row = (HSSFRow) rows.next();
				rowNumber = row.getRowNum();
				ActivitySummaryData aSummaryTreatment = new ActivitySummaryData();

				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell))
							setValue(column[cell.getCellNum()], aSummaryTreatment,cell.getDateCellValue());
						else
							setValue(column[cell.getCellNum()], aSummaryTreatment, cell.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0)
							column[cell.getCellNum()] = cell.getStringCellValue();
						else
							setValue(column[cell.getCellNum()], aSummaryTreatment, cell.getStringCellValue());
						break;
					
					}
				}
			if (aSummaryTreatment.getId() != null){
				activitySummaries.add(aSummaryTreatment);
			}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return activitySummaries;
	}
	private String format(Object value){
		Number n;
		if (value != null){
			n = ((Number) value).intValue();
			value = Long.valueOf(n.toString());
		}
		return value.toString();
		
	}
	public void setValue(String column, ActivitySummaryData o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("seq_No")){
			if (value != null){
				o.setId(Long.valueOf(format(value)));
			}
		}
		if (column.equalsIgnoreCase("accn_No")){
			if (value != null){
				o.setSequenceNumber(Long.valueOf(format(value)));
			}
		}
		if (column.equalsIgnoreCase("LocalDate")){
			if (!value.toString().equalsIgnoreCase("0000/00/00"))
				o.setLocalDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("SummaryDate")){
			if (!value.toString().equalsIgnoreCase("0000/00/00"))
				o.setSummaryDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("LocalCharacterizatio")){
				o.setLocalCharacterization(DataLoadUtils.format(value));
				if (o.getType() != null){
					o.setLocalCharacterization(Lookup.getInstance().getSummaryCharacterization(o.getLocalCharacterization(), o.getType()));
				}
		}
		if (column.equalsIgnoreCase("SummaryCharacterizat")){
				o.setSummaryCharacterization(DataLoadUtils.format(value));
				if (o.getType() != null){
					o.setSummaryCharacterization(Lookup.getInstance().getSummaryCharacterization(o.getSummaryCharacterization(), o.getType()));
				}
		}
		if (column.equalsIgnoreCase("TxType")){
			o.setType(DataLoadUtils.format(value));
			if (o.getLocalCharacterization() != null){
				o.setLocalCharacterization(Lookup.getInstance().getSummaryCharacterization(o.getLocalCharacterization(), o.getType()));
			}
			if (o.getSummaryCharacterization() != null){
				o.setSummaryCharacterization(Lookup.getInstance().getSummaryCharacterization(o.getSummaryCharacterization(), o.getType()));
			}
		}
	}
}

