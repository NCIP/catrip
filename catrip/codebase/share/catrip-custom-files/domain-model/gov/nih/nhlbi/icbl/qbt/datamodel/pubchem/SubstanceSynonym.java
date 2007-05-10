
package gov.nih.nhlbi.icbl.qbt.datamodel.pubchem;

/**
 *
 * @author srini
 */
public class SubstanceSynonym {

    private Long id;
    private String synonym;
    private Substance substance;
    
    /** Creates a new instance of Synonym */
    public SubstanceSynonym() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public Substance getSubstance() {
        return substance;
    }

    public void setSubstance(Substance substance) {
        this.substance = substance;
    }
    
}
