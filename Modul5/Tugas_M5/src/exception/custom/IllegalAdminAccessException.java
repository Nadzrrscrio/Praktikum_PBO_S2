package exception.custom;
public class IllegalAdminAccessException extends Exception {
    public IllegalAdminAccessException(String message) {
        super(message);
    }
}