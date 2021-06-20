package argsrefactor;

public class BooleanArg extends BaseArg{
    private Boolean value;
    public BooleanArg(char vc, String arg) {
        super(vc);
        value = "1".equals(arg);
    }

    @Override
    public Object getValue() {
        return value;
    }
}
