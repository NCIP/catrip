/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */


package gov.nih.nci.caintegrator.domain.common.bean;
import gov.nih.nci.caintegrator.domain.common.bean.*;
import java.util.*;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * array design identifier, given by the manufactures of the array: eg: U133plus2.0 by Affy manufactures 
   * 
   */

public  enum BioAssayArrayType 


{
 	
	   
       /**
   * A complementary DNA (cDNA) Array
   */

     cdnaarray
	   
       /**
   * Affymetrics Human Genome U133 Plus 2.0 Array
   */

     , hgu133p2
	   
       /**
   * Affymetic's Gene Chip Human Mapping 100K 
   */

     , hm100ksnparray;
}