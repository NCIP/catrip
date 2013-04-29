/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.cabig.tumorregistry.dataload;

import edu.duke.cabig.tumorregistry.domain.*;

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

import edu.duke.cabig.tumorregistry.domain.DataLoadUtils;

public class ActivityDataLoad {
	private static ActivityDataLoad instance = null;
	private static String data = DataLoadUtils.getProperty("activity_data");
	private static Lookup lookup = Lookup.getInstance();
	private Set<Activity> activities = new HashSet<Activity>();

	static ActivityDataLoad getInstance(){
		if (instance == null)
			instance = new ActivityDataLoad();
		return instance;
	}

	private ActivityDataLoad(){
		activities = buildActivities();
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
				Activity anActivity = new Chemotherapy();
				Iterator theCells = row.cellIterator();

				while( theCells.hasNext() ) {
					HSSFCell cell = (HSSFCell) theCells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_STRING:
						if (rowNumber == 0)
							column[cell.getCellNum()] = cell.getStringCellValue();
						else{
							if (column[cell.getCellNum()].equalsIgnoreCase("TxType")){
								if (cell.getStringCellValue().equalsIgnoreCase("Hormone")){
									anActivity = new HormoneTherapy();
									anActivity.setClassType("Hormone");
								}
								if (cell.getStringCellValue().equalsIgnoreCase("Chemotherapy")){
									anActivity = new Chemotherapy();
									anActivity.setClassType("Chemotherapy");
								}
								if (cell.getStringCellValue().equalsIgnoreCase("HemTransplantEndocrine")){
									anActivity = new HemTransplantEndocrineProcedure();
									// lookup value : HTE TRANSPLANT/ENDOCRINE
									anActivity.setClassType("HTE TRANSPLANT/ENDOCRINE");
								}
								if (cell.getStringCellValue().equalsIgnoreCase("Immunotherapy")){
									anActivity = new Immunotherapy();
									anActivity.setClassType("Immunotherapy");
								}
								if (cell.getStringCellValue().equalsIgnoreCase("NCDS")){
									anActivity = new NonCancerDirectedSurgery();
									// BIOPSY
									anActivity.setClassType("BIOPSY");
								}
								if (cell.getStringCellValue().equalsIgnoreCase("OtherTherapy")){
									anActivity = new OtherTherapy();
									// OTHER
									anActivity.setClassType("OTHER");
								}
								if (cell.getStringCellValue().equalsIgnoreCase("PSS")){
									anActivity = new PrimarySiteSurgery();
									anActivity.setClassType("PSS");
								}
								if (cell.getStringCellValue().equalsIgnoreCase("Radiation")){
									anActivity = new Radiation();
									// characterization : RAD: REGIONAL MODALITY 
									// atLocalFacility : RAD: LOCATION OF RADIATION
									// boostModality : RAD: BOOST MODALITY
									// volume : RAD: VOLUME
									//anActivity.setClassType("")
								}
								if (cell.getStringCellValue().equalsIgnoreCase("RDS")){
									anActivity = new RegionalDistantSurgery();
									// SURG PROCEDURE/OTHER SITE
									anActivity.setClassType("SURG PROCEDURE/OTHER SITE");
								}
								if (cell.getStringCellValue().equalsIgnoreCase("RLNS")){
									anActivity = new RegionalLymphNodeSurgery();
									// SCOPE REG LN SURG
									anActivity.setClassType("SCOPE REG LN SURG");
								}
							}
							anActivity.setPerformedIndicator("N");
						}
						break;
					}
				}

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
		// add the first course radiation activities
		Set firstCourseActivities = RadiationFirstCouseDataLoad.getInstance().getFirstCourseActivities();
		for (Iterator iter = firstCourseActivities.iterator(); iter.hasNext();) {
			FirstCourseRadiation element = (FirstCourseRadiation) iter.next();
			if (element.getId() != null){
				activities.add(element);
			}

		}
		return activities;
	}
	
	private void setValue(String column, Activity o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("uniq_no")){
			if (value != null){
				o.setId(Long.valueOf(DataLoadUtils.formatLong(value)));
			}
		}
		if (column.equalsIgnoreCase("seq_no")){
			if (value != null){
				o.setAccountNumber(DataLoadUtils.formatLong(value));
			}
		}

		if (column.equalsIgnoreCase("Accn_No")){
			if (value != null){
				o.setSequenceNumber(DataLoadUtils.formatLong(value));
			}
		}
		if (column.equalsIgnoreCase("AtLocalFacility")){
			Boolean b;
			if (value.toString().equalsIgnoreCase("Y"))
				b = Boolean.valueOf("1");
			else
				b = Boolean.valueOf("0");
			o.setAtLocalFacility(b);
		}
		
		if (column.equalsIgnoreCase("CharacterizationCode")){
			o.setCharacterizationCode(DataLoadUtils.format(value));
			if (o.getClassType() != null){
				o.setCharacterization(lookup.getNonSurgeryData(DataLoadUtils.format(value), o.getClassType()));
			}
		}
		if (column.equalsIgnoreCase("Course")){
			o.setCourse(DataLoadUtils.formatInteger(value));
		}
		if (column.equalsIgnoreCase("Tx_Code_Grp")){
			o.setTxCodeGroup(DataLoadUtils.format(value));
		}
		if (o.getClassType() != null && o.getClassType().equalsIgnoreCase("PSS") && o.getCharacterizationCode() != null && o.getTxCodeGroup() != null)
			o.setCharacterization(lookup.getSurgeryData(o.getCharacterizationCode(), o.getTxCodeGroup()));
		if (column.equalsIgnoreCase("StartDate")){
			if (!value.toString().equalsIgnoreCase("00/00/0000")){
				o.setStartDate((java.util.Date)value);
			}
		}
	}
}

