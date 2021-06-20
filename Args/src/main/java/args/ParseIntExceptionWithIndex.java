package args;

public class ParseIntExceptionWithIndex extends ArgsException {
    public ParseIntExceptionWithIndex(int index){
        super("Can not parse to int param " + index);
    }
}
