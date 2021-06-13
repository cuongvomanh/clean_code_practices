package core;

public class PageData {
    private String content;

    public PageData(String content) {
        this.content = content;
    }

    public WikiPage getWikiPage() {
        return new WikiPage();
    }

    public boolean hasAttribute(String test) {
        return true;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return String.format("%s : %s","html", this.content);
    }
}
