package core;

public class PageCrawlerImpl {
    public static WikiPage getInheritedPage(String suiteSetupName, WikiPage wikiPage) {
        wikiPage.setName(suiteSetupName);
        return wikiPage;
    }

    public WikiPagePath getFullPath(WikiPage suiteSetup) {
        return new WikiPagePath(String.format("%s + %s", "suiteSetup_WikiPagePath", suiteSetup.getName()));
    }
}
