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

public class RadiationFirstCouseDataLoad {
	private static RadiationFirstCouseDataLoad instance = null;
	private static String data = DataLoadUtils.getProperty("first_course_radiation_data");
	private Set<Activity> activities = new HashSet<Activity>();
	
	static RadiationFirstCouseDataLoad getInstance(){
		if (instance == null)
			instance = new RadiationFirstCouseDataLoad();
		return instance;
	}

	private RadiationFirstCouseDataLoad(){
		activities = buildActivities();
	}
	
	public Set getFirstCourseActivities(){
		return activities;
	}

	public Set<Activity> getActivities(Long sequenceId, Long patientId){
		Set<Activity> activityCollection = new HashSet<Activity>();
		for (Iterator iter = activities.iterator(); iter.hasNext();) {
			Activity anActivity = (Activity) iter.next();
			if (anActivity.getAccountNumber().equals(sequenceId) && anActivity.getSequenceNumber().equals(patientId)){
				activityCollection.add(anActivity);
			}
		}
		return activityCollection;
	}
	
	private Set<Activity> buildActivities() {
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
				FirstCourseRadiation anActivity = new FirstCourseRadiation();
				anActivity.setPerformedIndicator("N");
				
				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell))
							setValue(column[cell.getCellNum()], anActivity,cell.getDateCellValue());
						else
							setValue(column[cell.getCellNum()], anActivity, cell.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0)
							column[cell.getCellNum()] = cell.getStringCellValue();
						else
							setValue(column[cell.getCellNum()], anActivity, cell.getStringCellValue());
						break;
					}
				}
			
				if (anActivity.getId() != null){
					activities.add(anActivity);
				}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return activities;
	}
	
	private void setValue(String column, FirstCourseRadiation o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("uniq_no")){
			if (value != null)
				o.setId(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("seq_no")){
			if (value != null)
				o.setAccountNumber(DataLoadUtils.formatLong(value));
		}

		if (column.equalsIgnoreCase("Accn_No")){
			if (value != null)
				o.setSequenceNumber(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("CharacterizationCode")){
			o.setCharacterization(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("Course")){
			o.setCourse(DataLoadUtils.formatInteger(value));
		}
		if (column.equalsIgnoreCase("StartDate")){
			if (!value.toString().equalsIgnoreCase("9999/99/99") && (!value.toString().equalsIgnoreCase("8888/88/88")))
				o.setStartDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("StopDate")){
			if (!value.toString().equalsIgnoreCase("9999/99/99") && (!value.toString().equalsIgnoreCase("8888/88/88")))
				o.setStopDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("NumberOfTreatments")){
			o.setNumberOfTreatments(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("LocationCode")){
			o.setLocation(DataLoadUtils.format(value));
			Integer locationCode = DataLoadUtils.formatInteger(value);
			o.setLocation(Lookup.getInstance().getCharacterization(o.getLocation(), "RAD: LOCATION OF RADIATION"));

			if (locationCode.equals(1) || locationCode.equals(2) || locationCode.equals(3)) 
				o.setAtLocalFacility(Boolean.TRUE);
			else{ 
				if(locationCode.equals(4))
					o.setAtLocalFacility(Boolean.FALSE);
				else
					o.setLocation(null);
			}
		}
		
		if (column.equalsIgnoreCase("RegionalDose")){
			o.setRegionalDose(DataLoadUtils.formatInteger(value));
		}
		if (column.equalsIgnoreCase("VolumeCode")){
			o.setVolume(DataLoadUtils.format(value));
			o.setVolume(Lookup.getInstance().getCharacterization(o.getVolume(), "RAD: VOLUME"));
		}
		if (column.equalsIgnoreCase("BoostDose")){
			o.setBoostDose(DataLoadUtils.formatInteger(value));
		}
		if (column.equalsIgnoreCase("BoostModality")){
			o.setBoostModality(DataLoadUtils.format(value));
			o.setBoostModality(Lookup.getInstance().getCharacterization(o.getBoostModality(), "RAD: BOOST MODALITY"));
		}
		if (column.equalsIgnoreCase("RegionalModality")){
			
			o.setRegionalModality(DataLoadUtils.format(value));
			o.setRegionalModality(Lookup.getInstance().getCharacterization(o.getRegionalModality(), "RAD: REGIONAL MODALITY"));
			o.setCharacterization(o.getRegionalModality());
		}
	}
}

