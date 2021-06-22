package args;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;

public class ArgsExceptionTest extends TestCase {
    public void testUnexpectedMessage(){
        ArgsException e = new ArgsException(ErrorCode.UNEXPECTED_ARGUMENT, 'x', null);
        assertEquals("Argument -x unexpected.", e.errorMessage());
    }

    public void testMissingStringMessage(){
        ArgsException e = new ArgsException(ErrorCode.MISSING_STRING, 'x', null);
        assertEquals("Could not find string parameter for -x.", e.errorMessage());
    }

    public void testInvalidIntegerMessage(){
        ArgsException e = new ArgsException(ErrorCode.INVALID_INTEGER, 'x', "Forty two");
        assertEquals("Argument -x expects an integer but was 'Forty two'", e.errorMessage());
    }

    public void testMissingIntegerMessage(){
        ArgsException e = new ArgsException(ErrorCode.MISSING_INTEGER, 'x', null);
        assertEquals("Could not find integer parameter for -x.", e.errorMessage());
    }

    public void testInvalidDoubleMessage() throws Exception {
        ArgsException e = new ArgsException(ErrorCode.INVALID_DOUBLE,
                'x', "Forty two");
        assertEquals("Argument -x expects a double but was 'Forty two'.",
                e.errorMessage());
    }
    public void testMissingDoubleMessage() throws Exception {
        ArgsException e = new ArgsException(ErrorCode.MISSING_DOUBLE,
                'x', null);
        assertEquals("Could not find double parameter for -x.", e.errorMessage());
    }
}
