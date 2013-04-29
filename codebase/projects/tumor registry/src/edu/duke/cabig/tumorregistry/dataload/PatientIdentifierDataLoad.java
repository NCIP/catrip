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


public class PatientIdentifierDataLoad {
	private static PatientIdentifierDataLoad instance = null;
	private static String patientIdFile = DataLoadUtils.getProperty("patient_identifier_data");
	private Set<PatientIdentifier> patientIdentifiers = new HashSet<PatientIdentifier>();
	
	static PatientIdentifierDataLoad getInstance(){
		if (instance == null)
			instance = new PatientIdentifierDataLoad();
		return instance;
	}

	private PatientIdentifierDataLoad(){
		patientIdentifiers = getPatientIdentifiers();
	}
	
	public PatientIdentifier getPatientIdentifier(Long id){
		for (Iterator iter = patientIdentifiers.iterator(); iter.hasNext();) {
			PatientIdentifier element = (PatientIdentifier) iter.next();
			if (element.getId().equals(id))
				return element;
		}
		// if the mrn is missing create one
		PatientIdentifier p = new PatientIdentifier();
		p.setId(id);
		Long mrn = id*1000;
		p.setMedicalRecordNumber(mrn.toString());
		return p;
	}
	
	private Set<PatientIdentifier> getPatientIdentifiers() {
		String[] column = new String[5];
		int rowNumber;
		try {
			InputStream input = new FileInputStream(patientIdFile);
			POIFSFileSystem fs = new POIFSFileSystem( input );
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator(); 
			while( rows.hasNext() ) {        
				HSSFRow row = (HSSFRow) rows.next();
				rowNumber = row.getRowNum();
				PatientIdentifier p = new PatientIdentifier();

				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)){
							setValue(column[cell.getCellNum()], p,cell.getDateCellValue());

						}
						else{
							setValue(column[cell.getCellNum()], p, cell.getNumericCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0){
							column[cell.getCellNum()] = cell.getStringCellValue();
						}
						else{
							setValue(column[cell.getCellNum()], p, cell.getStringCellValue());
						}
						break;
					}
				}
				if (p.getId() != null)
					patientIdentifiers.add(p);
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return patientIdentifiers;
	}
	

	private void setValue(String column, PatientIdentifier o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("Accn_No")){
			if (value != null)
				o.setId(DataLoadUtils.formatLong(value));
		}

		if (column.equalsIgnoreCase("MedicalRecordNumber")){
			o.setMedicalRecordNumber(DataLoadUtils.format(value));
		}
		//if (column.equalsIgnoreCase("FakeMRNFromWilma")){
		//	o.setLastName(DataLoadUtils.format(value));
		//}
	}
}

