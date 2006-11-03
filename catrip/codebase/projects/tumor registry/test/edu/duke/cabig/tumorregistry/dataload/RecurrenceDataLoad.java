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


public class RecurrenceDataLoad {
	private static RecurrenceDataLoad instance = null;
	private static String spreadsheetData = DataLoadUtils.getProperty("recurrence_data");
	private static Lookup lookup = Lookup.getInstance();
	private Set<Recurrence> recurrences = new HashSet<Recurrence>();
	
	static RecurrenceDataLoad getInstance(){
		if (instance == null)
			instance = new RecurrenceDataLoad();
		return instance;
	}

	private RecurrenceDataLoad(){
		recurrences = getRecurrences();
	}
	
	public Recurrence getRecurrence(Long sequenceId, Long patientId){
		for (Iterator iter = recurrences.iterator(); iter.hasNext();) {
			RecurrenceData aRecurrence = (RecurrenceData) iter.next();
			if (aRecurrence.getId().equals(patientId) && aRecurrence.getSequenceNumber().equals(sequenceId)){
				return aRecurrence;
			}
		}
		return null;
	}

	private Set<Recurrence> getRecurrences() {
		String[] column = new String[100];
		int rowNumber;
		try {
			InputStream input = new FileInputStream(spreadsheetData);
			POIFSFileSystem fs = new POIFSFileSystem( input );
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator(); 
			while( rows.hasNext() ) {        
				HSSFRow row = (HSSFRow) rows.next();
				rowNumber = row.getRowNum();
				RecurrenceData aRecurrence = new RecurrenceData();

				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)){
							setValue(column[cell.getCellNum()], aRecurrence,cell.getDateCellValue());
						}
						else{
							setValue(column[cell.getCellNum()], aRecurrence, cell.getNumericCellValue());

						}
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0){
							column[cell.getCellNum()] = cell.getStringCellValue();
						}
						else{
							setValue(column[cell.getCellNum()], aRecurrence, cell.getStringCellValue());
						}
						break;
					}
				}
				if (aRecurrence.getId() != null){
					aRecurrence.setDistantSiteCollection(createDistantSite(aRecurrence));
					recurrences.add(aRecurrence);
				}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return recurrences;
	}
	
	private Set<DistantSite> createDistantSite(RecurrenceData obj){
		DistantSite d = null;
		Set<DistantSite> distantSiteCollection = new HashSet<DistantSite>();
		for (int i = 1; i < 4; i++) {
			d = obj.createDistantSite(i);
			if (d != null)
				distantSiteCollection.add(d);
		}
		return distantSiteCollection;
	}
	
	private void setValue(String column, RecurrenceData o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("FlID")){
			if (value != null)
				o.setId(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("accn_no")){
			if (value != null)
				o.setAccountNumber(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("seq_no")){
			if (value != null)
				o.setSequenceNumber(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("date")){
			if (!value.toString().equalsIgnoreCase("00/00/0000"))
				o.setDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("TypeCode")){
			if (value != null)
				o.setType(lookup.getValue(DataLoadUtils.format(value), "TYPE RECUR"));
		}
		if (column.equalsIgnoreCase("DistantSite1NameCode")){
			if (value != null)
				o.setSiteCode1(lookup.getValue(DataLoadUtils.format(value), "DISTANT SITES"));
		}
		if (column.equalsIgnoreCase("DistantSite2NameCode")){
			if (value != null)
				o.setSiteCode2(lookup.getValue(DataLoadUtils.format(value), "DISTANT SITES"));
		}
		if (column.equalsIgnoreCase("DistantSite3NameCode")){
			if (value != null)
				o.setSiteCode3(lookup.getValue(DataLoadUtils.format(value), "DISTANT SITES"));
		}

	}
}

