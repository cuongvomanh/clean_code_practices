import Args.Args;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import Args.CanNotFindArgException;
import Args.ParseIntExceptionWithIndex;
import Args.ArgsException;

public class ArgsTest {

    private Args arg;
    private String[] args;

    @Test
    public void args1_WillTrue(){
        args = new String[]{"1"};
        arg = new Args("l", args);
        Boolean b = arg.getBoolean('l');
        assertEquals(true, b);
    }

    @Test
    public void args0_WillFalse(){
        args = new String[]{"0"};
        arg = new Args("l", args);
        Boolean b = arg.getBoolean('l');
        assertEquals(false, b);
    }

    @Test
    public void args1And0_WillTrueAndFalse(){
        args = new String[]{"1", "0"};
        arg = new Args("t,f", args);
        Boolean t = arg.getBoolean('t');
        Boolean f = arg.getBoolean('f');
        assertEquals(true, t);
        assertEquals(false, f);
    }

    @Test
    public void args1And0And1_WillTrueAndFalseAndTrue(){
        args = new String[]{"1", "0", "1"};
        arg = new Args("t,f,k", args);
        Boolean t = arg.getBoolean('t');
        Boolean f = arg.getBoolean('f');
        Boolean k = arg.getBoolean('k');
        assertEquals(true, t);
        assertEquals(false, f);
        assertEquals(true, k);
    }

    @Test
    public void patternTGetT_NotThrowException(){
        args = new String[]{"1"};
        arg = new Args("t", args);
        Boolean t = arg.getBoolean('t');
    }

    @Test(expected = CanNotFindArgException.class)
    public void patternTGetK_WillThrowException(){
        args = new String[]{"1"};
        arg = new Args("t", args);
        Boolean t = arg.getBoolean('k');
    }

    @Test
    public void argXPatternYHashGetY_WillX(){
        args = new String[]{"99"};
        arg = new Args("l#", args);
        int t = arg.getInt('l');
        assertEquals(99, t);
    }

    @Test
    public void patternTHashGetT_NotThrowException(){
        args = new String[]{"99"};
        arg = new Args("t#", args);
        int t = arg.getInt('t');
    }

    @Test(expected = CanNotFindArgException.class)
    public void patternTHashGetK_WillThrowException(){
        args = new String[]{"1"};
        arg = new Args("t#", args);
        int t = arg.getInt('k');
    }

    @Test(expected = ParseIntExceptionWithIndex.class)
    public void argsStringPatternTHashGetT_WillThrowException(){
        args = new String[]{"number"};
        arg = new Args("t#", args);
        int t = arg.getInt('t');
    }

    @Test
    public void argsXXX_WillXXX(){
        args = new String[]{"XXX"};
        arg = new Args("s*", args);
        String s = arg.getString('s');
        assertEquals("XXX", s);
    }

    @Test
    public void patternBAndXHashAndSStar_WillBAndXAndS(){
        args = new String[]{"0", "99", "STRING"};
        arg = new Args("b,i#,s*", args);
        assertEquals(false, arg.getBoolean('b'));
        assertEquals(99, arg.getInt('i'));
        assertEquals("STRING", arg.getString('s'));
    }

    @Test(expected = ArgsException.class)
    public void patternXArgsEmpty_WillThrowException(){
        args = new String[]{};
        arg = new Args("b", args);
        Boolean b = arg.getBoolean('b');
    }

    @Test(expected = CanNotFindArgException.class)
    public void patternTStarGetK_WillThrowException(){
        args = new String[]{"XXX"};
        arg = new Args("t*", args);
        String t = arg.getString('k');
    }

    @Test(expected = ParseIntExceptionWithIndex.class)
    public void patternBAndXHashAndSGetB_WillThrowParseIntExceptionWithIndex(){
        args = new String[]{"0", "NUMBER", "STRING"};
        arg = new Args("b,i#,s*", args);
    }

    @Test
    public void argsXPatternTHashGetValueT_WillX(){
        args = new String[]{"99"};
        arg = new Args("x#", args);
        int x = (int) arg.getValue('x');
        assertEquals(99, x);
    }


}
