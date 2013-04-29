/*L
 * Copyright Duke Comprehensive Cancer Center
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/catrip/LICENSE.txt for details.
 */

package edu.duke.catrip.cae.domain.general;

public class ParticipantMedicalIdentifier implements java.io.Serializable {

	private static final long serialVersionUID = 1234567890L;
    private Long id;
    private String medicalRecordNumber;
    private edu.duke.catrip.cae.domain.general.Participant participant;
    public ParticipantMedicalIdentifier() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber) {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    public void setParticipant(edu.duke.catrip.cae.domain.general.Participant participant) {
        this.participant = participant;
    }

    public edu.duke.catrip.cae.domain.general.Participant getParticipant() {
        return participant;
    }
    
    public boolean equals(Object obj){
            boolean eq = false;
            if(obj instanceof ParticipantMedicalIdentifier) {
                    ParticipantMedicalIdentifier c =(ParticipantMedicalIdentifier)obj;                         
                    Long thisId = getId();          
                    
                            if(thisId != null && thisId.equals(c.getId())) {
                               eq = true;
                        }           
                    
            }
            return eq;
    }
    
    public int hashCode(){
            int h = 0;
            
            if(getId() != null) {
                    h += getId().hashCode();
            }
            
            return h;
    }
}
