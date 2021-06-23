package args;

import java.util.*;

public class Args {
    private String schema;
    private String[] args;
    private boolean valid;
    private Set<Character> unexpectedArguments = new TreeSet<Character>();
    private Map<Character, ArgumentMarshaler> argumentMarshalers = new HashMap<Character, ArgumentMarshaler>();
    private int numberOfArguments = 0;
    private Set<Character> argsFound = new HashSet<>();
    private ErrorCode errorCode;
    private int currentArgument;
    private char errorAgument = '\0';

    public Args(String schema, String[] args) throws ArgsException {
        this.schema = schema;
        this.args = args;
        valid = parse();
    }

    public boolean isValid() {
        return valid;
    }

    private boolean parse() throws ArgsException {
        if (schema.length() == 0 && args.length == 0) {
            return true;
        }
        parseSchema();
        parseArguments();
        return unexpectedArguments.size() == 0;
    }

    private boolean parseSchema() throws ArgsException {
        for (String element : schema.split(",")) {
            if (element.length() > 0){
                String strimmedElement = element.trim();
                parseSchemaElement(strimmedElement);
            }
        }
        return true;
    }

    private void parseSchemaElement(String element) throws ArgsException {
        char elementId = element.charAt(0);
        String elementTail = element.substring(1);
        validateSchemaElementId(elementId);
        if (isBooleanSchemaElement(elementTail)) {
            parseBooleanSchemaElement(element);
        } else if (isStringSchemaElement(elementTail)){
            parseStringSchemaElement(elementId);
        } else {
            throw new ArgsException(ErrorCode.INVALID_FORMAT, elementId, elementTail);
        }
    }

    private boolean isStringSchemaElement(String elementTail) {
        return elementTail.equals("*");
    }

    private boolean isBooleanSchemaElement(String elementTail) {
        return elementTail.length() == 0;
    }

    private void validateSchemaElementId(char elementId) throws ArgsException {
        if (!Character.isLetter(elementId)){
            throw new ArgsException(ErrorCode.INVALID_ARGUMENT_NAME, elementId, null);
        }
    }

    private void parseStringSchemaElement(char elementId){
        argumentMarshalers.put(elementId, new StringArgumentMarshaler(""));
    }

    private void parseBooleanSchemaElement(String element) {
        char c = element.charAt(0);
        if (Character.isLetter(c)) {
            ArgumentMarshaler arg = new BooleanArgumentMarshaler(true);
            argumentMarshalers.put(c, arg);
        }
    }

    private boolean parseArguments() throws ArgsException {
        for (currentArgument = 0; currentArgument < args.length; currentArgument++) {
            parseArgument(args[currentArgument]);
        }
        return true;
    }

    private void parseArgument(String arg) throws ArgsException {
        if (arg.startsWith("-"))
            parseElements(arg);
    }

    private void parseElements(String arg) throws ArgsException {
        for (int i = 1; i < arg.length(); i++) {
            parseElement(arg.charAt(i));
        }
    }

    private void parseElement(char argChar) throws ArgsException {
        if (setArgument(argChar)) {
            argsFound.add(argChar);
        } else {
            unexpectedArguments.add(argChar);
            valid = false;
        }
    }

    private boolean setArgument(char argChar) throws ArgsException {
        ArgumentMarshaler argumentMarshaler = argumentMarshalers.get(argChar);
        if (argumentMarshaler == null){
            throw new ArgsException(ErrorCode.UNEXPECTED_ARGUMENT, argChar, null);
        }
        if (! (argumentMarshaler instanceof BooleanArgumentMarshaler)){
            currentArgument++;
        }
        argumentMarshaler.set(args[currentArgument]);
        return true;
    }

    private void setStringArg(char argChar) {
        currentArgument++;
        try {
            argumentMarshalers.put(argChar, new StringArgumentMarshaler(args[currentArgument]));
        } catch (ArrayIndexOutOfBoundsException e){
            valid = false;
            errorAgument = argChar;
            errorCode = ErrorCode.MISSING_STRING;
        }
    }

    private boolean isString(char argChar) {
        return argumentMarshalers.containsKey(argChar);
    }

    private void setBooleanArg(char argChar, boolean value) {
        argumentMarshalers.put(argChar, new BooleanArgumentMarshaler(value));
    }

    private boolean isBoolean(char argChar) {
        return argumentMarshalers.containsKey(argChar);
    }

    public int cardinality() {
        return argsFound.size();
    }

    public String usage() {
        if (schema.length() > 0)
            return "-[" + schema + "]";
        else
            return "";
    }

    public String errorMessage() throws Exception {
        if (unexpectedArguments.size() > 0) {
            return unexpectedArgumentMessage();
        } else{
            switch (errorCode){
                case MISSING_STRING:
                    return String.format("Could not find string parameter for -%c.", errorAgument);
                case OK:
                    throw new Exception("TILT: Should not get here.");
            }
        }
        return "";
    }

    private String unexpectedArgumentMessage() {
        StringBuffer message = new StringBuffer("Argument(s) -");
        for (char c : unexpectedArguments) {
            message.append(c);
        }
        message.append(" unexpected.");
        return message.toString();
    }

    public boolean getBoolean(char arg) throws ArgsException {
        if (argsFound.contains(arg)){
            ArgumentMarshaler argumentMarshaler = argumentMarshalers.get(arg);
            if (argumentMarshaler instanceof BooleanArgumentMarshaler){
                return ((BooleanArgumentMarshaler) argumentMarshaler).getBooleanValue();
            } else {
                throw new ArgsException(ErrorCode.INVALID_FORMAT, arg, null);
            }
        } else {
            throw new ArgsException(ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }

    public String getString(char arg) throws ArgsException {
        if (argsFound.contains(arg)){
            ArgumentMarshaler argumentMarshaler = argumentMarshalers.get(arg);
            if (argumentMarshaler instanceof StringArgumentMarshaler){
                return ((StringArgumentMarshaler) argumentMarshaler).getStringValue();
            } else {
                throw new ArgsException(ErrorCode.INVALID_FORMAT, arg, null);
            }
        } else {
            throw new ArgsException(ErrorCode.UNEXPECTED_ARGUMENT, arg, null);
        }
    }

    public boolean has(char arg) {
        return argsFound.contains(arg);
    }

    public double getDouble(char arg) {
        return 0;
    }

    public int getInt(char arg) {
        return 0;
    }
}
