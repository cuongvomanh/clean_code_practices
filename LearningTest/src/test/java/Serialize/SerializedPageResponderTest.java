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
        request.addInput("type", "pages");
        Responder responder = new SerializedPageResponder();
        SimpleResponse response = (SimpleResponse) responder.makeResponse(new FitNessContext(root), request);
        String xml = response.getContent();

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

        PageData data = pageOne.getData();
        WikiPageProperties properties = data.getProperties();
        WikiPageProperty symLinks = properties.set(SymbolicPage.PROPERTY_NAME);
        symLinks.set("SymPage", "PageTwo");
        pageOne.commit(data);

        request.setResource("root");
        request.addInput("type", "pages");
        Responder responder = new SerializedPageResponder();
        SimpleResponse response = (SimpleResponse) responder.makeResponse(new FitNessContext(root), request);
        String xml = response.getContent();

        assertEquals("text/xml", response.getContentType());
        assertSubString("<name>PageOne</name>", xml);
        assertSubString("<name>PageTwo</name>", xml);
        assertSubString("<name>ChildOne</name>", xml);
        assertNotSubString("SymPage", xml);
    }

    private void assertNotSubString(String symPage, String xml) {
    }

    @Test
    public void testGetDataAsHtml(){
        crawler.addPage(root, PathParser.parse("TestPageOne"), "test page");

        request.setResource("TestPageOne");
        request.addInput("type", "data");
        Responder responder = new SerializedPageResponder();
        SimpleResponse response = (SimpleResponse) responder.makeResponse(new FitNessContext(root), request);
        String xml = response.getContent();

        assertEquals("text/xml", response.getContentType());
        assertSubString("test page", xml);
        assertSubString("<Test", xml);
    }
}