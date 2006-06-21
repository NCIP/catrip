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

    private Collection locations;
    private String locationMVR;
    private String venousLymphaticInvasion;
    private Collection microcalcificationLocations;

    public InvasiveBreastCarcinoma(){

    }


    public void setLocation(Collection locations) {
        this.locations = locations;
    }

    public Collection getLocation() {
        return locations;
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

    public void setMicrocalcificationLocation(Collection microcalcificationLocations) {
        this.microcalcificationLocations = microcalcificationLocations;
    }


    public Collection getMicrocalcificationLocation() {
        return microcalcificationLocations;
    }
}
