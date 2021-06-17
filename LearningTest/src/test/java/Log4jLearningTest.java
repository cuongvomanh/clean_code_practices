
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Log4jLearningTest {
    Logger logger = Logger.getLogger("MyLogger");
    @Before
    public void setup() {
        logger.removeAllAppenders();
        Logger.getRootLogger().removeAllAppenders();
    }

    @Test
    public void afterLog_WillNotPrint(){
        logger.info("Hello");
    }

    @Test
    public void afterAddAppender_WillPrint(){
        ConsoleAppender consoleAppender = new ConsoleAppender(new PatternLayout("%p %t %m%n"), ConsoleAppender.SYSTEM_OUT);
        logger.addAppender(consoleAppender);
        logger.info("Hello");
    }

    @Test
    public void afterAddAppenderWithoutSystemOut_WillNotPrint(){
        ConsoleAppender consoleAppender = new ConsoleAppender(new PatternLayout("%p %t %m%n"));
        logger.addAppender(consoleAppender);
        logger.info("Hello");
    }


}
