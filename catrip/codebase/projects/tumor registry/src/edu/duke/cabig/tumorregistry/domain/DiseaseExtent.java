package edu.duke.cabig.tumorregistry.domain;
import java.lang.Long;
import java.lang.String;
import java.lang.Float;





/**
 * @version 1.0
 * @created 01-Sep-2006 1:33:22 PM
 */
public class DiseaseExtent {

	private Long id;
	private String clinicalMetStage;
	private String clinicalNodeStage;
	private String clinicalTumorStage;
	/**
	 * Tumor Registry : Dg_path_m
	 * 
	 */
	private String pathologicMetStage;
	/**
	 * Tumor Registry : Dg_path_n
	 * 
	 */
	private String pathologicNodeStage;
	/**
	 * Tumor Registry : Dg_path_t
	 * 
	 */
	private String pathologicTumorStage;
	/**
	 * Tumor Registry : Dg_TNM_Editn
	 * 
	 */
	private String tnmEdition;
	/**
	 * measurement is in centimeters
	 */
	private Float tumorSize;
	private String pathologicAJCCStage;
	private String pathologicAJCCStageDescriptor;
	private String clinicalAJCCStage;
	private String clinicalAJCCStageDescriptor;
	/**
	 * Dg_Nd_Inv - Regional Nodes Positive
	 * 
	 */
	private String regionalNodesPositive;
	/**
	 * Dg_Nd_Exam - Regional Nodes Examined
	 * 
	 */
	private String regionalNodesExamined;
	/**
	 * Dg_Best_Stage
	 * Best AJCC stage, as calculated from derived Collaborative AJCC stage, or if no
	 * Collaborative stage, from Best of Path and Clin AJCC Stage.
	 * 
	 */ 
	private String bestAJCCStage;
	private String bestSEERSummaryStage;
	private String tumorMarker1;
	private String tumorMarker2;
	private String tumorMarker3;
	private java.util.Set distantSiteCollection;

	public DiseaseExtent(){

	}

	public void finalize() throws Throwable {

	}

	public String getBestAJCCStage() {
		return bestAJCCStage;
	}

	public void setBestAJCCStage(String bestAJCCStage) {
		this.bestAJCCStage = bestAJCCStage;
	}

	public String getBestSEERSummaryStage() {
		return bestSEERSummaryStage;
	}

	public void setBestSEERSummaryStage(String bestSEERSummaryStage) {
		this.bestSEERSummaryStage = bestSEERSummaryStage;
	}

	public String getClinicalAJCCStage() {
		return clinicalAJCCStage;
	}

	public void setClinicalAJCCStage(String clinicalAJCCStage) {
		this.clinicalAJCCStage = clinicalAJCCStage;
	}

	public String getClinicalAJCCStageDescriptor() {
		return clinicalAJCCStageDescriptor;
	}

	public void setClinicalAJCCStageDescriptor(String clinicalAJCCStageDescriptor) {
		this.clinicalAJCCStageDescriptor = clinicalAJCCStageDescriptor;
	}

	public String getClinicalMetStage() {
		return clinicalMetStage;
	}

	public void setClinicalMetStage(String clinicalMetStage) {
		this.clinicalMetStage = clinicalMetStage;
	}

	public String getClinicalNodeStage() {
		return clinicalNodeStage;
	}

	public void setClinicalNodeStage(String clinicalNodeStage) {
		this.clinicalNodeStage = clinicalNodeStage;
	}

	public String getClinicalTumorStage() {
		return clinicalTumorStage;
	}

	public void setClinicalTumorStage(String clinicalTumorStage) {
		this.clinicalTumorStage = clinicalTumorStage;
	}

	public java.util.Set getDistantSiteCollection() {
		return distantSiteCollection;
	}

	public void setDistantSiteCollection(java.util.Set distantSiteCollection) {
		this.distantSiteCollection = distantSiteCollection;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPathologicAJCCStage() {
		return pathologicAJCCStage;
	}

	public void setPathologicAJCCStage(String pathologicAJCCStage) {
		this.pathologicAJCCStage = pathologicAJCCStage;
	}

	public String getPathologicAJCCStageDescriptor() {
		return pathologicAJCCStageDescriptor;
	}

	public void setPathologicAJCCStageDescriptor(
			String pathologicAJCCStageDescriptor) {
		this.pathologicAJCCStageDescriptor = pathologicAJCCStageDescriptor;
	}

	public String getPathologicMetStage() {
		return pathologicMetStage;
	}

	public void setPathologicMetStage(String pathologicMetStage) {
		this.pathologicMetStage = pathologicMetStage;
	}

	public String getPathologicNodeStage() {
		return pathologicNodeStage;
	}

	public void setPathologicNodeStage(String pathologicNodeStage) {
		this.pathologicNodeStage = pathologicNodeStage;
	}

	public String getPathologicTumorStage() {
		return pathologicTumorStage;
	}

	public void setPathologicTumorStage(String pathologicTumorStage) {
		this.pathologicTumorStage = pathologicTumorStage;
	}

	public String getRegionalNodesExamined() {
		return regionalNodesExamined;
	}

	public void setRegionalNodesExamined(String regionalNodesExamined) {
		this.regionalNodesExamined = regionalNodesExamined;
	}

	public String getRegionalNodesPositive() {
		return regionalNodesPositive;
	}

	public void setRegionalNodesPositive(String regionalNodesPositive) {
		this.regionalNodesPositive = regionalNodesPositive;
	}

	public String getTnmEdition() {
		return tnmEdition;
	}

	public void setTnmEdition(String tnmEdition) {
		this.tnmEdition = tnmEdition;
	}

	public String getTumorMarker1() {
		return tumorMarker1;
	}

	public void setTumorMarker1(String tumorMarker1) {
		this.tumorMarker1 = tumorMarker1;
	}

	public String getTumorMarker2() {
		return tumorMarker2;
	}

	public void setTumorMarker2(String tumorMarker2) {
		this.tumorMarker2 = tumorMarker2;
	}

	public String getTumorMarker3() {
		return tumorMarker3;
	}

	public void setTumorMarker3(String tumorMarker3) {
		this.tumorMarker3 = tumorMarker3;
	}

	public Float getTumorSize() {
		return tumorSize;
	}

	public void setTumorSize(Float tumorSize) {
		this.tumorSize = tumorSize;
	}

}