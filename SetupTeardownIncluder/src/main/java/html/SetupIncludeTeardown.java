package html;

import core.*;

public class SetupIncludeTeardown implements SetupIncludeTeardownI {
    private PageData pageData;
    private boolean includeSuite;
    private WikiPage wikiPage;
    private WikiPage suiteSetup;
    private WikiPage teardown;
    private WikiPagePath pagePath;
    private WikiPagePath tearDownPath;
    private WikiPage setup;
    private WikiPage suiteTeardown;
    private WikiPagePath setupPath;
    private final StringBuffer buffer = new StringBuffer();

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
            includeSetupPages(includeSuite);
        }
        buffer.append(pageData.getContent());
        if (isTestPage(pageData)) {
            includeTeardownPages(includeSuite);
        }
        pageData.setContent(buffer.toString());
        return pageData.getHtml();
    }

    private void includeTeardownPages(boolean includeSuite) {
        includeTeardownPage();
        if (includeSuite) {
            includeSuiteTeardownPage();
        }
    }

    private void includeTeardownPage() {
        this.teardown =
                PageCrawlerImpl.getInheritedPage("TearDown", wikiPage);
        if (teardown != null) {
            renderTeardownPage();
        }
    }

    private void renderTeardownPage() {
        this.tearDownPath =
                wikiPage.getPageCrawler().getFullPath(teardown);
        String tearDownPathName = PathParser.render(tearDownPath);
        buffer.append("\n");
        render("teardown", tearDownPathName);
    }

    private void render(String type, String tearDownPathName) {
        buffer.append("!include -")
                .append(type)
                .append(" .")
                .append(tearDownPathName)
                .append("\n");
    }

    private void includeSuiteTeardownPage() {
        this.suiteTeardown =
                PageCrawlerImpl.getInheritedPage(
                        SuiteResponder.SUITE_TEARDOWN_NAME,
                        wikiPage
                );
        if (suiteTeardown != null) {
            renderSuiteTeardownPage();
        }
    }

    private void renderSuiteTeardownPage() {
        this.pagePath =
                suiteTeardown.getPageCrawler().getFullPath (suiteTeardown);
        String pagePathName = PathParser.render(pagePath);
        render("teardown", pagePathName);
    }

    private void includeSetupPages(boolean includeSuite) {
        if (includeSuite) {
            includeSuiteSetupPage();
        }
        includeSetupPage();
    }

    private void includeSetupPage() {
        this.setup =
                PageCrawlerImpl.getInheritedPage("SetUp", wikiPage);
        if (setup != null) {
            renderSetupPage();
        }
    }

    private void renderSetupPage() {
        this.setupPath =
                wikiPage.getPageCrawler().getFullPath(setup);
        String setupPathName = PathParser.render(setupPath);
        render("setup", setupPathName);
    }

    private void includeSuiteSetupPage() {
        this.suiteSetup =
                PageCrawlerImpl.getInheritedPage(
                        SuiteResponder.SUITE_SETUP_NAME, wikiPage
                );
        if (suiteSetup != null) {
            renderSuiteSetupPage();
        }
    }

    private void renderSuiteSetupPage() {
        this.pagePath =
                suiteSetup.getPageCrawler().getFullPath(suiteSetup);
        String pagePathName = PathParser.render(pagePath);
        render("setup", pagePathName);
    }

    private boolean isTestPage(PageData pageData) {
        return pageData.hasAttribute("Test");
    }
}
