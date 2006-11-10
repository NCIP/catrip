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

public class AddressDataLoad {
	private static String patientAddressData = DataLoadUtils.getProperty("patient_address_data");
	private static String diagnosisAddressData = DataLoadUtils.getProperty("diagnosis_address_data");
	private static Lookup lookup = Lookup.getInstance();
	private Set<Address> addresses = new HashSet<Address>();
	private Set<Address> diagnosisAddresses = new HashSet<Address>();
	private static AddressDataLoad instance = null;
	// used to make the diagnosis address ids unique.
	private final int MULTIPLIER = 11210;

	static AddressDataLoad getInstance(){
		if (instance == null)
			instance = new AddressDataLoad();
		return instance;
	}
	
	private AddressDataLoad(){
		buildAddresses();
	}
	
	public Address getAddress(Long patientId){
		for (Iterator iter = addresses.iterator(); iter.hasNext();) {
			Address address = (Address) iter.next();
			if (address.getId().equals(patientId)){
				return address;
			}
		}
		return null;
	}
	
	public Address getDiganosisAddress(Long sequenceId, Long patientId){
		for (Iterator iter = diagnosisAddresses.iterator(); iter.hasNext();) {
			AddressData address = (AddressData) iter.next();
			Long addressId = address.getId()/ MULTIPLIER;
			if (addressId.equals(patientId) && address.getSequenceNumber().equals(sequenceId)){
				return address;
			}
		}
		return null;
	}

	private void buildAddresses() {
		String[] column = new String[100];
		int rowNumber;
		String[] spreadsheets = new String[2];
		spreadsheets[0] = patientAddressData;
		spreadsheets[1] = diagnosisAddressData;
		for (int j = 0; j < spreadsheets.length; j++) {
			
		
		try {
			InputStream input = new FileInputStream(spreadsheets[j]);
			POIFSFileSystem fs = new POIFSFileSystem( input );
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			// Iterate over each row in the sheet
			Iterator rows = sheet.rowIterator(); 
			while( rows.hasNext() ) {        
				HSSFRow row = (HSSFRow) rows.next();
				rowNumber = row.getRowNum();
				AddressData aAddress = new AddressData();

				// Iterate over each cell in the row and print out the cell's content
				Iterator cells = row.cellIterator();

				while( cells.hasNext() ) {

					HSSFCell cell = (HSSFCell) cells.next();
					switch ( cell.getCellType() ) {
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell))
							setValue(column[cell.getCellNum()], aAddress,cell.getDateCellValue(), j==1);
						else
							setValue(column[cell.getCellNum()], aAddress, cell.getNumericCellValue(), j==1);
						break;
					case HSSFCell.CELL_TYPE_STRING: 
						if (rowNumber == 0)
							column[cell.getCellNum()] = cell.getStringCellValue();
						else
							setValue(column[cell.getCellNum()], aAddress, cell.getStringCellValue(), j==1);
						break;
					}
				}
				if (aAddress.getId() != null)
					if (j==0){
						addresses.add(aAddress);
					}
					else{
						diagnosisAddresses.add(aAddress);
					}
			}

		} catch ( IOException ex ) {
			ex.printStackTrace();
		}
	}
	}

	private void setValue(String column, AddressData o, Object value, boolean isDiagnosisAddress){
		if (column == null)
			return;
		if (isDiagnosisAddress){
			if (column.equalsIgnoreCase("Seq_No")){
				if (value != null){
					o.setId(DataLoadUtils.formatLong(value));
					if (isDiagnosisAddress)
						o.setId(o.getId() * MULTIPLIER);
				}
			}
			// link to patient
			if (column.equalsIgnoreCase("Accn_No")){
				if (value != null)
					o.setSequenceNumber(DataLoadUtils.formatLong(value));
			}

		}
		else{
			if (column.equalsIgnoreCase("accn_no")){
				if (value != null)
					o.setId(DataLoadUtils.formatLong(value));
			}
		}
		
		if (column.equalsIgnoreCase("address1")){
			o.setAddress1(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("address2")){
			o.setAddress2(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("city")){
			o.setCity(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("CountyCountryCode")){
			o.setCountyCountryCode(DataLoadUtils.format(value));
			o.setCounty(DataLoadUtils.format(value));
		}
		if (o.getCounty() != null && o.getState() != null){
			o.setCounty(lookup.getCountyValue(o.getCountyCountryCode(), o.getState()));
			if (o.getCountyCountryCode() != null)
				o.setCountry(lookup.getCountryValue(o.getCountyCountryCode()));
		}
		
		if (column.equalsIgnoreCase("state")){
			o.setState(DataLoadUtils.format(value));
		}
		if (column.equalsIgnoreCase("ZIPCode")){
			o.setZipcode(DataLoadUtils.format(value));
		}

	}
}

