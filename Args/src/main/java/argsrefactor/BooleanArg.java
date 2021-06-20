package argsrefactor;

import args.ArgsException;

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

    public static Boolean getValue(BaseArg arg){
        if (arg instanceof BooleanArg == false){
            throw new ArgsException("Not correct Type");
        }
        return (Boolean) arg.getValue();
    }
}
