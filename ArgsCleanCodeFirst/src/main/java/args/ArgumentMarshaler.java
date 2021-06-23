package args;

public abstract class ArgumentMarshaler {
    public abstract void set(String value) throws ArgsException;
}
