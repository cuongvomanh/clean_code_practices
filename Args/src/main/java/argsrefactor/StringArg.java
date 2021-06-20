package argsrefactor;

import args.ArgsException;

public class StringArg extends BaseArg {
    private String value;
    public StringArg(char vc, String arg) {
        super(vc);
        value = arg;
    }

    public static String getValue(BaseArg arg) {
        if (arg instanceof StringArg == false){
            throw new ArgsException("Not correct Type");
        }
        return (String) arg.getValue();
    }

    @Override
    public Object getValue() {
        return value;
    }
}
