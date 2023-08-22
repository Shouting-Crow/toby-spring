package springbook.user.sqlService.exception;

public class SqlUpdateFailureException extends RuntimeException{
    public SqlUpdateFailureException(String message){
        super(message);
    }

    public SqlUpdateFailureException(Exception e) {super(e.getMessage());}

    public SqlUpdateFailureException(String message, Throwable cause){
        super(message, cause);
    }
}
