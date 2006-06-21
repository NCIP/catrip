package edu.pitt.cabig.cae.domain.general;
import java.lang.Long;

import java.util.Date;


/**
 * @author Songhui Li
 * @version 1.0
 * @created 15-Jun-2006 2:15:31 PM
 */
public class EventParameters {

	private Long id;
	private Date timeStamp;

	public EventParameters(){

	}

	public void finalize() throws Throwable {

	}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
