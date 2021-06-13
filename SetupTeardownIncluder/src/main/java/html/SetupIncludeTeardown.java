package html;

import core.*;

import java.util.Locale;

public class SetupIncludeTeardown implements SetupIncludeTeardownI {
    public static final String SET_UP = "SetUp";
    public static final String SETUP = "setup";
    public static final String TEAR_DOWN = "TearDown";
    public static final String TEARDOWN = "teardown";
    public static final String INCLUDE = "!include -";
    public static final String TEST = "Test";
    private PageData pageData;
    private boolean includeSuite;
    private WikiPage wikiPage;
    private final StringBuffer newPageContent = new StringBuffer();

    @Override
    public void init(
            PageData pageData,
            boolean includeSuite
    ){
        this.pageData = pageData;
        this.includeSuite = includeSuite;
        this.wikiPage = pageData.getWikiPage();
    }

    @Override
    public String render() throws Exception {
        if (isTestPage(pageData)) {
            includeSetupPages();
        }
        newPageContent.append(pageData.getContent());
        newPageContent.append("\n");
        if (isTestPage(pageData)) {
            includeTeardownPages();
        }
        pageData.setContent(newPageContent.toString());
        return pageData.getHtml();
    }

    private void includeTeardownPages() {
        includeTeardownPage();
        if (includeSuite) {
            includeSuiteTeardownPage();
        }
    }

    private void includeTeardownPage() {
        include(TEAR_DOWN, TEARDOWN);
    }

    private void include(String pageName, String type) {
        WikiPage includeWikiPage =
                PageCrawlerImpl.getInheritedPage(pageName, wikiPage);
        if (includeWikiPage != null) {
            String pathName = getPagePath(includeWikiPage);
            render(type, pathName);
        }
    }

    private String getPagePath(WikiPage includeWikiPage) {
        WikiPagePath tearDownPath =
                wikiPage.getPageCrawler().getFullPath(includeWikiPage);
        String pathName = PathParser.render(tearDownPath);
        return pathName;
    }

    private void render(String type, String tearDownPathName) {
        newPageContent.append(INCLUDE)
                .append(type)
                .append(" .")
                .append(tearDownPathName)
                .append("\n");
    }

    private void includeSuiteTeardownPage() {
        include(SuiteResponder.SUITE_TEARDOWN_NAME, TEARDOWN);
    }

    private void includeSetupPages() {
        if (includeSuite) {
            includeSuiteSetupPage();
        }
        includeSetupPage();
    }

    private void includeSetupPage() {
        include(SET_UP, SETUP);
    }

    private void includeSuiteSetupPage() {
        include(SuiteResponder.SUITE_SETUP_NAME, SETUP);
    }

    private boolean isTestPage(PageData pageData) {
        return pageData.hasAttribute(TEST);
    }
}
