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

public class FollowupDataLoad {
	private static String spreadsheetData = DataLoadUtils.getProperty("followup_data");
	private static Lookup lookup = Lookup.getInstance();
	private Set<Followup> followups = new HashSet<Followup>();
	
	public FollowupDataLoad(){
		getFollowups();
	}

	public Set getFollowupCollection(Long sequenceId, Long patientId){
		Set<Followup> followupCollection = new HashSet<Followup>();
		for (Iterator iter = followups.iterator(); iter.hasNext();) {
			Followup followup = (Followup) iter.next();
			if (followup.getAccnNo().equals(sequenceId) && followup.getSeqNo().equals(patientId)){
				followupCollection.add(followup);
			}
		}
		return followupCollection;
	}

	public Set getFollowups() {
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
				Followup aFollowup = new Followup();

				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)){
							setValue(column[cell.getCellNum()], aFollowup,cell.getDateCellValue());
						}
						else{
							setValue(column[cell.getCellNum()], aFollowup, cell.getNumericCellValue());

						}
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0){
							column[cell.getCellNum()] = cell.getStringCellValue();
						}
						else{
							setValue(column[cell.getCellNum()], aFollowup, cell.getStringCellValue());
						}
						break;
					}
				}
				if (aFollowup.getAccnNo() != null || aFollowup.getSeqNo() != null){
					aFollowup.setRecurrence(RecurrenceDataLoad.getInstance().getRecurrence(aFollowup.getSeqNo(), aFollowup.getId()));
					followups.add(aFollowup);
				}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return followups;
	}
	
	private void setValue(String column, Followup o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("FlID")){
			if (value != null)
				o.setId(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("accn_no")){
			if (value != null)
				o.setAccnNo(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("seq_no")){
			if (value != null)
				o.setSeqNo(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("CancerStatusCode")){
			if (value != null)
				o.setCancerStatus(lookup.getValue(DataLoadUtils.format(value), "CANCER STATUS"));
		}
		if (column.equalsIgnoreCase("date")){
			if (!value.toString().equalsIgnoreCase("00/00/0000"))
				o.setDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("ContactMethodCode")){
			if (value != null)
				o.setContactMethod(lookup.getValue(DataLoadUtils.format(value), "FOLLOW-UP SOURCE"));
		}

	}
}

