package edu.pitt.cabig.cae.domain.breast;
import edu.pitt.cabig.cae.domain.general.Neoplasm;
import java.util.Collection;
import java.lang.String;

 /**
  *
  * @hibernate.class
  *           table="INVASIVE_BREAST_CARCINOMA"
  *
  *
  */

public class InvasiveBreastCarcinoma extends Neoplasm {

    private Collection location;
    private String locationMVR;
    private String venousLymphaticInvasion;
    private Collection microcalcificationLocation;

    public InvasiveBreastCarcinoma(){

    }


    public void setLocation(Collection location) {
        this.location = location;
    }

    /**
     *
     * @hibernate.bag
     *            name="location"
     *            lazy="false"
     *            table="BREAST_LOCATIONS"
     *
     * @hibernate.collection-key
     *            column="ID"
     *
     * @hibernate.collection-element
     *            column="LOCATION"
     *            type="java.lang.String"
     *
     */
    public Collection getLocation() {
        return location;
    }

    public void setLocationMVR(String locationMVR) {
        this.locationMVR = locationMVR;
    }

    /**
     * @hibernate.property
     *   column="LOCATION_MVR"
     *   type="java.lang.String"
     *
     */
    public String getLocationMVR() {
        return locationMVR;
    }

    public void setVenousLymphaticInvasion(String venousLymphaticInvasion) {
        this.venousLymphaticInvasion = venousLymphaticInvasion;
    }

    /**
     * @hibernate.property
     *   column="VENOUS_LYMPHATIC_INVASION"
     *   type="java.lang.String"
     *
     */
    public String getVenousLymphaticInvasion() {
        return venousLymphaticInvasion;
    }

    public void setMicrocalcificationLocation(Collection microcalcificationLocation) {
        this.microcalcificationLocation = microcalcificationLocation;
    }

    /**
     *
     * @hibernate.bag
     *            name="microcalcificationLocation"
     *            lazy="false"
     *            table="BREAST_MICROCALCIFICATION_LOCATIONS"
     *
     * @hibernate.collection-key
     *            column="ID"
     *
     * @hibernate.collection-element
     *            column="MICROCALCIFICATION_LOCATION"
     *            type="java.lang.String"
     *
     */
    public Collection getMicrocalcificationLocation() {
        return microcalcificationLocation;
    }
}
