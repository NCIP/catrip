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

class DiseaseExtentDataLoad {
	private static DiseaseExtentDataLoad instance = null;
	private static String data = DataLoadUtils.getProperty("disease_extent_data");
	private static Lookup lookup = Lookup.getInstance();
	private Set<DiseaseExtent> diseaseExtents = new HashSet<DiseaseExtent>();
	
	static DiseaseExtentDataLoad getInstance(){
		if (instance == null)
			instance = new DiseaseExtentDataLoad();
		return instance;
	}

	private DiseaseExtentDataLoad(){
		diseaseExtents = buildDiseaseExtents();
	}
	
	public Set<DiseaseExtent> getDiseaseExtents(Long sequenceId, Long patientId){
		Set<DiseaseExtent> diseaseCollection = new HashSet<DiseaseExtent>();
		for (Iterator iter = diseaseExtents.iterator(); iter.hasNext();) {
			DiseaseExtentData aDiseaseExtent = (DiseaseExtentData) iter.next();
			if (aDiseaseExtent.getAccountNumber().equals(sequenceId) && aDiseaseExtent.getSequenceNumber().equals(patientId)){
				diseaseCollection.add(aDiseaseExtent);
			}
		}
		return diseaseCollection;
	}
	
	private Set<DiseaseExtent> buildDiseaseExtents() {
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
				DiseaseExtentData aDiseaseExtent = new DiseaseExtentData();

				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell))
							setValue(column[cell.getCellNum()], aDiseaseExtent,cell.getDateCellValue());
						else
							setValue(column[cell.getCellNum()], aDiseaseExtent, cell.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0)
							column[cell.getCellNum()] = cell.getStringCellValue();
						else
							setValue(column[cell.getCellNum()], aDiseaseExtent, cell.getStringCellValue());
						break;
					}
				}
				if (aDiseaseExtent.getId() != null){
					aDiseaseExtent.setDistantSiteCollection(createDistantSite(aDiseaseExtent));
					diseaseExtents.add(aDiseaseExtent);
				}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return diseaseExtents;
	}

	private Set<DistantSite> createDistantSite(DiseaseExtentData obj){
		DistantSite d = null;
		Set<DistantSite> distantSiteCollection = new HashSet<DistantSite>();
		for (int i = 1; i < 4; i++) {
			d = obj.createDistantSite(i);
			if (d != null)
				distantSiteCollection.add(d);
		}
		return distantSiteCollection;
	}

	private String format(Object value){
		Number n;
		if (value != null){
			n = ((Number) value).intValue();
			value = Long.valueOf(n.toString());
		}
		return value.toString();
		
	}
	public void setValue(String column, DiseaseExtentData o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("uniq_no")){
			if (value != null)
				o.setId(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("Accn_No")){
			if (value != null)
				o.setAccountNumber(DataLoadUtils.formatLong(value));
		}

		if (column.equalsIgnoreCase("seq_no")){
			if (value != null)
				o.setSequenceNumber(DataLoadUtils.formatLong(value));
		}
		if (column.equalsIgnoreCase("BestAJCCStage")){
			o.setBestAJCCStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("BestSEERSummaryStage")){
			o.setBestSEERSummaryStage(format(value));
		}
		if (column.equalsIgnoreCase("ClinicalAJCCStage")){
			o.setClinicalAJCCStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("ClinicalAJCCStageDes")){
			o.setClinicalAJCCStageDescriptor(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("ClinicalMetStage")){
			o.setClinicalMetStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("ClinicalNodeStage")){
			o.setClinicalNodeStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("ClinicalTumorStage")){
			o.setClinicalTumorStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("PathologicAJCCStage")){
			o.setPathologicAJCCStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("PathologicAJCCStageD")){
			o.setPathologicAJCCStageDescriptor(DataLoadUtils.format(value));
		}

		if (column.equalsIgnoreCase("PathologicMetStage")){
			o.setPathologicMetStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("PathologicNodeStage")){
			o.setPathologicNodeStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("PathologicTumorStage")){
			o.setPathologicTumorStage(DataLoadUtils.format(value));
		}
		//Integer
		if (column.equalsIgnoreCase("RegionalNodesPositiv")){
			Integer i = new Integer(((Number) value).intValue());
			o.setRegionalNodesPositive(i);
		}
		//Integer
		if (column.equalsIgnoreCase("RegionalNodesExamine")){
			Integer i = new Integer(((Number) value).intValue());
			o.setRegionalNodesExamined(i);
		}
		if (column.equalsIgnoreCase("TNMEditionCode")){
			if (value != null)
				o.setTnmEdition(lookup.getValue(DataLoadUtils.format(value), "TNM EDITION"));
		}
			
		if (column.equalsIgnoreCase("TumorMarker1Code")){
			o.setTumorMarker1(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("TumorMarker2Code")){
			o.setTumorMarker2(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("TumorMarker3Code")){
			o.setTumorMarker3(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("TumorSize")){
			o.setTumorSize(new Float(value.toString()));
		}
		if (column.equalsIgnoreCase("DistantSite1NameCode")){
			if (value != null){
				o.setSiteCode1(lookup.getValue(DataLoadUtils.format(value), "DISTANT SITES"));
			}
		}
		if (column.equalsIgnoreCase("DistantSite2NameCode")){
			if (value != null){
				o.setSiteCode2(lookup.getValue(DataLoadUtils.format(value), "DISTANT SITES"));
			}
		}
		if (column.equalsIgnoreCase("DistantSite3NameCode")){
			if (value != null){
				o.setSiteCode3(lookup.getValue(DataLoadUtils.format(value), "DISTANT SITES"));
			}
		}
		
	}
}

