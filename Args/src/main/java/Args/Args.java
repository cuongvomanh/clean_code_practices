package Args;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Args {
    private String pattern;
    private String[] args;
    private List<String> variables;
    private Map<Character, Object> argStore;
    public Args(String pattern, String[] args) {
        this.pattern = pattern;
        this.args = args;
        variables = extractPattern(pattern);
        if(variables.size() > args.length){
            throw new ArgsException("Size of Args less than size of pattern!");
        }
        argStore = new HashMap<>();
        for(int i=0; i<variables.size();i++){
            parseArg(i);
        }
    }

    private void parseArg(int i) {
        String v =variables.get(i);
        char vc = v.charAt(0);
        Object value;
        if (v.endsWith("#")){
            value = parseInt(vc);
        } else if (v.endsWith("*")){
            value = parseString(vc);
        } else {
            value = parseBoolean(vc);
        }
        argStore.put(vc, value);
    }

    private List<String> extractPattern(String pattern) {
        return Arrays.asList(pattern.split(","));
    }

    public Boolean getBoolean(char b){
        Boolean aBoolean = (Boolean) argStore.get(b);
        if (aBoolean != null){
            return aBoolean;
        } else {
            throw new CanNotFindArgException(b);
        }
    }

    public int getInt(char i){
        Integer aInt = (Integer) argStore.get(i);
        if (aInt != null){
            return aInt;
        } else {
            throw new CanNotFindArgException(i);
        }
    }

    public String getString(char s){
        String aString = (String) argStore.get(s);
        if (aString != null){
            return aString;
        } else {
            throw new CanNotFindArgException(s);
        }
    }

    public Boolean parseBoolean(char b) {
        int index = variables.indexOf(Character.toString(b));
        checkIndexEqualMinusOneAndThrowException(b, index);
        String arg = returnOrThrowArgsException(args, index);
        return "1".equals(arg);
    }

    private String returnOrThrowArgsException(String[] args, int index) {
        try {
            return args[index];
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArgsException(e.getMessage());
        }
    }

    private void checkIndexEqualMinusOneAndThrowException(char b, int index) {
        if (index == -1) {
            throw new CanNotFindArgException(b);
        }
    }

    public int parseInt(char i) {
        int index = variables.indexOf(String.format("%s#", i));
        checkIndexEqualMinusOneAndThrowException(i, index);
        try{
            String arg = returnOrThrowArgsException(args, index);
            return Integer.parseInt(arg);
        } catch (Exception exception){
            System.out.println("Exception: " + exception);
            throw new ParseIntExceptionWithIndex(index);
        }
    }

    public String parseString(char s) {
        int index = variables.indexOf(String.format("%s*", s));
        checkIndexEqualMinusOneAndThrowException(s, index);
        String arg = returnOrThrowArgsException(args, index);
        return arg;
    }

}
