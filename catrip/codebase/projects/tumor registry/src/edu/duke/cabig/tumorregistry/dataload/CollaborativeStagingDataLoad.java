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

public class CollaborativeStagingDataLoad {
	private static CollaborativeStagingDataLoad instance = null;
	private static String data = DataLoadUtils.getProperty("collaborative_staging_data");
	private Set<CollaborativeStaging> collaboratives = new HashSet<CollaborativeStaging>();
	
	static CollaborativeStagingDataLoad getInstance(){
		if (instance == null)
			instance = new CollaborativeStagingDataLoad();
		return instance;
	}

	private CollaborativeStagingDataLoad(){
		collaboratives = buildCollaborativeStaging();
	}
	
	public CollaborativeStagingData getCollaborativeStaging(Long sequenceId, Long patientId){
		for (Iterator iter = collaboratives.iterator(); iter.hasNext();) {
			CollaborativeStagingData aCollaborativeStaging = (CollaborativeStagingData) iter.next();
			if (aCollaborativeStaging.getId().equals(patientId) && aCollaborativeStaging.getSequenceNumber().equals(sequenceId)){
				//System.out.println("CollaborativeStaging " + aCollaborativeStaging.getId()+" = " + (patientId) +" and " + aCollaborativeStaging.getSequenceNumber()+" = " + (sequenceId));
				return aCollaborativeStaging;
			}
		}
		return null;
	}
	
	private Set<CollaborativeStaging> buildCollaborativeStaging() {
		String[] column = new String[100];
		int rowNumber;
		try {
			InputStream input = new FileInputStream(data);
			if (input == null)
				System.out.println(input);
			POIFSFileSystem fs = new POIFSFileSystem( input );
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator(); 
			while( rows.hasNext() ) {        
				HSSFRow row = (HSSFRow) rows.next();
				rowNumber = row.getRowNum();
				CollaborativeStagingData collaborativeStaging = new CollaborativeStagingData();

				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell))
							setValue(column[cell.getCellNum()], collaborativeStaging,cell.getDateCellValue());
						else
							setValue(column[cell.getCellNum()], collaborativeStaging, cell.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0)
							column[cell.getCellNum()] = cell.getStringCellValue();
						else
							setValue(column[cell.getCellNum()], collaborativeStaging, cell.getStringCellValue());
						break;
					default:
						//System.out.println( cell.getCellType() );
					break;
					}
				}
			if (collaborativeStaging.getId() != null){
				collaboratives.add(collaborativeStaging);
			}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
		return collaboratives;
	}

	public void setValue(String column, CollaborativeStagingData o, Object value){
		if (column == null)
			return;
		if (column.equalsIgnoreCase("seq_No")){
			if (value != null){
				o.setId(Long.valueOf(DataLoadUtils.format(value)));
			}
		}
		if (column.equalsIgnoreCase("accn_No")){
			if (value != null){
				o.setSequenceNumber(Long.valueOf(DataLoadUtils.format(value)));
			}
		}
		if (column.equalsIgnoreCase("DerivedAJCCStage")){
			o.setDerivedAJCCStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("DerivedMetDescriptor")){
			o.setDerivedMetDescriptor(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("DerivedMetStage")){
			o.setDerivedMetStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("DerivedNodeDescripto")){
			o.setDerivedNodeDescriptor(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("DerivedNodeStage")){
			o.setDerivedNodeStage(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("DerivedSEERSummary19")){
			o.setDerivedSEERSummary1977(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("DerivedSEERSummary20")){
			o.setDerivedSEERSummary2000(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("DerivedTumorDescript")){
			o.setDerivedTumorDescriptor(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("DerivedTumorStage")){
			o.setDerivedTumorStage(DataLoadUtils.format(value));
		}

		if (column.equalsIgnoreCase("Extension")){
			o.setExtension(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("LymphNodes")){
			o.setLymphNodes(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("MetAtDiagnosis")){
			o.setMetAtDiagnosis(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("MetEvaluation")){
			o.setMetEvaluation(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("SiteSpecificFactor1")){
			o.setSiteSpecificFactor1(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("SiteSpecificFactor2")){
			o.setSiteSpecificFactor2(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("SiteSpecificFactor3")){
			o.setSiteSpecificFactor3(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("SiteSpecificFactor4")){
			o.setSiteSpecificFactor4(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("SiteSpecificFactor5")){
			o.setSiteSpecificFactor5(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("SiteSpecificFactor6")){
			o.setSiteSpecificFactor6(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("TumorSizeExtEvaluati")){
			o.setTumorSizeExtEvaluation(DataLoadUtils.format(value));
		}
		
	}
}

