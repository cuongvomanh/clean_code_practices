package core;

public class PageCrawlerImpl {
    public static WikiPage getInheritedPage(String suiteSetupName, WikiPage wikiPage) {
        WikiPage inheritedWikiPage = new WikiPage();
        inheritedWikiPage.setName(suiteSetupName);
        return inheritedWikiPage;
    }

    public WikiPagePath getFullPath(WikiPage suiteSetup) {
        return new WikiPagePath(String.format("%s + %s", "suiteSetup_WikiPagePath", suiteSetup.getName()));
    }
}
