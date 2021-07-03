package Stack;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    private Stack stack;

    @Before
    public void setUp() throws Exception {
        stack = new Stack();
    }

    @Test
    public void newStack_isEmpty() throws Exception {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void afterOnePush_isNotEmpty() throws Exception {
        stack.push(0);
        assertFalse(stack.isEmpty());
    }

    @Test(expected = Stack.Underflow.class)
    public void willThrowUnderflow_WhenEmptyStackIsPopped() throws Exception {
        stack.pop();
    }

    @Test
    public void afterOnePushAndOnePop_WillBeEmpty() throws Exception{
        stack.push(0);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    public void afterTwoPushesAndOnePop_WillNotEmpty() throws Exception{
        stack.push(0);
        stack.push(0);
        stack.pop();
        assertFalse(stack.isEmpty());
    }

    @Test
    public void afterPushX_WillPopX() throws Exception {
        stack.push(0);
        stack.push(99);
        assertEquals(stack.pop(), 99);
        stack.push(88);
        assertEquals(stack.pop(), 88);
    }

    @Test
    public void afterPushXAndY_WillPopYAndX() throws Exception{
        stack.push(0);
        stack.push(99);
        assertEquals(stack.pop(), 99);
        assertEquals(stack.pop(), 0);
    }
}
