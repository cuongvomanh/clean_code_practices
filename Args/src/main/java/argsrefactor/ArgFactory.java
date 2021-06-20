package argsrefactor;

public class ArgFactory {

    BaseArg parseArg(String v, String value) {
        BaseArg arg;
        char vc = getVc(v);
        if (v.endsWith("#")) {
            arg = new IntArg(vc, value);
        } else if (v.endsWith("*")) {
            arg = new StringArg(vc, value);
        } else {
            arg = new BooleanArg(vc, value);
        }
        return arg;
    }

    private char getVc(String v) {
        return v.charAt(0);
    }
}