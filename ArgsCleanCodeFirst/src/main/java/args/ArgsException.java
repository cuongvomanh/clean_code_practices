package args;

public class ArgsException extends Exception{
    private final ErrorCode errorCode;
    private final char errorArgumentId;
    private final String errorParameter;

    public ArgsException(ErrorCode errorCode, char errorArgumentId, String errorParameter) {
        this.errorCode = errorCode;
        this.errorArgumentId = errorArgumentId;
        this.errorParameter = errorParameter;
    }

    public String errorMessage() {
        return "";
    }

    public ErrorCode getErrorCode() {
        return null;
    }

    public char getErrorArgumentId() {
        return 0;
    }

    public char getErrorAgumentId() {
        return 0;
    }

    public String getErrorParameter() {
        return "";
    }

}
