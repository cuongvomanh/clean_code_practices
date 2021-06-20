package argsrefactor;

public class IntArg extends BaseArg {
    private int value;
    public IntArg(char vc, String arg) {
        super(vc);
        value = Integer.parseInt(arg);
    }

    @Override
    public Object getValue() {
        return value;
    }
}
