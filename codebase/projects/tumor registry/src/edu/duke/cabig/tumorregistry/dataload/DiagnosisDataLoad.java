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

public class DiagnosisDataLoad {
	private static String patient = DataLoadUtils.getProperty("diagnosis_data");
	private static Lookup lookup = Lookup.getInstance();
	private Set<Diagnosis> diagnosises = new HashSet<Diagnosis>();
	private FollowupDataLoad followupData = new FollowupDataLoad();
	private Set<Followup> followupCollection = new HashSet<Followup>();

	public Set getDiagnosisCollection(Long patientId){
		Set<Diagnosis> diagnosisCollection = new HashSet<Diagnosis>();
		for (Iterator iter = diagnosises.iterator(); iter.hasNext();) {
			DiagnosisData diagnosis = (DiagnosisData) iter.next();
			if (diagnosis.getSequenceNumber().equals(patientId)){
				diagnosisCollection.add(diagnosis);
			}
		}
		return diagnosisCollection;
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	public Set getDiagnosises() {
		String[] column = new String[100];
		int rowNumber;
		try {
			InputStream input = new FileInputStream(patient);
			POIFSFileSystem fs = new POIFSFileSystem( input );
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);

			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator(); 
			while( rows.hasNext() ) {        
				HSSFRow row = (HSSFRow) rows.next();
				rowNumber = row.getRowNum();
				DiagnosisData p = new DiagnosisData();

				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell))
							setValue(column[cell.getCellNum()], p,cell.getDateCellValue());
						else
							setValue(column[cell.getCellNum()], p, cell.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0)
							column[cell.getCellNum()] = cell.getStringCellValue();
						else
							setValue(column[cell.getCellNum()], p, cell.getStringCellValue());
						break;
					}
				}
				if (p.getId() != null){
					followupCollection = followupData.getFollowupCollection(p.getSequenceNumber(), p.getId());
					p.setFollowUpCollection(followupCollection);
					p.setAddress(AddressDataLoad.getInstance().getDiganosisAddress(p.getSequenceNumber(), p.getId()));
					p.setCollaborativeStaging(CollaborativeStagingDataLoad.getInstance().getCollaborativeStaging(p.getSequenceNumber(), p.getId()));
					p.setDiseaseExtentCollection(DiseaseExtentDataLoad.getInstance().getDiseaseExtents(p.getSequenceNumber(), p.getId()));
					Set set = ActivityDataLoad.getInstance().getActivities(p.getId(),p.getSequenceNumber());
					p.setActivityCollection(set);
					p.setFirstTreatment(FirstCouseTreatmentDataLoad.getInstance().getTreatmentSummary(p.getSequenceNumber(), p.getId()));
					p.setActivitySummary(ActivitySummaryDataLoad.getInstance().getActivitySummary(p.getSequenceNumber(), p.getId()));
					diagnosises.add(p);
				}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return diagnosises;
	}

	public void setValue(String column, DiagnosisData o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("Seq_No")){
			if (value != null){
				o.setId(DataLoadUtils.formatLong(value));
			}
		}
		// link to patient
		if (column.equalsIgnoreCase("Accn_No")){
			if (value != null){
				o.setSequenceNumber(DataLoadUtils.formatLong(value));
			}
		}
		if (column.equalsIgnoreCase("AgeAtDiagnosis")){
			if (value != null){
				o.setAgeAtDiagnosis(DataLoadUtils.formatInteger(value));
			}
		}

		if (column.equalsIgnoreCase("Behavior")){
			if (value != null){
				o.setBehavior(lookup.getValue(DataLoadUtils.format(value), "BEHAVIOR"));
			}
		}
		if (column.equalsIgnoreCase("CauseOfDeath")){
			o.setCauseOfDeath(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("ClassOfCaseCode")){
			if (value != null){
				o.setClassOfCase(lookup.getValue(DataLoadUtils.format(value), "CLASS"));
				o.setClassOfCaseCode(DataLoadUtils.formatInteger(value));
			}
		}

		if (column.equalsIgnoreCase("FirstContactDate")){
			if (!value.toString().equalsIgnoreCase("00/00/0000")){
				o.setFirstContactDate((java.util.Date)value);
			}
		}
		if (column.equalsIgnoreCase("HistologicGradeCode")){
			if (value != null){
				o.setHistologicGrade(lookup.getValue(DataLoadUtils.format(value), "GRADE"));
			}
		}
		if (column.equalsIgnoreCase("HistologyCode")){
			if (value != null){
				o.setHistology(lookup.getValue(DataLoadUtils.format(value), "ICD-O-3 MOR/HISTOLOGY"));
				o.setHistologyCode(DataLoadUtils.formatInteger(value));
			}
		}
		if (column.equalsIgnoreCase("InitialDiagnosisDate")){
			if (!value.toString().equalsIgnoreCase("00/00/0000"))
				o.setInitialDiagnosisDate((java.util.Date)value);
		}
		if (column.equalsIgnoreCase("LateralityCode")){
			if (value != null){
				o.setLaterality(lookup.getValue(DataLoadUtils.format(value), "LATERALITY"));
			}
		}

		if (column.equalsIgnoreCase("LateralityCode")){
			if (value != null){
				o.setLaterality(lookup.getValue(DataLoadUtils.format(value), "LATERALITY"));
			}
		}
		if (column.equalsIgnoreCase("PrimarySiteCode")){
			if (value != null){
				o.setPrimarySite(lookup.getValue(DataLoadUtils.format(value), "SITE CODES"));
				o.setPrimarySiteCode(DataLoadUtils.format(value));
			}
		}
	}
}

