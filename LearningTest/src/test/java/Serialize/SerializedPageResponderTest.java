package Serialize;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SerializedPageResponderTest {
    private Crawler crawler = new Crawler();
    private String root = "";
    private MyRequest request = new MyRequest();

    @Test
    public void testGetPageHieratchyAsXml() {
        crawler.addPage(root, PathParser.parse("PageOne"));
        crawler.addPage(root, PathParser.parse("PageOne.ChileOne"));
        crawler.addPage(root, PathParser.parse("PageTwo"));

        request.setResource("root");
        SimpleResponse response = getSimpleResponse("pages");
        String xml = response.getContent();

        assertXmlAndPages(response, xml);
    }

    private SimpleResponse getSimpleResponse(String pages) {
        request.addInput("type", pages);
        Responder responder = new SerializedPageResponder();
        return (SimpleResponse) responder.makeResponse(new FitNessContext(root), request);
    }

    private void assertXmlAndPages(SimpleResponse response, String xml) {
        assertEquals("text/xml", response.getContentType());
        assertSubString("<name>PageOne</name>", xml);
        assertSubString("<name>PageTwo</name>", xml);
        assertSubString("<name>ChildOne</name>", xml);
    }

    private void assertSubString(String s, String xml) {
    }

    @Test
    public void testGetPageHieratchyAsXmlDoesntContainSymbolicLinks(){
        WikiPage pageOne = crawler.addPage(root, PathParser.parse("PageOne"));
        crawler.addPage(root, PathParser.parse("PageOne.ChildOne"));
        crawler.addPage(root, PathParser.parse("PageTwo"));

        commitPageOne(pageOne);

        request.setResource("root");
        SimpleResponse response = getSimpleResponse("pages");
        String xml = response.getContent();

        assertXmlAndPages(response, xml);
        assertNotSubString("SymPage", xml);
    }

    private void commitPageOne(WikiPage pageOne) {
        PageData data = pageOne.getData();
        WikiPageProperties properties = data.getProperties();
        WikiPageProperty symLinks = properties.set(SymbolicPage.PROPERTY_NAME);
        symLinks.set("SymPage", "PageTwo");
        pageOne.commit(data);
    }

    private void assertNotSubString(String symPage, String xml) {
    }

    @Test
    public void testGetDataAsHtml(){
        crawler.addPage(root, PathParser.parse("TestPageOne"), "test page");

        request.setResource("TestPageOne");
        SimpleResponse response = getSimpleResponse("data");
        String xml = response.getContent();

        assertEquals("text/xml", response.getContentType());
        assertSubString("test page", xml);
        assertSubString("<Test", xml);
    }
}