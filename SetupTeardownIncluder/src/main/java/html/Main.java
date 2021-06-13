package html;

public class Main {
    public static void main(String[] args){
        try {
            String html = HtmlUtil.testableHtml(new PageData("PAGE CONTENT"), true);
            System.out.println(html);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
