package Args;

public class ArgsException extends RuntimeException{
    private String errorMessage;
    public ArgsException(String mess) {
        super(mess);
        errorMessage = mess;
    }

    public String errorMessage() {
        return errorMessage;
    }
}
