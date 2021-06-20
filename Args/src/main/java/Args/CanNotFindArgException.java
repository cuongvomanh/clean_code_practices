package Args;

public class CanNotFindArgException extends ArgsException {
    public CanNotFindArgException(char arg) {
        super("Can not find arg " + arg);
    }

}
