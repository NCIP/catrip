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


public class PatientDataLoad {
	private static String patient = DataLoadUtils.getProperty("patient_data");
	private static Lookup lookup = Lookup.getInstance();
	private Set<Patient> patients = new HashSet<Patient>();

	public Patient getPatient(Long id){
		for (Iterator iter = patients.iterator(); iter.hasNext();) {
			Patient element = (Patient) iter.next();
			if (element.getId().equals(id))
				return element;
		}
		return null;
	}
	
	public Set getPatients() {
		String[] column = new String[25];
		int rowNumber;
		try {
			InputStream input = new FileInputStream(patient);
			POIFSFileSystem fs = new POIFSFileSystem( input );
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int i=0;
			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator(); 
			while( rows.hasNext() ) {        
				if (i++ > TumorDataLoadTest.PATIENT_LIMIT)
					break;
				HSSFRow row = (HSSFRow) rows.next();
				rowNumber = row.getRowNum();
				Patient p = new Patient();

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
				if (p.getId() != null){
					p.setPatientIdentifier(getPatientIdentifier(p.getId()));
					p.setAddress(AddressDataLoad.getInstance().getAddress(p.getId()));
					patients.add(p);
				}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return patients;
	}
	private PatientIdentifier getPatientIdentifier(Long id) {
		return PatientIdentifierDataLoad.getInstance().getPatientIdentifier(id);
	}

	private void setValue(String column, Patient o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("Accn_No")){
			if (value != null)
				o.setId(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("AutopsyCode")){
			if (value != null)
				o.setAutopsy(lookup.getValue(DataLoadUtils.format(value), "Autopsy"));
		}
		if (column.equalsIgnoreCase("DateOfBirth")){
			if (!value.toString().equalsIgnoreCase("00/00/0000"))
				o.setDateOfBirth((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("DateOfDeath")){
			if (!value.toString().equalsIgnoreCase("00/00/0000")){
				o.setDateOfDeath((java.util.Date)value);
			}
		}
		if (column.equalsIgnoreCase("EthnicGroupCode")){
			if (value != null)
				o.setEthnicGroup(lookup.getValue(DataLoadUtils.format(value), "ETHNICITY"));
		}

		if (column.equalsIgnoreCase("FirstName")){
			o.setFirstName(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("LastName")){
			o.setLastName(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("MiddleName")){
			o.setMiddleName(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("RaceCode")){
			if (value != null)
				o.setRace(lookup.getValue(DataLoadUtils.format(value), "Race"));
		}
		if (column.equalsIgnoreCase("SexCode")){
			if (value != null)
				o.setSex(lookup.getValue(DataLoadUtils.format(value), "Sex"));
		}

		if (column.equalsIgnoreCase("Suffix")){
			o.setSuffix(DataLoadUtils.format(value));
		}
	//	if (column.equalsIgnoreCase("mrn")){
	//		mrn = DataLoadUtils.format(value);
	//	}
	}
}

