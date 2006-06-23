package edu.pitt.cabig.cae.domain.general;
import java.lang.Long;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:28 PM
 */
public class AnnotationSet {

	private Long id;

	public AnnotationSet(){

	}

	public void finalize() throws Throwable {

	}

        public void setId(Long id) {
            this.id = id;
        }

        /**
           *
           * @hibernate.id
           *    column="ID"
           *    generator-class="assigned"
        */
        public Long getId() {
            return id;
        }
}
