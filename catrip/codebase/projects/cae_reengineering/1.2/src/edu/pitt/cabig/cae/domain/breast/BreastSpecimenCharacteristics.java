package edu.pitt.cabig.cae.domain.breast;

	/**
	 * @version 1.0
	 * @created 15-Sep-2006 3:07:06 PM
	 */
	public class BreastSpecimenCharacteristics extends SpecimenCharacteristics {

		private static final long serialVersionUID = 1234567890L;
		private String lymphNodeSamplingProcedure;
		private String laterality;
		private String lateralityMVR;

		public BreastSpecimenCharacteristics(){

		}

		public void finalize() throws Throwable {
			super.finalize();
		}

		public String getLaterality() {
			return laterality;
		}

		public void setLaterality(String laterality) {
			this.laterality = laterality;
		}

		public String getLateralityMVR() {
			return lateralityMVR;
		}

		public void setLateralityMVR(String lateralityMVR) {
			this.lateralityMVR = lateralityMVR;
		}

		public String getLymphNodeSamplingProcedure() {
			return lymphNodeSamplingProcedure;
		}

		public void setLymphNodeSamplingProcedure(String lymphNodeSamplingProcedure) {
			this.lymphNodeSamplingProcedure = lymphNodeSamplingProcedure;
		}

	}