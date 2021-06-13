package html;

import core.PageData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HtmlUtilTest {
    @Test
    public void renderHtml(){
        try {
            SetupIncludeTeardownI setupIncludeTeardown = new HtmlUtil();
            String html = null;
            setupIncludeTeardown.init(new PageData("PAGE CONTENT"), true);
            html = setupIncludeTeardown.render();
            String actual = "html : !include -setup .suiteSetup_WikiPagePath + SUITE_SETUP_NAME\n" +
                    "!include -setup .suiteSetup_WikiPagePath + SetUp\n" +
                    "PAGE CONTENT\n" +
                    "!include -teardown .suiteSetup_WikiPagePath + TearDown\n" +
                    "!include -teardown .suiteSetup_WikiPagePath + SUITE_TEARDOWN_NAME\n";
            assertEquals(actual, html);
        } catch (Exception exception) {
            exception.printStackTrace();
            assert false;
        }
    }
}