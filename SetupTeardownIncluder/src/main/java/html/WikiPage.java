package html;

public class WikiPage {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PageCrawlerImpl getPageCrawler() {
        return new PageCrawlerImpl();
    }
}
