package springbook.user.sqlService.exception;

public class SqlRetrievalFailureException extends RuntimeException{
    public SqlRetrievalFailureException(String message){
        super(message);
    }

    public SqlRetrievalFailureException(String message, Throwable cause){
        super(message, cause);
    }
}
