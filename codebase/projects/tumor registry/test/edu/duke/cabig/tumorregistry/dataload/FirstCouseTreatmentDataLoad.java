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

public class FirstCouseTreatmentDataLoad {
	private static FirstCouseTreatmentDataLoad instance = null;
	private static String data = DataLoadUtils.getProperty("first_course_treatment_data");
	private Set<FirstCourseTreatmentSummary> treatmentSummaries = new HashSet<FirstCourseTreatmentSummary>();
	
	static FirstCouseTreatmentDataLoad getInstance(){
		if (instance == null)
			instance = new FirstCouseTreatmentDataLoad();
		return instance;
	}

	private FirstCouseTreatmentDataLoad(){
		treatmentSummaries = buildTreatments();
	}
	

	public FirstCourseTreatmentSummaryData getTreatmentSummary(Long sequenceId, Long patientId){
		for (Iterator iter = treatmentSummaries.iterator(); iter.hasNext();) {
			FirstCourseTreatmentSummaryData aTreatment = (FirstCourseTreatmentSummaryData) iter.next();
			if (aTreatment.getId().equals(patientId) && aTreatment.getSequenceNumber().equals(sequenceId)){
				return aTreatment;
			}
		}
		return null;
	}
	
	private Set<FirstCourseTreatmentSummary> buildTreatments() {
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
				FirstCourseTreatmentSummaryData aSummaryTreatment = new FirstCourseTreatmentSummaryData();

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
					default:
						//System.out.println( cell.getCellType() );
					break;
					}
				}
			if (aSummaryTreatment.getId() != null){
				treatmentSummaries.add(aSummaryTreatment);
			}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return treatmentSummaries;
	}

	private void setValue(String column, FirstCourseTreatmentSummaryData o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("seq_No")){
			if (value != null)
				o.setId(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("accn_No")){
			if (value != null)
				o.setSequenceNumber(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("FirstSurgeryDate")){
			if (!value.toString().equalsIgnoreCase("0000/00/00"))
				o.setFirstSurgeryDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("FirstSystemicTreatme")){
			if (!value.toString().equalsIgnoreCase("0000/00/00"))
				o.setFirstSystemicTreatmentDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("FirstTreatmentDate")){
			if (!value.toString().equalsIgnoreCase("0000/00/00"))
				o.setFirstTreatmentDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("MostDefinitiveSurger")){
			if (!value.toString().equalsIgnoreCase("0000/00/00"))
				o.setMostDefinitiveSurgeryDate((java.util.Date)value);
		}
	}
}

