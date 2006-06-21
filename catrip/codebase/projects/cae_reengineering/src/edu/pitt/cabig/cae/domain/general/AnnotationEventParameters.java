package edu.pitt.cabig.cae.domain.general;
import java.util.Date;





/**
 * @author Songhui Li
 * @version 1.0
 * @created 15-Jun-2006 2:15:28 PM
 */
public class AnnotationEventParameters extends EventParameters {

	private String source;
	private Date sourceDate;
	public AnnotationSet annotationSetCollection;

	public AnnotationEventParameters(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public void setSourceDate(Date sourceDate) {
        this.sourceDate = sourceDate;
    }

    public Date getSourceDate() {
        return sourceDate;
    }

    public void setAnnotationSetCollection(AnnotationSet annotationSetCollection) {
        this.annotationSetCollection = annotationSetCollection;
    }

    public AnnotationSet getAnnotationSetCollection() {
        return annotationSetCollection;
    }
}
