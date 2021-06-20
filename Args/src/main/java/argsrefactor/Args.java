package argsrefactor;

import args.ArgsException;
import args.CanNotFindArgException;
import args.ParseIntExceptionWithIndex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Args {
    private final ArgFactory argFactory = new ArgFactory();
    private String pattern;
    private String[] args;
    private List<String> variables;
    private Map<Character, BaseArg> argStore;
    public Args(String pattern, String[] args) {
        this.pattern = pattern;
        this.args = args;
        variables = extractPattern(pattern);
        if(variables.size() > args.length){
            throw new ArgsException("Size of Args less than size of pattern!");
        }
        argStore = new HashMap<>();
        for(int i=0; i<variables.size();i++){
            String v = variables.get(i);
            try {
                BaseArg arg = argFactory.parseArg(v, args[i]);
                argStore.put(arg.getVc(), arg);
            } catch (NumberFormatException exception){
                System.out.println("Exception: " + exception);
                throw new ParseIntExceptionWithIndex(i);
            }
        }
    }


    private List<String> extractPattern(String pattern) {
        return Arrays.asList(pattern.split(","));
    }

    public Boolean getBoolean(char b){
        return (Boolean) getArg(b);
    }

    private Object getArg(char b) {
        BaseArg arg = argStore.get(b);
        if (arg != null){
            return arg.getValue();
        } else {
            throw new CanNotFindArgException(b);
        }
    }

    public int getInt(char i){
        return (int) getArg(i);
    }

    public String getString(char s){
        return (String) getArg(s);
    }

    public Object getValue(char x) {
        BaseArg arg = argStore.get(x);
        if (arg != null){
            return arg.getValue();
        } else {
            throw new CanNotFindArgException(x);
        }
    }
}
