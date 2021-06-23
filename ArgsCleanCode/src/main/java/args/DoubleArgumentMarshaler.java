package args;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleArgumentMarshaler implements ArgumentMarshaler {
    private double doubleValue;
    public static double getValue(ArgumentMarshaler argumentMarshaler) {
        return ((DoubleArgumentMarshaler) argumentMarshaler).doubleValue;
    }

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String doubleString = null;
        try {
            doubleString = currentArgument.next();
            doubleValue = Double.parseDouble(doubleString);
        } catch (NoSuchElementException e){
            throw new ArgsException(ErrorCode.MISSING_DOUBLE);
        } catch (NumberFormatException e){
            throw new ArgsException(ErrorCode.INVALID_DOUBLE, doubleString);
        }
    }
}
