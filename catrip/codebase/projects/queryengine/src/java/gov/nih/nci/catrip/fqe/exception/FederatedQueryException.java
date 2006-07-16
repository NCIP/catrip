package gov.nih.nci.catrip.fqe.exception;

public class FederatedQueryException extends Exception{ 
    public FederatedQueryException(Throwable cause) {
        super(cause);
    }
    
    public FederatedQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
