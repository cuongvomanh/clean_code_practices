package argsrefactor;

public class StringArg extends BaseArg {
    private String value;
    public StringArg(char vc, String arg) {
        super(vc);
        value = arg;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
