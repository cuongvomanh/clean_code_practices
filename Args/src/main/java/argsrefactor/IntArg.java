package argsrefactor;

import args.ArgsException;

public class IntArg extends BaseArg {
    private int value;
    public IntArg(char vc, String arg) {
        super(vc);
        value = Integer.parseInt(arg);
    }

    public static int getValue(BaseArg arg) {
        if (arg instanceof IntArg == false){
            throw new ArgsException("Not correct Type");
        }
        return (int) arg.getValue();
    }

    @Override
    public Object getValue() {
        return value;
    }
}
