package args;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
    private int intValue;

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String intString = null;
        try {
            intString = currentArgument.next();
            intValue = Integer.parseInt(intString);
        } catch (NoSuchElementException e){
            throw new ArgsException(ErrorCode.MISSING_INTEGER);
        } catch (NumberFormatException e){
            throw new ArgsException(ErrorCode.INVALID_INTEGER, intString);
        }
    }

    public static int getValue(ArgumentMarshaler argumentMarshaler) {
        return ((IntegerArgumentMarshaler) argumentMarshaler).intValue;
    }
}
