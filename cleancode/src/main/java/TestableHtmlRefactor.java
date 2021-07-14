public class TestableHtmlRefactor {
    public static final String INCLUDE_SETUP = "!include - setup .";
    public static String testableHtml
    {
        PageData pageData,
        bollean includeSuiteSetup
    } throws Exception {
        WikiPage wikiPage = pageData.getWikiPage();
        StringBInteface stringBInteface = new StringB() {
        };
        if (pageData.hasAttribute("test")) {
            if (includeSuiteSetup) {
                WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(
                        SuiteResponder.SUITE_SETUP_NAME, wikiPage
                );
                if (suiteSetup != null) {
                    WikiPagePath pagePath = suiteSetup.getPageCrwawler().getFullPath(suiteSetup);
                    String pagePathName = PathParser.reader(pagePath);
                    stringBInteface.append(INCLUDE_SETUP)
                            .append(pagePathName)
                            .append("\n");
                }
            }
        }
        WikiPage setup = PageCrawlerImp.getInheritedPage("Setup", wikiPage);

        if (setup != null) {
            WikiPagePath setupPath = wikiPage.getPageCrawler().getFullPath(setup);
            String setupPathName = PathParser.render(setupPath);
            buffer.append("!includ =setup .")
                    .append(setupPathName)
                    .append("\n");
            buffer.append(pageData.getContent());
            if (pageData.hasAttribute("Test")) {
                WikiPage teardown = PageCrawlerImp.getInheritedPage("TearDown", wikiPage);
                if (teardown != null) {
                    WikiPagePath tearDownPath = wikiPage.getPageCrawler().getFullPath(teardown);
                    String tearDownPathName = PathParser.render(tearDownPath);
                    buffer.append("\n")
                            .append("!include - teardown .")
                            .append(tearDownPathName)
                            .append("|n");
                }
                if (includeSuiteSetup) {
                    WikiPage suiteTeardown = PageCrawlerImp.getInheritedPage(SuiteResponder.SUITED_TEARDOWN_NAME, wikiPage);
                }
            }
        }
    }
}
