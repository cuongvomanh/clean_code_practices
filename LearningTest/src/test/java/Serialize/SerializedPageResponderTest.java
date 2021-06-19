package Serialize;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SerializedPageResponderTest {
    public static final String NAME_PAGE_ONE_NAME = "<name>PageOne</name>";
    public static final String NAME_PAGE_TWO_NAME = "<name>PageTwo</name>";
    public static final String NAME_CHILD_ONE_NAME = "<name>ChildOne</name>";
    private Crawler crawler = new Crawler();
    private String root = "";
    private MyRequest request = new MyRequest();
    private SimpleResponse response;

    private void addPages(String page1, String page2, String page3) {
        addPage(page1);
        addPage(page2);
        addPage(page3);
    }

    private void addPages(String page1, String page2) {
        addPage(page1);
        addPage(page2);
    }

    private WikiPage addPage(String page1) {
        WikiPage pageOne = crawler.addPage(root, PathParser.parse(page1));
        return pageOne;
    }

    @Test
    public void testGetPageHieratchyAsXml() {
        addPages("PageOne", "PageOne.ChildOne", "PageTwo");

        summitRequest("root", "pages");

        assertXmlType();
        assertPagesContain(NAME_PAGE_ONE_NAME, NAME_PAGE_TWO_NAME, NAME_CHILD_ONE_NAME);
    }

    private void summitRequest(String resource, String type) {
        request.setResource(resource);
        response = getSimpleResponse(type);
    }

    private SimpleResponse getSimpleResponse(String pages) {
        request.addInput("type", pages);
        Responder responder = new SerializedPageResponder();
        return (SimpleResponse) responder.makeResponse(new FitNessContext(root), request);
    }

    private void assertPagesContain(String s1, String s2, String s3) {
        assertPagesContain(s1);
        assertPagesContain(s2);
        assertPagesContain(s3);
    }

    private void assertXmlType() {
        assertEquals("text/xml", response.getContentType());
    }

    private void assertSubString(String s, String xml) {
    }

    @Test
    public void testGetPageHieratchyAsXmlDoesntContainSymbolicLinks(){
        WikiPage pageOne = addPage("PageOne");
        addPages("PageOne.ChildOne", "PageTwo");

        createSymPageOnPageOne(pageOne, "SymPage");
        summitRequest("root", "pages");

        assertXmlType();
        assertPagesContain(NAME_PAGE_ONE_NAME, NAME_PAGE_TWO_NAME, NAME_CHILD_ONE_NAME);
        assertPagesNotContain("SymPage");
    }

    private void assertPagesNotContain(String s1) {
        assertNotSubString(s1, response.getContent());
    }


    private void createSymPageOnPageOne(WikiPage pageOne, String symPage) {
        PageData data = pageOne.getData();

        WikiPageProperties properties = data.getProperties();
        WikiPageProperty symLinks = properties.set(SymbolicPage.PROPERTY_NAME);
        symLinks.set(symPage, "PageTwo");
        pageOne.commit(data);
    }

    private void assertNotSubString(String symPage, String xml) {
    }

    @Test
    public void testGetDataAsHtml(){
        makePageWithContent("TestPageOne", "test page");

        summitRequest("TestPageOne", "data");

        assertXmlType();
        assertPagesContain("test page");
        assertPagesContain("<Test");
    }

    private void assertPagesContain(String s) {
        assertSubString(s, response.getContent());
    }

    private void makePageWithContent(String page, String content) {
        crawler.addPage(root, PathParser.parse(page), content);
    }
}