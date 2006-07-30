package edu.duke.catrip.cae.domain.general;

public class ParticipantMedicalIdentifier {
    
    private Long id;
    private String medicalRecordNumber;
    private Participant participant;
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

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Participant getParticipant() {
        return participant;
    }
}
