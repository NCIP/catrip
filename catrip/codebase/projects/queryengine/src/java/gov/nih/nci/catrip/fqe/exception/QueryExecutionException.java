package gov.nih.nci.catrip.fqe.exception;

public class QueryExecutionException extends Exception{ 
    public QueryExecutionException(Throwable cause) {
        super(cause);
    }
    
    public QueryExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}