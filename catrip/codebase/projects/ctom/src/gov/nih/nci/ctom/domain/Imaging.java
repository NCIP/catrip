package gov.nih.nci.ctom.domain;

/**
 * @version 1.0
 * @created 26-Jun-2006 9:10:28 AM
 */
public class Imaging extends Procedure {

	/**
	 * Values include: Not Applicable, No, Yes.
	 */
	public boolean contrastAgentEnhancementIndicator;
	public String enhancementDescriptionText;
	public int enhancementRateValue;
	public String imageIdentifier;

	public Imaging(){

	}

	public boolean isContrastAgentEnhancementIndicator() {
		return contrastAgentEnhancementIndicator;
	}

	public void setContrastAgentEnhancementIndicator(
			boolean contrastAgentEnhancementIndicator) {
		this.contrastAgentEnhancementIndicator = contrastAgentEnhancementIndicator;
	}

	public String getEnhancementDescriptionText() {
		return enhancementDescriptionText;
	}

	public void setEnhancementDescriptionText(String enhancementDescriptionText) {
		this.enhancementDescriptionText = enhancementDescriptionText;
	}

	public int getEnhancementRateValue() {
		return enhancementRateValue;
	}

	public void setEnhancementRateValue(int enhancementRateValue) {
		this.enhancementRateValue = enhancementRateValue;
	}

	public String getImageIdentifier() {
		return imageIdentifier;
	}

	public void setImageIdentifier(String imageIdentifier) {
		this.imageIdentifier = imageIdentifier;
	}

}