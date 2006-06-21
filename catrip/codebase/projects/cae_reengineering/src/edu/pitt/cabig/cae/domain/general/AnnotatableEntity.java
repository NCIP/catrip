package edu.pitt.cabig.cae.domain.general;
import java.lang.Long;





/**
 * @version 1.0
 * @created 15-Jun-2006 2:15:28 PM
 */
public class AnnotatableEntity {

	private Long id;
	public AnnotationEventParameters annotationEventParametersCollection;

	public AnnotatableEntity(){

	}

	public void finalize() throws Throwable {

	}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAnnotationEventParametersCollection(AnnotationEventParameters annotationEventParametersCollection) {
        this.annotationEventParametersCollection = annotationEventParametersCollection;
    }

    public AnnotationEventParameters getAnnotationEventParametersCollection() {
        return annotationEventParametersCollection;
    }
}
